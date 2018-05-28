package com.technique.jersey.configs;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.google.inject.BindingAnnotation;
import java.lang.annotation.Retention;

@Retention(RUNTIME)
@BindingAnnotation
public @interface ClubOSResource {

  String value() default "default";
}

