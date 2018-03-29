package com.qlone.deureka.login.server.service;


import com.qlone.deureka.api.basic.enumapi.ApiEnum;
import com.qlone.deureka.api.basic.enumapi.ApiResult;
import com.qlone.deureka.api.login.service.UserModelService;
import com.qlone.deureka.login.server.dao.UserDataMapper;
import com.qlone.deureka.login.server.dto.UserDataDTO;
import com.qlone.deureka.login.server.util.RedisClient;
import com.qlone.deureka.login.server.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@Component("UserDataService")
public class UserDataService implements UserModelService {
    Logger logger = LoggerFactory.getLogger(UserModelService.class);
    private final static ReentrantLock lock = new ReentrantLock();
    @Autowired
    UserDataMapper userDataMapper;
    @Autowired
    RedisClient redisClient;


    @Override
    public ApiResult<ApiEnum,String> loginAndGetToken(String account, String password,boolean muiltylogin) {
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setAccount(account);
        userDataDTO.setPassword(password);
        try {
            logger.info(">>>>>>>>>>>logging");
            UserDataDTO queryDto = userDataMapper.checkAccountAndPsw(userDataDTO);
            if(queryDto == null){ return ApiEnum.userResult(ApiEnum.USER_FAIL,"");}
            //登陆成功后，获取token，将id信息放入token中
            String userId = String.valueOf(queryDto.getId());
            //是否支持多人登陆
            String token = null;
            if(muiltylogin){
                //如果支持将redis中的token分享
                token = redisClient.get(userId);
                if(token == null){
                    token = TokenUtil.getToken(userId);
                    redisClient.set(userId,token,86400);
                }
            }else{
                //如果不支持直接覆盖redis 中的token
                token = TokenUtil.getToken(userId);
                redisClient.set(userId,token,86400);
            }


            return ApiEnum.userResult(ApiEnum.USER_SUCCESS,token);
        } catch (Exception e) {
            logger.info("error",e);
            return ApiEnum.userResult(ApiEnum.USER_SUCCESS,"");
        }
    }

    @Override
    public ApiResult<ApiEnum,String> checkToken(String token) {
        if(token == null){return ApiEnum.userResult(ApiEnum.USER_PARAMTER_FAIL,"");}
        token = token.replace(" ","+");
        String value = TokenUtil.getValue(token);
        if(value == null){return ApiEnum.userResult(ApiEnum.USER_FAIL,"");}
        try {
            //尝试从redis上获取token，若redis上token与传来token相同则有权限
            String redisToken = redisClient.get(value);
            logger.info("value:"+value);
            logger.info("redisToken:"+redisToken);
            logger.info("token:"+token);
            if(token.equals(redisToken)){
                return ApiEnum.userResult(ApiEnum.USER_SUCCESS,value);
            }else {
                return ApiEnum.userResult(ApiEnum.USER_FAIL,"");
            }
        } catch (Exception e) {
            logger.error("获取redis失败 value:"+value,e);
            return ApiEnum.userResult(ApiEnum.USER_FAIL,"");
        }
    }


    @Override
    public ApiResult<ApiEnum, String> registerAccount(String account, String psw) {
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(psw)){
            return ApiEnum.userResult(ApiEnum.USER_PARAMTER_FAIL,"");
        }
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setAccount(account);
        userDataDTO.setPassword(psw);

        try {
            lock.lock();
            //检查账号是否已有
            UserDataDTO res = userDataMapper.queryUserAccount(userDataDTO);
            if( res != null ){
                //若已经有账号则，返回
                return ApiEnum.userResult(ApiEnum.USER_ACCOUNT_EXSITS,"");
            }
            //否则,插入账号 并获取插入成功数
            int insertRes = userDataMapper.insertUser(userDataDTO);
            if(insertRes < 1){
                return ApiEnum.userResult(ApiEnum.USER_FAIL,"插入失败,插入数目:"+insertRes);
            }
            return ApiEnum.userResult(ApiEnum.USER_SUCCESS,userDataDTO.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


        return ApiEnum.userResult(ApiEnum.USER_FAIL,"");
    }
}
