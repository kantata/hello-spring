package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //AOP (Interceptor) : 공통사항을 AOP로 빼서 처리
@Component //SpringConfig.java timeTraceAop() 메서드 호출과 동일 이라는데 해당 설정으로 하면 에러남..
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 실행되는 조건 : * 클래스명, (..) 파라미터 타입
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); //어떤메서드를 콜하는지 찍음
        try {
            Object result = joinPoint.proceed(); // 다음메서드로 진행
            return  result;
        } finally {
            long finishTime = System.currentTimeMillis();
            long durationTime = finishTime - startTime;
            System.out.println("END: " + joinPoint.toString() + " " + durationTime + "ms"); //어떤메서드를 콜하는지 찍음
        }
    }
}
