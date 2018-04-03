package com.qlone.deureka.api.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlone.deureka.api.basic.enumapi.ApiEnum;
import com.qlone.deureka.api.basic.enumapi.ApiResult;
import com.qlone.deureka.login.server.service.UserDataService;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class TokenAOP {
    Logger logger = LoggerFactory.getLogger(TokenAOP.class);
    private static final  ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserDataService userDataService;


    @Around("within(com.qlone.deureka..*) && @annotation(parseToken)")
    public Object around(ProceedingJoinPoint joinPoint,ParseToken parseToken) throws Throwable{
        logger.info("begin aop");
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = joinPoint.getSignature().getName(); //获取方法名称
        Object[] args = joinPoint.getArgs();//参数

        int index = findFieldsNameIndex(this.getClass(), clazzName, methodName, args,"token");

        args[index] = ((String)args[index]).replace(" ","+");
        //验证token
        ApiResult<ApiEnum,String> result = userDataService.checkToken((String)args[index]);
        if(result.getCode().equals(ApiEnum.USER_SUCCESS)){
            return joinPoint.proceed(args);
        }else{
            returnJson(ApiEnum.userResult(ApiEnum.USER_ILLEGEL,""));
            return null;
        }

    }

    private void  returnJson(Object object) throws Exception {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletResponse response = sra.getResponse();
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        ServletOutputStream sos = null;
        try{
            String msg = objectMapper.writeValueAsString(object);
            sos = response.getOutputStream();
            sos.write(msg.getBytes("UTF-8"));
            sos.flush();
            sos.close();
        }catch (Exception e){
            throw new Exception("页面出错");
        }finally {
            if( sos != null){
                sos.close();
            }
        }
    }


    private int findFieldsNameIndex(Class cls, String clazzName, String methodName, Object[] args,String findName) throws NotFoundException {


        ClassPool pool = ClassPool.getDefault();
        //ClassClassPath classPath = new ClassClassPath(this.getClass());
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        // String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            //map.put(attr.variableName(i + pos), args[i]);//paramNames即参数名
            if(attr.variableName(i + pos).equals(findName));
            return i;
        }
        return -1;

    }
}
