package com.leeds.manito.manito_pj.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.dto.UserInfoDTO;
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
    UserInfo user = new UserInfo();

    // @Autowired
    // ModelMapper modelMapper;
    private final ModelMapper modelMapper;

    public ManitoService2(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserInfoDTO insertUser(HttpSession session,int idx){
        String abc = (String)session.getAttribute("kakaoId");
        String bbc = (String)session.getAttribute("email");
        System.out.println("abaaa = "+ abc +" , "+bbc);


        UserInfo ui = UserInfo.builder()
            .manitoIdx(idx)
            .userId((String)session.getAttribute("email"))
            .kakaoId((String)session.getAttribute("kakaoId"))
            .build();
        UserInfo userInfo = userRepository.save(ui); // 저장된 엔티티 반환
        return modelMapper.map(userInfo,UserInfoDTO.class);
    }

    public ManitoInfoDTO getManitoInfo(Model model, int idx){
        ManitoInfo manitoInfo = manitoRepository.findBymanitoIdx(idx).orElseGet(() -> manito);
        return modelMapper.map(manitoInfo, ManitoInfoDTO.class);
    }

    public long checkCnt(String kakaoId){
        return userRepository.countByKakaoId(kakaoId);
    }

    public UserInfoDTO checkUser(int idx){
        UserInfo userInfo= userRepository.findByManitoIdx(idx).orElseGet(()-> user);
        return modelMapper.map(userInfo, UserInfoDTO.class);
    }
}
