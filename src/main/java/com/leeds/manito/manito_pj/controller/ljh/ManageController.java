package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.service.KakaoService;
import com.leeds.manito.manito_pj.service.ManitoService;



@Controller
public class ManageController {

    @Autowired
    ManitoService manitoService;
    @Autowired
    KakaoService kakaoService;

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
        kakaoService.test(model);
        return "thymeleaf/ljh/invite";
    }

    @RequestMapping("/thym-invite2.do")
    public String invite2(Model model, ManitoInfoDTO manitoInfoDTO, @ModelAttribute("at")String at){
        System.out.println("여기는 : " + at);
        manitoService.CreateManito(manitoInfoDTO); // 게임 생성
        kakaoService.test(model);
        model.addAttribute("logout_redirect_uri", "http://localhost:8080/kakao/logout.do");
        return "thymeleaf/ljh/gameDetail";
    }
    

}