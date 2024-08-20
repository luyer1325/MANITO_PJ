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
import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

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

    public UserInfoDTO checkUser(UserInfoDTO userInfoDTO){
        UserInfo userInfo= userRepository.findByManitoIdxOrUserId(userInfoDTO.getManitoIdx(),userInfoDTO.getUserId()).orElseGet(()-> user);
        return modelMapper.map(userInfo, UserInfoDTO.class);

    }

    public void startGame(String time){
        List<ManitoInfo> list = manitoRepository.findByStartDate(time);
    }

    @Transactional
    public void gameSetting(){
        String abc = "out";
        // int aaa = manitoRepository.setGameSetting();
        // System.out.println("스케쥴러 ="+ aaa);
    }
}
