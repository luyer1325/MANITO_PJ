package com.leeds.manito.manito_pj.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.entity.ManitoInfo;
import com.leeds.manito.manito_pj.repository.ManitoRepository;

@Service
public class ManitoService {
    private final ManitoRepository manitoRepository;

    public ManitoService(ManitoRepository manitoRepository) {
        this.manitoRepository = manitoRepository;
    } 

    public Integer insert(ManitoInfoDTO manitoInfoDTO){
        ManitoInfo me = ManitoInfo.builder()
            .join_yn(manitoInfoDTO.getJoin_yn())
            .show_yn(manitoInfoDTO.getShow_yn())
            .mission_yn(manitoInfoDTO.getMission_yn())
            .create_user("test")    
            .created(manitoInfoDTO.getCreated())    
            .build();
            this.manitoRepository.save(me);
            return manitoInfoDTO.getManito_idx();
    }
}
