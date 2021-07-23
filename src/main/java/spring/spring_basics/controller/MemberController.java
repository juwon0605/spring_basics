package spring.spring_basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring.spring_basics.service.MemberService;

@Controller // @Controller 명시해야 스프링이 컨테이너에 스프링 빈으로 Controller 리소스 관리(컴포넌트 스캔과 자동 의존관계 설정)
public class MemberController {

    //모든 컨트롤러 클래스에서 동일한 리소스 한 개만 사용하도록 아래와 같이 작업(싱글톤 등록)
    /*
    private final MemberService memberService = new MemberService();
    */
    private final MemberService memberService;
    @Autowired  // 스프링이 컨테이너안 스프링 빈에 있는 리소스를 생성자로 연결(의존성 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
