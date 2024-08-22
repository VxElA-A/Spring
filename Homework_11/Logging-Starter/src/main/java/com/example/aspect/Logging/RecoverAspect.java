package com.example.aspect.Logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class RecoverAspect {

    private final RecoverProperties recoverProperties;


//    private final RecoverProperties recoverProperties;
//
//    public RecoverAspect(RecoverProperties recoverProperties) {
//        this.recoverProperties = recoverProperties;
//    }

    @Around("@annotation(recover)")
    public Object handleRecovery(ProceedingJoinPoint joinPoint, Recover recover) {
        try {
            return joinPoint.proceed();
        } catch (Throwable ex) {
            for (Class<?> noRecoverFor : recover.noRecoverFor()) {
                if (noRecoverFor.isAssignableFrom(ex.getClass())) {
                    throw new RuntimeException(ex);
                }
            }

            log.error("Recovering {}#{} after Exception[{}: \"{}\"]",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    ex.getClass().getSimpleName(),
                    ex.getMessage());

            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Class<?> returnType = methodSignature.getReturnType();
            return getDefaultValue(returnType);
        }
    }

    private Object getDefaultValue(Class<?> returnType) {
        if (returnType.isPrimitive()) {
            if (returnType == boolean.class) return false;
            if (returnType == char.class) return '\0';
            if (returnType == byte.class || returnType == short.class || returnType == int.class) return 0;
            if (returnType == long.class) return 0L;
            if (returnType == float.class) return 0.0f;
            if (returnType == double.class) return 0.0;
        }
        return null;
    }
}
