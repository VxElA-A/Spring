package com.example.DZEight.Aspect;


import com.example.DZEight.Model.Timesheet;
import com.example.DZEight.Service.TimesheetService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Slf4j // Slf4j -> Simple Logging Facade for Java
@Aspect
@Component
public class LoggingAspect {
    /*
     * @Before("execution(* com.example.DZEight.Service.*.*(..))")
     * After
     * AfterThrowing
     * AfterReturning = After + check if exception was thrown
     * Around -> before + after + afterThrowing + afterReturning
     *
     */

    @Pointcut("execution(* com.example.DZEight.Service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointcut() {
        log.info("TimesheetService pointcut");
    }

    @Before("execution(* com.example.DZEight.Service.TimesheetService.findById(Long))")
    public void beforeTimesheetServiceFindById(JoinPoint joinPoint) {
        logArguments(joinPoint);
    }

    // Pointcut - точка входа в аспест
//    @Before("execution(* com.example.DZEight.Service.*.*(..))")
//    @Before( value = "execution(* com.example.DZEight.Service.TimesheetService.findById(Long))")
//    public void beforeTimesheetServiceFindById(JoinPoint joinPoint) {
////        System.out.println("Before method: " + joinPoint.getSignature().getName()); // getSignature - метод, который вызывается
////        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs()));    // getArgs - аргументы метода
////        System.out.println("Target class: " + joinPoint.getTarget().getClass().getName()); // getTarget - класс, который вызывается
////
////        log.info("Before method: " + joinPoint.getSignature().getName()); // getSignature - метод, который вызывается
//
//        Long id = (Long) joinPoint.getArgs()[0];
//        log.info("timesheetServiceFindById: id = {} " , id);    // getArgs - аргументы метода
//    }

    @Before("timesheetServiceMethodsPointcut()")
    public void before(JoinPoint joinPoint) {
        logArguments(joinPoint);
    }

//    @Before( value = "timesheetServiceMethodsPointcut()") // execution - точка входа в аспест
//    public void before(JoinPoint joinPoint) {
//        log.info("Before method: {} ", joinPoint.getSignature().getName()); // getSignature - метод, который вызывается
//    }


    @After(value = "timesheetServiceMethodsPointcut()") // execution - точка входа в аспест
    public void after(JoinPoint joinPoint) {
        logArguments(joinPoint);
        log.info("After method: {} ", joinPoint.getSignature().getName()); // getSignature - метод, который вызывается
    }

    @AfterThrowing(value = "timesheetServiceMethodsPointcut()", throwing = "exception")
    // execution - точка входа в аспест
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        logArguments(joinPoint);
        log.info("AfterThrowing method: {} , Exception : {}", joinPoint.getSignature().getName(), exception.getClass().getName()); // getSignature - метод, который вызывается
    }

    @AfterReturning(value = "timesheetServiceMethodsPointcut()") // execution - точка входа в аспест
    public void afterReturning(JoinPoint joinPoint) {
        logArguments(joinPoint);
        log.info("AfterReturning method: {} ", joinPoint.getSignature().getName()); // getSignature - метод, который вызывается
    }


    private void logArguments(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder(methodName).append("(");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(args[i].getClass().getSimpleName()).append(" = ").append(args[i]);
        }
        sb.append(")");
        log.info(sb.toString());
    }

//    @Around( value = "timesheetServiceMethodsPointcut()") // execution - точка входа в аспест
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
////        log.info("Around method: {} ", proceedingJoinPoint.getSignature().getName()); // getSignature - метод, который вызывается
//        long startTime = System.currentTimeMillis();
//        try{
//            return   proceedingJoinPoint.proceed();
//        }finally {
//            log.info("Around method: {} , Time taken: {} ms", proceedingJoinPoint.getSignature().getName(), System.currentTimeMillis() - startTime); // getSignature - метод, который вызывается
//        }
//
//
////        Timesheet timesheet = new Timesheet();
////        timesheet.setTimesheetId(-100L);
////        return Optional.of(timesheet);
//
//    }
}
