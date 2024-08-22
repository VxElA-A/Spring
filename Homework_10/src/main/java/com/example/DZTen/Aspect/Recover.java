package com.example.DZTen.Aspect;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Recover { // recover - восстанавливать

    Class<?>[] noRecoverFor() default {};
}
