package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;

import com.leeds.manito.manito_pj.entity.UserInfo;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,String>{
    Long countByKakaoId(String kakaoId);
    Optional<UserInfo> findByManitoIdxOrUserId(int idx,String userId);
    Optional<UserInfo> findByKakaoId(String kakaoId);
    List<UserInfo> findByManitoIdx(int idx);

    @Query("select ui.nickname from UserInfo ui where ui.kakaoId = :manitoTarget")
    String findNicknameByKakaoId(String manitoTarget);

    @Query("select ui.nickname from UserInfo ui where ui.manitoTarget =:kakaoId")
    String findNicknameByManitoTarget(String kakaoId);

    @Procedure(procedureName  = "start_game")
    void updateStartSetting(@Param("manitoIdx")int manitoIdx);
    /*
    drop procedure if exists start_game;
    DELIMITER //
    CREATE PROCEDURE start_game(
        in v_manito_idx varchar(10)
        )
        begin
        WITH randomized_users AS (
            SELECT 
                user_id,
                kakao_id,
                ROW_NUMBER() OVER (ORDER BY RAND()) AS rn,
                COUNT(*) OVER () AS total_users
            FROM 
                user_info where manito_idx = v_manito_idx
        ),
        nick_users as(
            select
                v_nickname,
                ROW_NUMBER() OVER (ORDER BY RAND()) AS rn
            from nick_list nl 
        ),
        
        -- 2. 매칭 테이블 생성
        paired_users AS (
            SELECT 
                ru.user_id,
                ru.kakao_id,
                ru.rn,
                ru.total_users,
                COALESCE(LEAD(ru.kakao_id) OVER (ORDER BY ru.rn), (SELECT kakao_id FROM randomized_users WHERE rn = 1)) AS target_kakao_id,
                nu.v_nickname
            FROM 
                randomized_users ru
                LEFT JOIN 
                nick_users nu ON ru.rn = nu.rn
        )
        
        -- 3. 원본 테이블 업데이트
        UPDATE 
            user_info ui
        JOIN 
            paired_users pu ON ui.user_id = pu.user_id
        SET 
            ui.manito_target = pu.target_kakao_id
            ,ui.nickname = pu.v_nickname
        WHERE 
            ui.manito_idx =v_manito_idx
            and ui.kakao_id != pu.target_kakao_id;
        end //
    DELIMITER ;
     */
}
