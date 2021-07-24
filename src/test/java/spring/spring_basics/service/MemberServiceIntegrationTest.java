package spring.spring_basics.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import spring.spring_basics.domain.Member;
import spring.spring_basics.repository.MemberRepository;
import spring.spring_basics.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 단순 java코드로 작동만 검증하는게 아니라 스프링 컨테이너 실행까지 테스트
@Transactional  // 테스트코드에 사용하면 테스트코드 함수마다 db clear을 위한 작업 수행(테스트마다 시작 전으로 롤백)
class MemberServiceIntegrationTest {
    
/*
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
*/
//    매번 리소스 새로 할당
/*
@BeforeEach
public void beforeEach(){
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
}
*/
    
//    new()를 통해 사실상 다른 테스트 코드와 매번 다른 리소스 DB(Memory)를 사용하는 것이니 아래와 같이 작업
//    모든 컨트롤러 클래스에서 동일한 리소스 한 개만 사용하도록 아래와 같이 작업(스프링 컨테이너 스프링 빈 싱글톤 등록)
    // 테스트 리소스는 테스트 하고 끝나니 편한 방법으로 작성
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

//    @Transactional이 아래 작업을 자동으로 수행
/*
//    테스트코드 함수마다 객체 clear을 위한 작업
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
*/

    @Test
//    @Commit 이렇게하면 DB Test 결과를 저장할 수도 있음
//    테스트 코드는 실제 빌드되는 코드에 반영되지 않아서 다국적으로 일하는게 아니면 한글로 작성할 수도 있음
    void 회원가입() {
        //1.given: 이러한 상황이 주어지면
        Member member = new Member();
        member.setName("spring");

        //2.when: 이것을 실행했을때
        Long saveId = memberService.join(member);

        //3.then: 이러한 결과가 나와야한다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

//    테스트 코드는 정상 작동일 때가 아니라 예외 상황에서의 검증이 훨씬 더 중요함!
    @Test
    public void 중복_회원_예외(){
        //1.given: 이러한 상황이 주어지면
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //2.when: 이것을 실행했을때
        memberService.join(member1);
/*
        try{
            memberService.join(member2); // 중복 이름으로 가입 시도해서 throw 발생
            fail(); // catch 되지 않으면 fail();
        } catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
//        try-catch문을 쓰지 않고 아래와 같이 throw catch
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 이 함수를 실행하면 파라미터 타입의 예외 발생 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //3.then: 이러한 결과가 나와야한다

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}