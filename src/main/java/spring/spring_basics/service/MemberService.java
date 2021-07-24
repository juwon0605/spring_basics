package spring.spring_basics.service;
// service는 네이밍을 비즈니스 용어에 가깝게
// repository는 네이밍을 작동을 명확하게

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.spring_basics.domain.Member;
import spring.spring_basics.repository.MemberRepository;
import spring.spring_basics.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional // JPA에서 DB data 저장 변경하기 위해 필요(JPA는 @Trnasactional안에서 실행되게 설계)
//@Service // @Service 명시해야 스프링이 컨테이너에 스프링 빈으로 Service 리소스 관리(컴포넌트 스캔과 자동 의존관계 설정)(DI: dependency injection)
public class MemberService {
//    new()를 통해 사실상 다른 테스트 코드와 매번 다른 리소스 DB(Memory)를 사용하는 거니 아래와 같이 작업
//    모든 컨트롤러 클래스에서 동일한 리소스 한 개만 사용하도록 아래와 같이 작업(싱글톤 등록)
/*
    private final MemberRepository memberRepository = new MemoryMemberRepository();
*/
    //    객체 생성후 외부에서 메모리 할당(의존성 주입)
    private final MemberRepository memberRepository;
    @Autowired  // 스프링이 컨테이너안 스프링 빈에 있는 리소스를 생성자로 연결(의존성 주입)(DI: dependency injection)
    public MemberService(MemberRepository memberRepository){ // 의존성 주입-1.생성자 주입 방법
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
//       이름이 같은 중복 회원은 가입을 하면 안 된다고 가정
/*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { // ifPresent: Optional 객체에 값이 있으면 실행하는 함수
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
*/
        // 아래와 같이 객체 생성하지 않고 바로 이어서 함수 작성할 수 있음
/*
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // ifPresent: Optional 객체에 값이 있으면 실행하는 함수
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
*/
        // Extract Method(Ctrl+Alt+M)으로 리팩토링
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // ifPresent: Optional 객체에 값이 있으면 실행하는 함수
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
