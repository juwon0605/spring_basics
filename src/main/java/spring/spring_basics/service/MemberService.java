package spring.spring_basics.service;
// service는 네이밍을 비즈니스 용어에 가깝게
// repository는 네이밍을 작동을 명확하게

import spring.spring_basics.domain.Member;
import spring.spring_basics.repository.MemberRepository;
import spring.spring_basics.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member){
//       이름이 같은 중복 회원은 가입을 하면 안 된다고 가정
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { // ifPresent: Optional 객체에 값이 있으면 실행하는 함수
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 아래와 같이 객체 생성하지 않고 바로 이어서 함수 작성할 수 있음
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> { // ifPresent: Optional 객체에 값이 있으면 실행하는 함수
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });

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
