package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;

import com.leeds.manito.manito_pj.entity.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,String>{
    //Optional<UserInfo> 
}
