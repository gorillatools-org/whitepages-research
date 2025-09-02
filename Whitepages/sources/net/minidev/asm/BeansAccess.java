package net.minidev.asm;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BeansAccess {
    private static ConcurrentHashMap cache = new ConcurrentHashMap();

    public static BeansAccess get(Class cls, FieldFilter fieldFilter) {
        String str;
        Class<?> cls2;
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(cache.get(cls));
        Accessor[] accessors = ASMUtil.getAccessors(cls, fieldFilter);
        String name = cls.getName();
        if (name.startsWith("java.util.")) {
            str = "net.minidev.asm." + name + "AccAccess";
        } else {
            str = name.concat("AccAccess");
        }
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(cls.getClassLoader());
        try {
            cls2 = dynamicClassLoader.loadClass(str);
        } catch (ClassNotFoundException unused) {
            cls2 = null;
        }
        LinkedList parents = getParents(cls);
        if (cls2 == null) {
            BeansAccessBuilder beansAccessBuilder = new BeansAccessBuilder(cls, accessors, dynamicClassLoader);
            Iterator it = parents.iterator();
            while (it.hasNext()) {
                beansAccessBuilder.addConversion((Iterable) BeansAccessConfig.classMapper.get((Class) it.next()));
            }
            cls2 = beansAccessBuilder.bulid();
        }
        try {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(cls2.newInstance());
            throw null;
        } catch (Exception e) {
            throw new RuntimeException("Error constructing accessor class: " + str, e);
        }
    }

    private static LinkedList getParents(Class cls) {
        Class<Object> cls2;
        LinkedList linkedList = new LinkedList();
        while (true) {
            cls2 = Object.class;
            if (cls == null || cls.equals(cls2)) {
                linkedList.addLast(cls2);
            } else {
                linkedList.addLast(cls);
                for (Class addLast : cls.getInterfaces()) {
                    linkedList.addLast(addLast);
                }
                cls = cls.getSuperclass();
            }
        }
        linkedList.addLast(cls2);
        return linkedList;
    }
}
