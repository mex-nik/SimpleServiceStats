package mx.challenge.simpleservicestats.aspect;

import lombok.extern.slf4j.Slf4j;
import mx.challenge.simpleservicestats.data.input.Request;
import mx.challenge.simpleservicestats.service.HourlyStatsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Mladen Nikolic <mladen.nikolic.mex@gmail.com>
 * https://www.linkedin.com/in/mladen-nikolic-mex/
 * @created 10.09.2021
 * @project SimpleServiceStats
 */

@Slf4j
@Aspect
@Configuration
public class CountRequestsAspect {

    private final HourlyStatsService hourlyStatsService;

    @Autowired
    public CountRequestsAspect(HourlyStatsService hourlyStatsService) {
        this.hourlyStatsService = hourlyStatsService;
    }


    @Pointcut("@annotation(CountValid)")
    public void countValid(){}

    @Pointcut("@annotation(CountInvalid)")
    public void countInvalid(){}

    @AfterReturning(value = "countValid()", returning = "obj")
    public void countValidRequest(JoinPoint joinPoint, Object obj) {
        if (obj instanceof Request) {
            Request request = (Request) obj;
            hourlyStatsService.incrementValid(request.getCustomerID(), request.getTimestamp());
        }
    }

    @AfterReturning(value = "countInvalid()", returning = "obj")
    public void countInvalidRequest(JoinPoint joinPoint, Object obj) {
        // Potential issue with malformed request: Missing customer ID and/or time stamp.
        Optional<MethodArgumentNotValidException> methodArgumentNotValidExceptionOptional = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof MethodArgumentNotValidException).map(objArg -> (MethodArgumentNotValidException) objArg).findFirst();
        if (methodArgumentNotValidExceptionOptional.isPresent()) {
            MethodArgumentNotValidException methodArgumentNotValidException = methodArgumentNotValidExceptionOptional.get();
            Object target = methodArgumentNotValidException.getBindingResult().getTarget();
            if (target instanceof Request) {
                Request request = (Request) target;
                if (request.getCustomerID() != null) {
                    hourlyStatsService.incrementInvalid(request.getCustomerID(), request.getTimestamp());
                }
            }
        }
    }
}
