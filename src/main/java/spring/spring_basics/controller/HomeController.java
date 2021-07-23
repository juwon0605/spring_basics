package spring.spring_basics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 우선순위: 스프링 컨테이너 > index.html
    public String home(){
        return "home";
    }
}
