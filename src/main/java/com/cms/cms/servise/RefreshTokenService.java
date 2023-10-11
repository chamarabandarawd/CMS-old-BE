package com.cms.cms.servise;

import com.cms.cms.dao.RefreshToken;
import com.cms.cms.repository.RefreshTokenRepository;
import com.cms.cms.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public RefreshToken createRefreshToken(String username){
       RefreshToken refreshToken= RefreshToken.builder()
                .userInfo(userInfoRepository.findByUsername(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(60000)) //10 min
                .build();

       return refreshTokenRepository.save(refreshToken);
    }
}
