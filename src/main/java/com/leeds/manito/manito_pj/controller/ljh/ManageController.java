package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;
import com.leeds.manito.manito_pj.service.ManitoService;

import jakarta.servlet.http.HttpSession;



@Controller
public class ManageController {

    @Autowired
    ManitoService manitoService;

    @RequestMapping("/thym-invite2.do")
    public String invite2(Model model, ManitoInfoDTO manitoInfoDTO, @ModelAttribute("at")String at){
        manitoService.CreateManito(manitoInfoDTO); // 게임 생성
        //kakaoService.getSettings(model);
        //model.addAttribute("logout_redirect_uri", "http://localhost:8080/kakao/logout.do");
        return "thymeleaf/ljh/gameDetail";
    }
    
    @PostMapping("/thym-ljh.do")
    public String thym(Model model, ManitoInfoDTO manitoInfoDTO){
        return "thymeleaf/ljh/invite";
    }

    @RequestMapping("/thym-invite.do")
    public String invite(Model model, ManitoInfoDTO manitoInfoDTO){
        model.addAttribute("kakao_redirect_uri", "http://localhost:8080/kakao/login2.do");
        return "thymeleaf/ljh/invite";
    }
    @RequestMapping("/thym-makeGame.do")
    public String makeGame(Model model, ManitoInfoDTO manitoInfoDTO){
        return "thymeleaf/ljh/makeGame";
    }
    @RequestMapping("/thym-checkLogin.do")
    public String checkLogin(Model model, ManitoInfoDTO manitoInfoDTO,HttpSession session,@RequestParam("email") String email){
        manitoInfoDTO.setCreateUser(email);
        manitoInfoDTO = manitoService.checkLogin(model,manitoInfoDTO);// 로그인 한 아이디에서 이미 생성된 게임이 있는지 체크하여 url 변경
        System.out.println("@@@@@"+manitoInfoDTO.getManitoIdx()+"@@@@@");
        System.out.println("@@@@@"+model.getAttribute("email")+"@@@@@");
        System.out.println("@@@@@"+manitoInfoDTO.getCreateUser()+"@@@@@");
        if(manitoInfoDTO.getCreateUser() == null || manitoInfoDTO.getCreateUser().trim().isEmpty()){
            model.addAttribute("rUrl","/thym-makeGame.do");
        }else {
            model.addAttribute("rUrl","/thym-start.do");
        }
        return "thymeleaf/ljh/kakao";
    }
    @RequestMapping("/thym-start.do")
    public String startGame(Model model, ManitoInfoDTO manitoInfoDTO){
        return "thymeleaf/ljh/start";
    }
    @RequestMapping("/thym-gameDetail.do")
    public String gameDetail(Model model, ManitoInfoDTO manitoInfoDTO, HttpSession session){
        manitoInfoDTO.setCreateUser((String)session.getAttribute("email"));
        manitoService.CreateManito(manitoInfoDTO);
        return "thymeleaf/ljh/gameDetail";
    }

    

}