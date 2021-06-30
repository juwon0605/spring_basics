package spring.spring_basics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 내장 서버인 톰캣에 URL이 오면 @Controller의 @GetMapping에 매핑 되는 지 탐색
public class HelloController {

   @GetMapping("hello") // 주소/hello에 매핑
    public String hello(Model model){ // 매핑이 되면 스프링에서 model을 파라미터로 넘겨줌
       model.addAttribute("data", "hello!!"); // 넘겨받은 파라미터의 속성 설정
       return "hello"; // viewResolver가 resources/templates/hello.html에 rendering
   }
}
