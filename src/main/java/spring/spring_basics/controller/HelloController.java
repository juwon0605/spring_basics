package spring.spring_basics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 내장 서버인 톰캣에 URL이 오면 @Controller의 @GetMapping에 매핑 되는 지 탐색
public class HelloController {

   @GetMapping("hello") // 주소/hello에 매핑
    public String hello(Model model){ // 매핑이 되면 스프링에서 model을 파라미터로 넘겨줌
       model.addAttribute("data", "hello!!"); // 넘겨받은 파라미터의 속성 설정
       return "hello"; // -> viewResolver가 resources/templates/hello.html에 rendering
   }

   @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ //주소/hello-mvc?name="input"/
       model.addAttribute("name",name);
       return "hello-template";
   }

   @GetMapping("hello-string")
   @ResponseBody // html body에 직접 data return
                 // viewResolver를 통해 내려가지 않음!
                 // 이런식으로는 쓰지 않음. 아래와 같이 객체로 return
    public String helloString(@RequestParam("name") String name){
       return "hello " + name;  // HttpMessageConverter가 StringConverter 동작
   }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
       Hello hello = new Hello();
       hello.setName(name);
       return hello;    // 객체를 return하면 json 방식으로 return {"name": ""}
                        // HttpMessageConverter가 JsonConverter 동작
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
