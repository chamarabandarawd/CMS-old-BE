package com.cms.cms.controller;

import com.cms.cms.dao.RefreshToken;
import com.cms.cms.dao.UserInfo;
import com.cms.cms.dto.AuthRequest;
import com.cms.cms.dto.JwtResponse;
import com.cms.cms.servise.RefreshTokenService;
import com.cms.cms.servise.UserInfoService;
import com.cms.cms.servise.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/signUp")
    public String userCreate(@RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }


    @PostMapping("/login")
    public JwtResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));


        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getUsername());
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(authRequest.getUsername()))
                    .token(refreshToken.getToken())
                    .build();
        }else {
            throw new UsernameNotFoundException("Invalid user request !...");
        }
    }
}
