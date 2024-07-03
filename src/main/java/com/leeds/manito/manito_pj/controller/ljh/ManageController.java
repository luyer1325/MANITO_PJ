package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestParam;

import com.leeds.manito.manito_pj.dto.ljh.RoomInfoDTO;


@Controller
public class ManageController {
    @GetMapping("/ljh.do")
    public String getMethodName(Model model, RoomInfoDTO roomInfoDTO) {
        System.out.println("여기 : 여기2");
        return "mng/invite";
    }
    
    @GetMapping("/ljh2.do")
    public String getMethodName2(Model model, RoomInfoDTO roomInfoDTO) {
        //String test = model.getAttribute("abc");
        String test = "abc";
        System.out.println(test);
        return "mng/invite";
    }
}
