package com.iquestgroup.l2c.core;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Retention(RUNTIME)
@Target(TYPE)
@Inherited
public @interface InheritedComponent {

	Component component() default @Component;
}
