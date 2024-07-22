package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.service.KakaoService;

import java.util.HashMap;


@Controller
public class KakaoController {
    
    @Autowired
    KakaoService kakaoservice;

    @RequestMapping("/kakao/login.do")
    public String kakaoLogin(Model model, ManitoInfoDTO manitoInfoDTO,@RequestParam String code){
        String accessToken = code;
        System.out.println("어세스코드는 : "+accessToken);
        kakaoservice.test(model);
        accessToken = kakaoservice.getAccessToken(code);
        kakaoservice.getUserInfo(model,accessToken);
        model.addAttribute("at",accessToken);
        System.out.println("테스트nick : "+model.getAttribute("nickname"));
        System.out.println("테스트email : "+model.getAttribute("email"));
        System.out.println("현재 여기");
        return "thymeleaf/ljh/invite";
    }
    @RequestMapping("/kakao/logout.do")
    public String kakaoLogout(Model model, ManitoInfoDTO manitoInfoDTO){
        System.out.println("at : "+model.getAttribute("at"));
        return "";
    }
}
