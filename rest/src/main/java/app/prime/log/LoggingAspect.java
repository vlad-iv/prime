package app.prime.log;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Logging api & services call.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
@Component
@Log4j2
@Aspect
public class LoggingAspect {
    @Around("execution(* *(..)) && @annotation(app.prime.Logged)")
    public Object logAroundLoggedMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Call: {}.{}({})", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        return joinPoint.proceed();
    }
}
