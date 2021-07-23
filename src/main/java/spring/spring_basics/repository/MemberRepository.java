package spring.spring_basics.repository;

import spring.spring_basics.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // null이 return될 시 Optional로 감싸서 return
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
