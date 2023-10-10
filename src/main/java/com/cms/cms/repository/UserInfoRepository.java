package com.cms.cms.repository;

import com.cms.cms.dao.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
}
