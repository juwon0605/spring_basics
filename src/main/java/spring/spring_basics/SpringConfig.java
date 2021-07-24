package spring.spring_basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.spring_basics.repository.*;
import spring.spring_basics.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.xml.crypto.Data;

// 수동으로 스프링 컨테이너에 스프링 빈을 등록하는 방법
// 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
// 그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다

// @Service와 @Repository 대신 아래와 같이 사용 가능
@Configuration
public class SpringConfig {

    // Memory, Jdbc 의존성 주입
/*
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/
    // JPA 의존성 주입
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
//    개방-폐쇄 원칙(OCP, Open-Closed Principle)
//    기능 확장에는 열려있고, 수정.변경에는 닫혀있다.
//    스프링의 DI(Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현클래스를 변경할 수 있다.
    public MemberRepository memberRepository(){ // MemberRepository는 implement로 구현하여 아래와 같이 다형성 활용
//        return new MemoryMemberRepository();  // implement 구현체를 MemoryMemberRepository로 구현
//        return new JdbcMemberRepository(dataSource); // implement 구현체를 JdbcMemberRepository로 구현
//        return new JdbcTemplateMemberRepository(dataSource); // implement 구현체를 JdbcTemplateMemberRepository로 구현
        return new JpaMemberRepository(em); // implement 구현체를 JdbcTemplateMemberRepository로 구현
    }

}
