package com.qlone.deureka.login.controller;

import com.qlone.deureka.login.server.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class LoginRestController{
    @Autowired
    UserDataService userDataService;


    @RequestMapping(value = "/login",produces =  { "application/json;charset=UTF-8" })
    public Object loginAndGetToken(@RequestParam(value = "account",defaultValue = "") String account,
                                   @RequestParam(value = "psw",defaultValue = "") String password,
                                   @RequestParam(value = "flag",defaultValue = "false") boolean muiltylogin) {
        return userDataService.loginAndGetToken(account,password,muiltylogin);
    }

    @RequestMapping(value = "/check",produces =  { "application/json;charset=UTF-8" })
    public Object checkToken(@RequestParam(value = "token") String token) {
        return userDataService.checkToken(token);
    }

    @RequestMapping(value = "/register",produces =  { "application/json;charset=UTF-8" })
    public Object registerAccount(@RequestParam(value = "account",defaultValue = "") String account,
                                  @RequestParam(value = "psw",defaultValue = "") String password) {
        return userDataService.registerAccount(account, password);
    }
}
