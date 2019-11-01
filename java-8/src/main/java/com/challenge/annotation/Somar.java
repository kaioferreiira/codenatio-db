package com.challenge.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
public @interface Somar {

    String value();

}
