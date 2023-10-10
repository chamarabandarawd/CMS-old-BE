package com.cms.cms.controller;

import com.cms.cms.dao.UserInfo;
import com.cms.cms.servise.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/signUp")
    public String userCreate(@RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }
}
