package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;
import com.leeds.manito.manito_pj.entity.ManitoInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ManitoRepository extends JpaRepository<ManitoInfo,Integer>{
    @Query("select m from ManitoInfo m where m.manito_idx is not null")
    List<ManitoInfo> methodName();
    
}
