package spring.spring_basics.domain;
// 일반적인 웹 애플리케이션 계층 구조
// 컨트롤러 -> 서비스 -> 리포지토리 -> DB
//    |         v         |
//    |_>     도메인     <_|

// 컨트롤러: 웹 MVC의 컨트롤러 역할
// 서비스: 핵심 비즈니스 로직 구현
// 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
// 도메인: 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

public class Member {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}