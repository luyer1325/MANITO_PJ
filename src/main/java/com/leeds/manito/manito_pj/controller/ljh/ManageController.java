package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leeds.manito.manito_pj.dto.ManitoInfoDTO;


@Controller
public class ManageController {
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
}
