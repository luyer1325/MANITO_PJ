package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.leeds.manito.manito_pj.entity.ManitoEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ManitoRepository extends JpaRepository<ManitoEntity,Integer>{
    Optional<ManitoEntity> findByUserName(String username);
}
