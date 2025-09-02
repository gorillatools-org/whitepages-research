package net.minidev.asm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface FieldFilter {
    boolean canUse(Field field, Method method);
}
