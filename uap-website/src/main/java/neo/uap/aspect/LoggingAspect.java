package neo.uap.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(* neo.uap..*(..))")
    public void methodPerformed() {
    }

    @Before("methodPerformed()")
    public void before(JoinPoint joinPoint) {
        log.info("Enter " + joinPoint.getSignature().toString());
    }

    @After("methodPerformed()")
    public void after(JoinPoint joinPoint) {
        log.info("Exit " + joinPoint.getSignature().toString());
    }
}
