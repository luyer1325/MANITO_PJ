package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;

import com.leeds.manito.manito_pj.entity.UserInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,String>{
    Long countByKakaoId(String kakaoId);
    Optional<UserInfo> findByManitoIdxOrUserId(int idx,String userId);
    Optional<UserInfo> findByKakaoId(String kakaoId);
}
