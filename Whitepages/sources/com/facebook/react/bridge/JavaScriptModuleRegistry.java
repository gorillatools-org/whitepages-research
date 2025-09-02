package com.facebook.react.bridge;

import com.facebook.react.common.build.ReactBuildConfig;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;

public final class JavaScriptModuleRegistry {
    private final HashMap<Class<? extends JavaScriptModule>, JavaScriptModule> mModuleInstances = new HashMap<>();

    public synchronized <T extends JavaScriptModule> T getJavaScriptModule(CatalystInstance catalystInstance, Class<T> cls) {
        T t = (JavaScriptModule) this.mModuleInstances.get(cls);
        if (t != null) {
            return t;
        }
        T t2 = (JavaScriptModule) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new JavaScriptModuleInvocationHandler(catalystInstance, cls));
        this.mModuleInstances.put(cls, t2);
        return t2;
    }

    private static class JavaScriptModuleInvocationHandler implements InvocationHandler {
        private final CatalystInstance mCatalystInstance;
        private final Class<? extends JavaScriptModule> mModuleInterface;
        private String mName;

        public JavaScriptModuleInvocationHandler(CatalystInstance catalystInstance, Class<? extends JavaScriptModule> cls) {
            this.mCatalystInstance = catalystInstance;
            this.mModuleInterface = cls;
            if (ReactBuildConfig.DEBUG) {
                HashSet hashSet = new HashSet();
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                int i = 0;
                while (i < length) {
                    Method method = declaredMethods[i];
                    if (hashSet.add(method.getName())) {
                        i++;
                    } else {
                        throw new AssertionError("Method overloading is unsupported: " + this.mModuleInterface.getName() + "#" + method.getName());
                    }
                }
            }
        }

        private String getJSModuleName() {
            if (this.mName == null) {
                this.mName = JavaScriptModuleRegistry.getJSModuleName(this.mModuleInterface);
            }
            return this.mName;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            this.mCatalystInstance.callFunction(getJSModuleName(), method.getName(), objArr != null ? Arguments.fromJavaArgs(objArr) : new WritableNativeArray());
            return null;
        }
    }

    public static String getJSModuleName(Class<? extends JavaScriptModule> cls) {
        String simpleName = cls.getSimpleName();
        int lastIndexOf = simpleName.lastIndexOf(36);
        return lastIndexOf != -1 ? simpleName.substring(lastIndexOf + 1) : simpleName;
    }
}
