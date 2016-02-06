package it.bela.market.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FieldSelector {

	String value();
	String attribute() default "";
	int elementIndex() default 0;
	String javaType() default "String";
	String pattern() default ".*";
	int groupIndex() default 0;
}
