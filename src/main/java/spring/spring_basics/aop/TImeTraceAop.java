package spring.spring_basics.aop;
//회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
//시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
//핵심 관심 사항을 깔끔하게 유지할 수 있다.
//변경이 필요하면 이 로직만 변경하면 된다.
//원하는 적용 대상을 선택할 수 있다.

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP 사용(프록시 기술)
@Component // AOP 함수 스프링 빈에 등록
public class TImeTraceAop {

    @Around("execution(* spring.spring_basics..*(..))") // 적용 범위 설정(모두 적용)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            /*
            Object result = joinPoint.proceed();
            return result;
            */
            // 위와 같은 표현(inline 방식 적용)
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
