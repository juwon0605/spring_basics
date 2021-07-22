package spring.spring_basics.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.spring_basics.domain.Member;
import spring.spring_basics.repository.MemberRepository;
import spring.spring_basics.repository.MemoryMemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    ??? new()를 통해 사실상 다른 테스트 코드와 매번 다른 DB(Memory)를 사용하는 거니 아래와 같이 작업(?)
//    ??? DB(Memory)와 연동된 객체로 테스트 하기 위해(?)
/*
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
*/
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

//    테스트코드 함수마다 객체 clear을 위한 작업
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }



    @Test
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