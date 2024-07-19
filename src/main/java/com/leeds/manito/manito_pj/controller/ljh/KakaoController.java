package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.service.KakaoService;


@Controller
public class KakaoController {
    
    @Autowired
    KakaoService kakaoservice;

    @RequestMapping("/kakao/login.do")
    public String kakaoLogin(Model model, ManitoInfoDTO manitoInfoDTO,@RequestParam String code){
        String accessToken = code;
        System.out.println("어세스코드는 : "+accessToken);
        kakaoservice.test(model);
        kakaoservice.getAccessToken(code);
        System.out.println("현재 여기");
        return "thymeleaf/ljh/invite";
    }
    
}
