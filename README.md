# spring_basics


## **⦁ 개요**

​	-간단한 예제를 통해 스프링 부트, 웹 MVC, DB 접근 기술, 테스트 코드 작성, AOP 학습

## **⦁ 구현 영상 (24초)**

![spring_basics 시연 영상](https://user-images.githubusercontent.com/58173061/126873461-e4f25d35-dc34-4028-9350-440bcb8545e1.gif)

## **⦁ 기술 스택**

1. **스프링 부트**

   - spring initializr을 통해 프로젝트 환경설정 세팅

2. **웹 MVC**

   - Controller, Service, Repository, Domain 계층 구조로 개발
   - 공통: @Controller, @Service, @Repository, @Autowired를 통해 컴포넌트 스캔과 자동 의존관계 방법으로 DI(Dependency Injection)
   - Controller: @GetMapping과 @PostMapping을 통해 렌더링
   - Service: 핵심 비즈니스 로직(회원 가입, 조회) 구현 
   - Repository: 인터페이스(MemberRepository)와 스프링 빈 수동 등록(SpringConfig)를 통해 다양한 DB 접근 기술 적용
   - Domain: 비즈니스 도메인 객체(Member) 구현  

3. **DB 접근 기술**

     - 간단한 학습을 위한 가볍고 편리한 H2 데이터베이스 설치
     - 인터페이스(MemberRepository)와 스프링 빈 수동 등록(SpringConfig)를 통해 다양한 DB 접근 기술 적용
     - Interface: MemberRepository
     - Jdbc: JdbcMemberRepository
     - JdbcTemlplate: JdbcTemplateMemberRepository
     - JPA: JpaMemberRepository
     - 스프링 데이터 JPA: SpringDataJpaMemberRepository

4. **테스트 코드 작성**

     - @Test와 순수 자바 코드로 구현된 단위 테스트: MemberServiceTest
     - @SpringBootTest를 추가해 스프링 컨테이너 실행까지 테스트: MemberServiceIntegrationTest

5. **AOP**

     - AOP를 통해 모든 메소드 실행 시간 측정: TimeTraceAop
