package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;

import com.leeds.manito.manito_pj.entity.ManitoInfo;

import java.util.Optional;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
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
    List<ManitoInfo> findByStartDate(String time);
    @Procedure(procedureName  = "game_setting") //프로시저를 만들때 out 파라미터를 만들어야하며, 레파지토리에서는 in파라미터만 넣어주고 out파라미터는 따로 데이터를 안넣어도 된다.
    int setGameSetting();
    /* 프로시저 쿼리문
    drop procedure if exists game_setting;
    DELIMITER //

    CREATE PROCEDURE game_setting(out o_val varchar(20))
    begin
	DECLARE b_val int;
	DECLARE i_val int;
	DECLARE c_val int;
	DECLARE v_manito_idx varchar(10);
	DECLARE done INT DEFAULT FALSE;
	DECLARE cur CURSOR FOR SELECT manito_idx FROM manito_info where status='B' and start_date <= now();	
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

	
	CREATE TEMPORARY TABLE b_table AS SELECT manito_idx FROM manito_info where status='B' and start_date <= now();
	CREATE TEMPORARY TABLE i_table AS SELECT manito_idx FROM manito_info where status='I' and end_date >= now();
	CREATE TEMPORARY TABLE c_table AS SELECT manito_idx FROM manito_info where status='I' and end_date <= now();

	select count(*) into b_val from b_table;
	select count(*) into i_val from i_table;
	select count(*) into c_val from c_table;

	open cur;

	IF b_val >= '1'
	then update manito_info set status = 'I' where manito_idx in (select * from b_table);
	
	

	read_loop: LOOP
        FETCH cur INTO v_manito_idx;
		select v_manito_idx as b_ma;
	-- 1. 랜덤 순서로 사용자 목록 생성

	WITH randomized_users AS (
	    SELECT 
	        user_id,
	        kakao_id,
	        ROW_NUMBER() OVER (ORDER BY RAND()) AS rn,
	        COUNT(*) OVER () AS total_users
	    FROM 
	        user_info where manito_idx = v_manito_idx
	),
	
	-- 2. 매칭 테이블 생성
	paired_users AS (
	    SELECT 
	        ru.user_id,
	        ru.kakao_id,
	        ru.rn,
	        ru.total_users,
	        COALESCE(LEAD(ru.kakao_id) OVER (ORDER BY ru.rn), (SELECT kakao_id FROM randomized_users WHERE rn = 1)) AS target_kakao_id
	    FROM 
	        randomized_users ru
	)
	
	-- 3. 원본 테이블 업데이트
	UPDATE 
	    user_info ui
	JOIN 
	    paired_users pu ON ui.user_id = pu.user_id
	SET 
	    ui.manito_target = pu.target_kakao_id
	WHERE 
		ui.manito_idx =v_manito_idx
		and ui.kakao_id != pu.target_kakao_id;
	   
	IF done THEN LEAVE read_loop;
    END IF;
	END loop read_loop;
	close cur;
	elseif i_val >= '1'
	then select count(*) from i_table;
	elseif c_val>= '1'
	then update manito_info set status = 'C' where manito_idx in (select * from c_table);
	end IF;
	
	set o_val = b_val;
    -- 임시 테이블 제거
	DROP TEMPORARY TABLE b_table;
	DROP TEMPORARY TABLE i_table;
	DROP TEMPORARY TABLE c_table;

    END //

    DELIMITER ; 

     */
}
