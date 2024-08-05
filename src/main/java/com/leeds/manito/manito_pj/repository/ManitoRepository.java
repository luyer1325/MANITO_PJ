package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;
import com.leeds.manito.manito_pj.entity.ManitoInfo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ManitoRepository extends JpaRepository<ManitoInfo,Integer>{
    //@Query("select m from ManitoInfo m where m.manito_idx is not null")
    //List<ManitoInfo> methodName();
    Optional<ManitoInfo> findByCreateUser(String CreateUser);
    Optional<ManitoInfo> findBymanitoIdx(int manitoIdx);
}
