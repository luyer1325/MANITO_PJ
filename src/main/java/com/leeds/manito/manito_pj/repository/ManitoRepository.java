package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;
import com.leeds.manito.manito_pj.entity.ManitoInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ManitoRepository extends JpaRepository<ManitoInfo,Integer>{
    
    //@PersistenceContext
    //private EntityManager entitymanager;

    //@Query("select m from ManitoInfo m where m.manito_idx is not null")
    //List<ManitoInfo> methodName();
    @Query("SELECT mi FROM ManitoInfo mi WHERE (mi.createUser = :createUser or mi.manitoIdx = :manitoIdx) and mi.endDate >= current_timestamp")
    Optional<ManitoInfo> findByCreateUserOrManitoIdx(String createUser,int manitoIdx , int time);
    Optional<ManitoInfo> findBymanitoIdx(int manitoIdx);
}
