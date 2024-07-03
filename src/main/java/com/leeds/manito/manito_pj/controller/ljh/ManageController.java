package com.leeds.manito.manito_pj.controller.ljh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ManageController {
    @GetMapping("/ljh.do")
    public String getMethodName() {
        return "mng/invite";
    }
    
}
