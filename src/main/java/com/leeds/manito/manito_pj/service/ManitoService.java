package com.leeds.manito.manito_pj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.entity.ManitoInfo;
import com.leeds.manito.manito_pj.repository.ManitoRepository;


@Service
public class ManitoService {

    @Autowired
    ManitoRepository manitoRepository;
    /*
     * 마니또 게임의 생성 메소드
     * 
     * @ param ManitoInfoDTO
     * @ return ManitoInfoDTO.getManito_idx
     */
    public Integer CreateManito(ManitoInfoDTO manitoInfoDTO){
        ManitoInfo me = ManitoInfo.builder()
            .join_yn(manitoInfoDTO.getJoin_yn())
            .show_yn(manitoInfoDTO.getShow_yn())
            .mission_yn(manitoInfoDTO.getMission_yn())
            .create_user("test")    
            .created(manitoInfoDTO.getCreated())
            .end_date(manitoInfoDTO.getEnd_date())
            .build();
            this.manitoRepository.save(me);
            return manitoInfoDTO.getManito_idx();
    }
}
