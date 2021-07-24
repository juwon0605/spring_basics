package spring.spring_basics.repository;

import org.springframework.data.jpa.repository.JpaRepository;   // 스프링 데이터 JPA가 제공하는 라이브러리(공통 인터페이스)
import spring.spring_basics.domain.Member;

import java.util.Optional;

// 스프링 데이터 JPA가 interface를 자동으로 구현체를 만들어서 스프링 빈에 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    @Override
    Optional<Member> findByName(String name);
    // select m from Member m where m.name = ?
    // 함수의 네이밍으로 자동 JPQl 생성
}
