package com.leeds.manito.manito_pj;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaintoController {
    @RequestMapping("/wel-jsp.do")
    public String main() {
        return "index";
    }

    @RequestMapping("/")
    public String main(Model model) throws Exception {
        model.addAttribute("greeting", "hello");
        return "thymeleaf/index";
    }

}
