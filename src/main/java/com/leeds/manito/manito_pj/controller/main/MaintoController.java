package com.leeds.manito.manito_pj.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.service.KakaoService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MaintoController {

    @Autowired
    KakaoService kakaoService;

    @RequestMapping("/")
    public String main() {
        return "thymeleaf/index";
    }

    @RequestMapping("/wel2.do")
    public String main(Model model) throws Exception {
        model.addAttribute("greeting", "hello");
        return "thymeleaf/index";
    }

    @RequestMapping("/main.do")
    public String main(Model model, ManitoInfoDTO manitoInfoDTO){
        kakaoService.getSettings(model);
        return "thymeleaf/main";
    }

    @RequestMapping("/invite.do")
    public String invite(Model model, ManitoInfoDTO manitoInfoDTO){
        return "thymeleaf/makeGame";
    }
    @RequestMapping("/detail.do")
    public String requestMethodName() {
        //@RequestParam String param
        return "thymeleaf/gameDetail";
    }
    

    @RequestMapping("/kakao/login.do")
    public String kakaoLogin(Model model,@RequestParam String code,HttpSession session){
        kakaoService.getSettings(model); // Key값 받아오는 서비스
        String accessToken = kakaoService.getAccessToken(session,code,model); // 허용토큰 받는 서비스
        kakaoService.getUserInfo(model,accessToken); //유저 정보 받는 서비스
        model.addAttribute("rUrl","/invite.do");// redirect Url - kakao.html에서 부모창 redirect url로  이동
        return "thymeleaf/ljh/kakao";
    }

    @RequestMapping("/kakao/logout.do")
    public String kakaoLogout(Model model, HttpSession session){
        kakaoService.kakaoLogout(model); //카카오 세션 및 계정 로그아웃 -> 메인페이지로 이동
        model.addAttribute("rUrl","/main.do");
        return "thymeleaf/ljh/kakao";
    }

}
