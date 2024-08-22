package com.example.DZNine.Aspect;


public @interface Timer {

        boolean enabled() default true;

        org.slf4j.event.Level level() default org.slf4j.event.Level.INFO; // INFO, DEBUG, ERROR , TRACE Level.DEBUG

}
