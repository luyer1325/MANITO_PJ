package com.leeds.manito.manito_pj.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.entity.ManitoInfo;
import com.leeds.manito.manito_pj.entity.UserInfo;
import com.leeds.manito.manito_pj.repository.ManitoRepository;
import com.leeds.manito.manito_pj.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ManitoService2 {
    
    @Autowired
    ManitoRepository manitoRepository;

    @Autowired
    UserRepository userRepository;

    ManitoInfo manito = new ManitoInfo();

    // @Autowired
    // ModelMapper modelMapper;
    private final ModelMapper modelMapper;

    public ManitoService2(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void insertUser(HttpSession session,int idx){
        //String abc = (String)session.getAttribute("kakaoId");
        UserInfo ui = UserInfo.builder()
            .manitoIdx(idx)
            .userId((String)session.getAttribute("email"))
            .kakaoId((String)session.getAttribute("kakaoId"))
            .build();
        //return userRepository.save(ui).getManitoIdx();
    }

    public ManitoInfoDTO getManitoInfo(Model model, int idx){
        ManitoInfo manitoInfo = manitoRepository.findBymanitoIdx(idx).orElseGet(() -> manito);
        return modelMapper.map(manitoInfo, ManitoInfoDTO.class);
    }

    public long checkCnt(String kakaoId){
        return userRepository.countByKakaoId(kakaoId);
    }
}
