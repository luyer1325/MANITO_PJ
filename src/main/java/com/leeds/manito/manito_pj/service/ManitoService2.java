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

@Service
public class ManitoService2 {
    
    @Autowired
    ManitoRepository manitoRepository;

    //@Autowired
    //UserRepository userRepository;

    ManitoInfo manito = new ManitoInfo();

    // @Autowired
    // ModelMapper modelMapper;
    private final ModelMapper modelMapper;

    public ManitoService2(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void insertUser(Model model, ManitoInfoDTO manitoInfoDTO, String idx){
        UserInfo ui = UserInfo.builder()
            .kakaoId("")
            .name("")
            .userId("")
            .build();
        //return userRepository.save(ui).getManitoIdx();
    }

    public ManitoInfoDTO getManitoInfo(Model model, String idx){
        ManitoInfo manitoInfo = manitoRepository.findBymanitoIdx(Integer.parseInt(idx)).orElseGet(() -> manito);
        return modelMapper.map(manitoInfo, ManitoInfoDTO.class);
    }
}
