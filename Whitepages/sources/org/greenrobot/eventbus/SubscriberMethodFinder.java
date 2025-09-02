package org.greenrobot.eventbus;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.meta.SubscriberInfo;

class SubscriberMethodFinder {
    private static final FindState[] FIND_STATE_POOL = new FindState[4];
    private static final Map METHOD_CACHE = new ConcurrentHashMap();
    private final boolean ignoreGeneratedIndex;
    private final boolean strictMethodVerification;
    private List subscriberInfoIndexes;

    SubscriberMethodFinder(List list, boolean z, boolean z2) {
        this.subscriberInfoIndexes = list;
        this.strictMethodVerification = z;
        this.ignoreGeneratedIndex = z2;
    }

    /* access modifiers changed from: package-private */
    public List findSubscriberMethods(Class cls) {
        List list;
        Map map = METHOD_CACHE;
        List list2 = (List) map.get(cls);
        if (list2 != null) {
            return list2;
        }
        if (this.ignoreGeneratedIndex) {
            list = findUsingReflection(cls);
        } else {
            list = findUsingInfo(cls);
        }
        if (!list.isEmpty()) {
            map.put(cls, list);
            return list;
        }
        throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
    }

    private List findUsingInfo(Class cls) {
        FindState prepareFindState = prepareFindState();
        prepareFindState.initForSubscriber(cls);
        while (prepareFindState.clazz != null) {
            getSubscriberInfo(prepareFindState);
            findUsingReflectionInSingleClass(prepareFindState);
            prepareFindState.moveToSuperclass();
        }
        return getMethodsAndRelease(prepareFindState);
    }

    private List getMethodsAndRelease(FindState findState) {
        ArrayList arrayList = new ArrayList(findState.subscriberMethods);
        findState.recycle();
        synchronized (FIND_STATE_POOL) {
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                }
                try {
                    FindState[] findStateArr = FIND_STATE_POOL;
                    if (findStateArr[i] == null) {
                        findStateArr[i] = findState;
                        break;
                    }
                    i++;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return arrayList;
    }

    private FindState prepareFindState() {
        synchronized (FIND_STATE_POOL) {
            int i = 0;
            while (i < 4) {
                try {
                    FindState[] findStateArr = FIND_STATE_POOL;
                    FindState findState = findStateArr[i];
                    if (findState != null) {
                        findStateArr[i] = null;
                        return findState;
                    }
                    i++;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            return new FindState();
        }
    }

    private SubscriberInfo getSubscriberInfo(FindState findState) {
        findState.getClass();
        List list = this.subscriberInfoIndexes;
        if (list != null) {
            Iterator it = list.iterator();
            if (it.hasNext()) {
                MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
                throw null;
            }
        }
        return null;
    }

    private List findUsingReflection(Class cls) {
        FindState prepareFindState = prepareFindState();
        prepareFindState.initForSubscriber(cls);
        while (prepareFindState.clazz != null) {
            findUsingReflectionInSingleClass(prepareFindState);
            prepareFindState.moveToSuperclass();
        }
        return getMethodsAndRelease(prepareFindState);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ea, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00eb, code lost:
        r15 = "Could not inspect methods of " + r15.clazz.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0104, code lost:
        if (r14.ignoreGeneratedIndex != false) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0106, code lost:
        r15 = r15 + ". Please consider using EventBus annotation processor to avoid reflection.";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0118, code lost:
        r15 = r15 + ". Please make this class visible to EventBus annotation processor to avoid reflection.";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x012e, code lost:
        throw new org.greenrobot.eventbus.EventBusException(r15, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        r1 = r15.clazz.getMethods();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
        r15.skipSuperClasses = true;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0008 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void findUsingReflectionInSingleClass(org.greenrobot.eventbus.SubscriberMethodFinder.FindState r15) {
        /*
            r14 = this;
            r0 = 1
            java.lang.Class r1 = r15.clazz     // Catch:{ all -> 0x0008 }
            java.lang.reflect.Method[] r1 = r1.getDeclaredMethods()     // Catch:{ all -> 0x0008 }
            goto L_0x0010
        L_0x0008:
            java.lang.Class r1 = r15.clazz     // Catch:{ LinkageError -> 0x00ea }
            java.lang.reflect.Method[] r1 = r1.getMethods()     // Catch:{ LinkageError -> 0x00ea }
            r15.skipSuperClasses = r0
        L_0x0010:
            int r2 = r1.length
            r3 = 0
            r4 = r3
        L_0x0013:
            if (r4 >= r2) goto L_0x00e9
            r6 = r1[r4]
            int r5 = r6.getModifiers()
            r7 = r5 & 1
            java.lang.String r8 = "."
            java.lang.Class<org.greenrobot.eventbus.Subscribe> r9 = org.greenrobot.eventbus.Subscribe.class
            if (r7 == 0) goto L_0x00a5
            r5 = r5 & 5192(0x1448, float:7.276E-42)
            if (r5 != 0) goto L_0x00a5
            java.lang.Class[] r5 = r6.getParameterTypes()
            int r7 = r5.length
            if (r7 != r0) goto L_0x005b
            java.lang.annotation.Annotation r7 = r6.getAnnotation(r9)
            org.greenrobot.eventbus.Subscribe r7 = (org.greenrobot.eventbus.Subscribe) r7
            if (r7 == 0) goto L_0x00e5
            r8 = r5[r3]
            boolean r5 = r15.checkAdd(r6, r8)
            if (r5 == 0) goto L_0x00e5
            org.greenrobot.eventbus.ThreadMode r9 = r7.threadMode()
            java.util.List r11 = r15.subscriberMethods
            org.greenrobot.eventbus.SubscriberMethod r12 = new org.greenrobot.eventbus.SubscriberMethod
            int r10 = r7.priority()
            boolean r13 = r7.sticky()
            r5 = r12
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r13
            r5.<init>(r6, r7, r8, r9, r10)
            r11.add(r12)
            goto L_0x00e5
        L_0x005b:
            boolean r7 = r14.strictMethodVerification
            if (r7 == 0) goto L_0x00e5
            boolean r7 = r6.isAnnotationPresent(r9)
            if (r7 != 0) goto L_0x0067
            goto L_0x00e5
        L_0x0067:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.Class r0 = r6.getDeclaringClass()
            java.lang.String r0 = r0.getName()
            r15.append(r0)
            r15.append(r8)
            java.lang.String r0 = r6.getName()
            r15.append(r0)
            java.lang.String r15 = r15.toString()
            org.greenrobot.eventbus.EventBusException r0 = new org.greenrobot.eventbus.EventBusException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "@Subscribe method "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r15 = "must have exactly 1 parameter but has "
            r1.append(r15)
            int r15 = r5.length
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            r0.<init>(r15)
            throw r0
        L_0x00a5:
            boolean r5 = r14.strictMethodVerification
            if (r5 == 0) goto L_0x00e5
            boolean r5 = r6.isAnnotationPresent(r9)
            if (r5 != 0) goto L_0x00b0
            goto L_0x00e5
        L_0x00b0:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.Class r0 = r6.getDeclaringClass()
            java.lang.String r0 = r0.getName()
            r15.append(r0)
            r15.append(r8)
            java.lang.String r0 = r6.getName()
            r15.append(r0)
            java.lang.String r15 = r15.toString()
            org.greenrobot.eventbus.EventBusException r0 = new org.greenrobot.eventbus.EventBusException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r15)
            java.lang.String r15 = " is a illegal @Subscribe method: must be public, non-static, and non-abstract"
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            r0.<init>(r15)
            throw r0
        L_0x00e5:
            int r4 = r4 + 1
            goto L_0x0013
        L_0x00e9:
            return
        L_0x00ea:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Could not inspect methods of "
            r1.append(r2)
            java.lang.Class r15 = r15.clazz
            java.lang.String r15 = r15.getName()
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            boolean r1 = r14.ignoreGeneratedIndex
            if (r1 == 0) goto L_0x0118
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r15)
            java.lang.String r15 = ". Please consider using EventBus annotation processor to avoid reflection."
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            goto L_0x0129
        L_0x0118:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r15)
            java.lang.String r15 = ". Please make this class visible to EventBus annotation processor to avoid reflection."
            r1.append(r15)
            java.lang.String r15 = r1.toString()
        L_0x0129:
            org.greenrobot.eventbus.EventBusException r1 = new org.greenrobot.eventbus.EventBusException
            r1.<init>(r15, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.eventbus.SubscriberMethodFinder.findUsingReflectionInSingleClass(org.greenrobot.eventbus.SubscriberMethodFinder$FindState):void");
    }

    static class FindState {
        final Map anyMethodByEventType = new HashMap();
        Class clazz;
        final StringBuilder methodKeyBuilder = new StringBuilder(128);
        boolean skipSuperClasses;
        Class subscriberClass;
        final Map subscriberClassByMethodKey = new HashMap();
        final List subscriberMethods = new ArrayList();

        FindState() {
        }

        /* access modifiers changed from: package-private */
        public void initForSubscriber(Class cls) {
            this.clazz = cls;
            this.subscriberClass = cls;
            this.skipSuperClasses = false;
        }

        /* access modifiers changed from: package-private */
        public void recycle() {
            this.subscriberMethods.clear();
            this.anyMethodByEventType.clear();
            this.subscriberClassByMethodKey.clear();
            this.methodKeyBuilder.setLength(0);
            this.subscriberClass = null;
            this.clazz = null;
            this.skipSuperClasses = false;
        }

        /* access modifiers changed from: package-private */
        public boolean checkAdd(Method method, Class cls) {
            Object put = this.anyMethodByEventType.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (checkAddWithMethodSignature((Method) put, cls)) {
                    this.anyMethodByEventType.put(cls, this);
                } else {
                    throw new IllegalStateException();
                }
            }
            return checkAddWithMethodSignature(method, cls);
        }

        private boolean checkAddWithMethodSignature(Method method, Class cls) {
            this.methodKeyBuilder.setLength(0);
            this.methodKeyBuilder.append(method.getName());
            StringBuilder sb = this.methodKeyBuilder;
            sb.append('>');
            sb.append(cls.getName());
            String sb2 = this.methodKeyBuilder.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class cls2 = (Class) this.subscriberClassByMethodKey.put(sb2, declaringClass);
            if (cls2 == null || cls2.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.subscriberClassByMethodKey.put(sb2, cls2);
            return false;
        }

        /* access modifiers changed from: package-private */
        public void moveToSuperclass() {
            if (this.skipSuperClasses) {
                this.clazz = null;
                return;
            }
            Class superclass = this.clazz.getSuperclass();
            this.clazz = superclass;
            String name = superclass.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.") || name.startsWith("androidx.")) {
                this.clazz = null;
            }
        }
    }
}
