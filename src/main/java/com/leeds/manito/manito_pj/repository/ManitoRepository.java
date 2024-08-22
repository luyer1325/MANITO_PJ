package com.leeds.manito.manito_pj.repository;

import org.springframework.stereotype.Repository;

import com.leeds.manito.manito_pj.entity.ManitoInfo;

import java.util.Optional;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	@Modifying // @Query는 기본적으로 조회만 가능하며 update,delete같이 변경이 필요한 경우 @Modifying 필요 이가 없을 경우 'Expecting a SELECT Query' 오류 나타남
	@Query("UPDATE ManitoInfo mi SET mi.status = 'I' WHERE mi.manitoIdx = :manitoIdx") //jpql에서는 mi.컬럼명인데 언더바( _ )를 다 빼고 사용해야한다.
	void updateStatusByManitoIdx(@Param("manitoIdx") int manitoIdx);
    
	Optional<ManitoInfo> findBymanitoIdx(int manitoIdx);

    List<ManitoInfo> findByStartDate(String time);
}
