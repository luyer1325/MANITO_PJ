package com.leeds.manito.manito_pj.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaintoController {
    @RequestMapping("/")
    public String main() {
        return "thymeleaf/index";
    }

    @RequestMapping("/wel2.do")
    public String main(Model model) throws Exception {
        model.addAttribute("greeting", "hello");
        return "thymeleaf/index";
    }

}
