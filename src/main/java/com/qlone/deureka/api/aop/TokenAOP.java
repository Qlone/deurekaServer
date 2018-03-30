package com.qlone.deureka.api.aop;

import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TokenAOP {
    Logger logger = LoggerFactory.getLogger(TokenAOP.class);

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

        return joinPoint.proceed(args);

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
