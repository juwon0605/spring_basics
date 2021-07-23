package spring.spring_basics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.spring_basics.domain.Member;
import spring.spring_basics.service.MemberService;

@Controller // @Controller 명시해야 스프링이 컨테이너에 스프링 빈으로 Controller 리소스 관리(컴포넌트 스캔과 자동 의존관계 설정)(DI: dependency injection)
public class MemberController {

    //모든 컨트롤러 클래스에서 동일한 리소스 한 개만 사용하도록 아래와 같이 작업(싱글톤 등록)
    /*
    private final MemberService memberService = new MemberService();
    */
    private final MemberService memberService;
    // 의존성 주입-1.생성자 주입 방법(권장)
    // 장점: 빌드시 한 번 사용 이후 호출 불가
    @Autowired  // 스프링이 컨테이너안 스프링 빈에 있는 리소스를 생성자로 연결(의존성 주입)(DI: dependency injection)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 의존성 주입-2.필드 주입 방법
    // 단점: 수정 변경할 수 없음
    /*
    @Autowired private final MemberService memberService;
    */

    // 의존성 주입-3.setter 주입 방법
    // 단점: 빌드시 한 번 사용 이후 public하게 노출되어 있음(import시 불필요한 상황에서도 호출 가능)
    /*
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    */

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){  // Spring이 html에서 post 방식으로 넘어온 form을 MemberForm으로 맞춰서 넣어줌
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

}
