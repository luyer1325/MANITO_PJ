package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;
import com.leeds.manito.manito_pj.entity.ManitoInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ManitoRepository extends JpaRepository<ManitoInfo,Integer>{
    
    //@PersistenceContext
    //private EntityManager entitymanager;

    // Top = LIMIT (default 1), OrderByManitoIdx Desc= order by Manito_idx desc
    // @Param("createUser") 를 사용한다면 :createUser에 연결된다.     
    // 네이티브쿼리는 DB에서 직접적인 쿼리사용이므로 DB에서 쓰는 펑션과 문법을 사용해야한다.
    @Query(value = "select mi.* from Manito_Info mi where (mi.create_User = :createUser or mi.manito_Idx = :manitoIdx) and mi.deleted  is null and Date_Format(mi.end_Date,'%Y-%m-%d %H:%i:%s')>now()", nativeQuery = true) //네이티브 쿼리
    Optional<ManitoInfo> findByCreateUserOrManitoIdx(@Param("createUser")String createUser, @Param("manitoIdx")int manitoIdx); 
    Optional<ManitoInfo> findBymanitoIdx(int manitoIdx);
}
