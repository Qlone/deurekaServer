package com.qlone.deureka.api.basic.controller;

import com.qlone.deureka.api.basic.enumapi.ApiEnum;
import com.qlone.deureka.api.basic.enumapi.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     * @param req
     * @param resp
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @RequestMapping(produces = { "application/json;charset=UTF-8" })
    public Object defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {


        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return ApiEnum.userResult(ApiEnum.API_404,"");
        } else {
            return ApiEnum.userResult(ApiEnum.API_500,"");
        }

    }
}
