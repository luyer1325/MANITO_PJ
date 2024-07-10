package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.service.ManitoService;



@Controller
public class ManageController {

    @Autowired
    ManitoService manitoService;

    @RequestMapping("/ljh.do")
    public String getMethodName(Model model, ManitoInfoDTO manitoInfoDTO) {
        System.out.println("여기 : 여기2");
        return "thymeleaf/ljh/main";
    }
    
    @RequestMapping("/ljh2.do")
    public String getMethodName2(Model model, ManitoInfoDTO manitoInfoDTO) {
        String test = "abc";
        System.out.println(test);
        return "mng/invite";
    }

    @PostMapping("/thym-ljh.do")
    public String thym(Model model, ManitoInfoDTO manitoInfoDTO){
        System.out.println("타임리프");
        return "thymeleaf/ljh/invite";
    }

    @RequestMapping("/thym-invite.do")
    public String invite(Model model, ManitoInfoDTO manitoInfoDTO){
        //manitoInfoDTO.setJoin_yn("Y");
        return "thymeleaf/ljh/invite";
    }

    @RequestMapping("/thym-invite2.do")
    public String invite2(Model model, ManitoInfoDTO manitoInfoDTO){
        manitoService.CreateManito(manitoInfoDTO); // 게임 생성
        //return "thymeleaf/ljh/invite";
        return "thymeleaf/ljh/gameDetail";
    }
    
    @RequestMapping("/kakao/login.do")
    public String kakaoLogin(Model model, ManitoInfoDTO manitoInfoDTO){
        manitoService.test(model);
        return "thymeleaf/ljh/invite";
    }
}