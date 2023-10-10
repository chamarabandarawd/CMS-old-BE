package com.cms.cms.config;

import com.cms.cms.dao.UserInfo;
import com.cms.cms.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserInfo> userInfo=userInfoRepository.findByUsername(username);

       return userInfo.map(UserInfoUserDetails::new)
               .orElseThrow(()->new UsernameNotFoundException("user not found "+username));


    }
}
