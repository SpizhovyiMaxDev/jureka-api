package studio.maxdev.jureka_api.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {
    private Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return type, class name, method ame, args
    @Before("execution(* *.*(..))")
    public void logMethodCall(){
        System.out.println("Method called");
    }
}
