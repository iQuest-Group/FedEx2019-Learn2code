package com.iquestgroup.l2c.core;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface RegistrableService {

	String owner() default "no owner";
	
	String description() default "";
	
	String version() default "0.1";

	Feature feature() default Feature.DEFAULT;
}
