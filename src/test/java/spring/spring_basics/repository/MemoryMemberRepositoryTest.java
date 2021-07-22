package spring.spring_basics.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import spring.spring_basics.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // @Test 함수가 하나 끝날때 마다 실행되는 콜백 메서드
               // 사용하는 객체를 초기화하는 용도로 사용
               // 만약 사용하는 객체가 초기화되지 않으면 랜덤으로 실행되는 @Test 함수중에 에러가 발생할 수 있음
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional타입에서 .get()으로 값을 꺼내기
//        Assertions.assertEquals(result, member); // Junit에서 제공하는 두 값의 비교 함수 -> 실행 결과 확인
//                                                 // Test에서 save()한 객체(member)와 DB(Memory)에서 save()한 객체(result)가 같으면 정상 동작 확인
//        //overwriting 함수
//        Assertions.assertThat(member).isEqualTo(result);    // DB(Memory)에서 save()한 객체(result)와 Test에서 save()한 객체(member)가 같으면 정상 동작 확인
//      static import하면 아래와 같이 사용 가능
        assertThat(member).isEqualTo(result);    // DB(Memory)에서 save()한 객체(result)와 Test에서 save()한 객체(member)가 같으면 정상 동작 확인
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1); // 정상 동작 확인
//        assertThat(result).isEqualTo(member2); // 에러 확인
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); // 정상 동작 확인
//        assertThat(result.size()).isEqualTo(3); // 에러 확인
    }
}
