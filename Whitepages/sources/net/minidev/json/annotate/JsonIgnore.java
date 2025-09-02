package net.minidev.json.annotate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JsonIgnore {
    boolean value() default true;
}
