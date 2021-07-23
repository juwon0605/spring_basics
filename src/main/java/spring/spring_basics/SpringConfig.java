package spring.spring_basics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.spring_basics.repository.MemberRepository;
import spring.spring_basics.repository.MemoryMemberRepository;
import spring.spring_basics.service.MemberService;

// 수동으로 스프링 컨테이너에 스프링 빈을 등록하는 방법
// 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
// 그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다

// @Service와 @Repository 대신 아래와 같이 사용 가능
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

}
