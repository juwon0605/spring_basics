package spring.spring_basics.repository;
// 일반적인 웹 애플리케이션 계층 구조
// 컨트롤러 -> 서비스 -> 리포지토리 -> DB
//    |         v         |
//    |_>     도메인     <_|

// 컨트롤러: 웹 MVC의 컨트롤러 역할
// 서비스: 핵심 비즈니스 로직 구현
// 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
// 도메인: 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

import org.springframework.stereotype.Repository;
import spring.spring_basics.domain.Member;

import java.lang.reflect.Array;
import java.util.*;

@Repository // @Repository를 명시해야 스프링이 컨테이너에 스프링 빈으로 Repository 리소스 관리(컴포넌트 스캔과 자동 의존관계 설정)
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put((member.getId()), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear(); // Test 코드에서 각 테스트 함수 이후 객체를 초기화 하기 위한 메서드
    }
}
