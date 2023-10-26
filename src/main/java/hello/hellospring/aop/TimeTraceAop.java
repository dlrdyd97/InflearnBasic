package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 적어줘야 AOP로 사용 가능
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring.service..*(..))") // 어디에 적용?
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();
        System.out.println("START : " +joinPoint.toString());
        try {
            return joinPoint.proceed(); // Ctrl + Alt + Shift + T = 인라인
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("END : "+joinPoint.toString()+" "+timeMs+"ms");
        }

    }

}
