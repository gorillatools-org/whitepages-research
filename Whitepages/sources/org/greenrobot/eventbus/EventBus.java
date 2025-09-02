package org.greenrobot.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import org.greenrobot.eventbus.android.AndroidDependenciesDetector;

public class EventBus {
    private static final EventBusBuilder DEFAULT_BUILDER = new EventBusBuilder();
    private static final Map eventTypesCache = new HashMap();
    private final AsyncPoster asyncPoster;
    private final BackgroundPoster backgroundPoster;
    private final ThreadLocal currentPostingThreadState = new ThreadLocal() {
        /* access modifiers changed from: protected */
        public PostingThreadState initialValue() {
            return new PostingThreadState();
        }
    };
    private final boolean eventInheritance;
    private final ExecutorService executorService;
    private final int indexCount;
    private final boolean logNoSubscriberMessages;
    private final boolean logSubscriberExceptions;
    private final Logger logger;
    private final Poster mainThreadPoster;
    private final MainThreadSupport mainThreadSupport;
    private final boolean sendNoSubscriberEvent;
    private final boolean sendSubscriberExceptionEvent;
    private final Map stickyEvents;
    private final SubscriberMethodFinder subscriberMethodFinder;
    private final Map subscriptionsByEventType;
    private final boolean throwSubscriberException;
    private final Map typesBySubscriber;

    public static EventBusBuilder builder() {
        return new EventBusBuilder();
    }

    EventBus(EventBusBuilder eventBusBuilder) {
        this.logger = eventBusBuilder.getLogger();
        this.subscriptionsByEventType = new HashMap();
        this.typesBySubscriber = new HashMap();
        this.stickyEvents = new ConcurrentHashMap();
        MainThreadSupport mainThreadSupport2 = eventBusBuilder.getMainThreadSupport();
        this.mainThreadSupport = mainThreadSupport2;
        this.mainThreadPoster = mainThreadSupport2 != null ? mainThreadSupport2.createPoster(this) : null;
        this.backgroundPoster = new BackgroundPoster(this);
        this.asyncPoster = new AsyncPoster(this);
        List list = eventBusBuilder.subscriberInfoIndexes;
        this.indexCount = list != null ? list.size() : 0;
        this.subscriberMethodFinder = new SubscriberMethodFinder(eventBusBuilder.subscriberInfoIndexes, eventBusBuilder.strictMethodVerification, eventBusBuilder.ignoreGeneratedIndex);
        this.logSubscriberExceptions = eventBusBuilder.logSubscriberExceptions;
        this.logNoSubscriberMessages = eventBusBuilder.logNoSubscriberMessages;
        this.sendSubscriberExceptionEvent = eventBusBuilder.sendSubscriberExceptionEvent;
        this.sendNoSubscriberEvent = eventBusBuilder.sendNoSubscriberEvent;
        this.throwSubscriberException = eventBusBuilder.throwSubscriberException;
        this.eventInheritance = eventBusBuilder.eventInheritance;
        this.executorService = eventBusBuilder.executorService;
    }

    public void register(Object obj) {
        if (!AndroidDependenciesDetector.isAndroidSDKAvailable() || AndroidDependenciesDetector.areAndroidComponentsAvailable()) {
            List<SubscriberMethod> findSubscriberMethods = this.subscriberMethodFinder.findSubscriberMethods(obj.getClass());
            synchronized (this) {
                try {
                    for (SubscriberMethod subscribe : findSubscriberMethods) {
                        subscribe(obj, subscribe);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        throw new RuntimeException("It looks like you are using EventBus on Android, make sure to add the \"eventbus\" Android library to your dependencies.");
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod) {
        Class cls = subscriberMethod.eventType;
        Subscription subscription = new Subscription(obj, subscriberMethod);
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.subscriptionsByEventType.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.subscriptionsByEventType.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(subscription)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList.size();
        int i = 0;
        while (true) {
            if (i > size) {
                break;
            } else if (i == size || subscriberMethod.priority > ((Subscription) copyOnWriteArrayList.get(i)).subscriberMethod.priority) {
                copyOnWriteArrayList.add(i, subscription);
            } else {
                i++;
            }
        }
        copyOnWriteArrayList.add(i, subscription);
        List list = (List) this.typesBySubscriber.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.typesBySubscriber.put(obj, list);
        }
        list.add(cls);
        if (!subscriberMethod.sticky) {
            return;
        }
        if (this.eventInheritance) {
            for (Map.Entry entry : this.stickyEvents.entrySet()) {
                if (cls.isAssignableFrom((Class) entry.getKey())) {
                    checkPostStickyEventToSubscription(subscription, entry.getValue());
                }
            }
            return;
        }
        checkPostStickyEventToSubscription(subscription, this.stickyEvents.get(cls));
    }

    private void checkPostStickyEventToSubscription(Subscription subscription, Object obj) {
        if (obj != null) {
            postToSubscription(subscription, obj, isMainThread());
        }
    }

    private boolean isMainThread() {
        MainThreadSupport mainThreadSupport2 = this.mainThreadSupport;
        return mainThreadSupport2 == null || mainThreadSupport2.isMainThread();
    }

    public void post(Object obj) {
        PostingThreadState postingThreadState = (PostingThreadState) this.currentPostingThreadState.get();
        List list = postingThreadState.eventQueue;
        list.add(obj);
        if (!postingThreadState.isPosting) {
            postingThreadState.isMainThread = isMainThread();
            postingThreadState.isPosting = true;
            if (!postingThreadState.canceled) {
                while (!list.isEmpty()) {
                    try {
                        postSingleEvent(list.remove(0), postingThreadState);
                    } finally {
                        postingThreadState.isPosting = false;
                        postingThreadState.isMainThread = false;
                    }
                }
                return;
            }
            throw new EventBusException("Internal error. Abort state was not reset");
        }
    }

    public void postSticky(Object obj) {
        synchronized (this.stickyEvents) {
            this.stickyEvents.put(obj.getClass(), obj);
        }
        post(obj);
    }

    public Object removeStickyEvent(Class cls) {
        Object cast;
        synchronized (this.stickyEvents) {
            cast = cls.cast(this.stickyEvents.remove(cls));
        }
        return cast;
    }

    private void postSingleEvent(Object obj, PostingThreadState postingThreadState) {
        boolean z;
        Class<?> cls = obj.getClass();
        if (this.eventInheritance) {
            List lookupAllEventTypes = lookupAllEventTypes(cls);
            int size = lookupAllEventTypes.size();
            z = false;
            for (int i = 0; i < size; i++) {
                z |= postSingleEventForEventType(obj, postingThreadState, (Class) lookupAllEventTypes.get(i));
            }
        } else {
            z = postSingleEventForEventType(obj, postingThreadState, cls);
        }
        if (!z) {
            if (this.logNoSubscriberMessages) {
                this.logger.log(Level.FINE, "No subscribers registered for event " + cls);
            }
            if (this.sendNoSubscriberEvent && cls != NoSubscriberEvent.class && cls != SubscriberExceptionEvent.class) {
                post(new NoSubscriberEvent(this, obj));
            }
        }
    }

    private boolean postSingleEventForEventType(Object obj, PostingThreadState postingThreadState, Class cls) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = (CopyOnWriteArrayList) this.subscriptionsByEventType.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            Subscription subscription = (Subscription) it.next();
            postingThreadState.event = obj;
            postingThreadState.subscription = subscription;
            try {
                postToSubscription(subscription, obj, postingThreadState.isMainThread);
                if (postingThreadState.canceled) {
                    return true;
                }
            } finally {
                postingThreadState.event = null;
                postingThreadState.subscription = null;
                postingThreadState.canceled = false;
            }
        }
        return true;
    }

    /* renamed from: org.greenrobot.eventbus.EventBus$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$greenrobot$eventbus$ThreadMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.greenrobot.eventbus.ThreadMode[] r0 = org.greenrobot.eventbus.ThreadMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$greenrobot$eventbus$ThreadMode = r0
                org.greenrobot.eventbus.ThreadMode r1 = org.greenrobot.eventbus.ThreadMode.POSTING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$greenrobot$eventbus$ThreadMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.greenrobot.eventbus.ThreadMode r1 = org.greenrobot.eventbus.ThreadMode.MAIN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$greenrobot$eventbus$ThreadMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.greenrobot.eventbus.ThreadMode r1 = org.greenrobot.eventbus.ThreadMode.MAIN_ORDERED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$greenrobot$eventbus$ThreadMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.greenrobot.eventbus.ThreadMode r1 = org.greenrobot.eventbus.ThreadMode.BACKGROUND     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$greenrobot$eventbus$ThreadMode     // Catch:{ NoSuchFieldError -> 0x003e }
                org.greenrobot.eventbus.ThreadMode r1 = org.greenrobot.eventbus.ThreadMode.ASYNC     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.eventbus.EventBus.AnonymousClass2.<clinit>():void");
        }
    }

    private void postToSubscription(Subscription subscription, Object obj, boolean z) {
        int i = AnonymousClass2.$SwitchMap$org$greenrobot$eventbus$ThreadMode[subscription.subscriberMethod.threadMode.ordinal()];
        if (i == 1) {
            invokeSubscriber(subscription, obj);
        } else if (i != 2) {
            if (i == 3) {
                Poster poster = this.mainThreadPoster;
                if (poster != null) {
                    poster.enqueue(subscription, obj);
                } else {
                    invokeSubscriber(subscription, obj);
                }
            } else if (i != 4) {
                if (i == 5) {
                    this.asyncPoster.enqueue(subscription, obj);
                    return;
                }
                throw new IllegalStateException("Unknown thread mode: " + subscription.subscriberMethod.threadMode);
            } else if (z) {
                this.backgroundPoster.enqueue(subscription, obj);
            } else {
                invokeSubscriber(subscription, obj);
            }
        } else if (z) {
            invokeSubscriber(subscription, obj);
        } else {
            this.mainThreadPoster.enqueue(subscription, obj);
        }
    }

    private static List lookupAllEventTypes(Class cls) {
        List list;
        Map map = eventTypesCache;
        synchronized (map) {
            try {
                list = (List) map.get(cls);
                if (list == null) {
                    list = new ArrayList();
                    for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                        list.add(cls2);
                        addInterfaces(list, cls2.getInterfaces());
                    }
                    eventTypesCache.put(cls, list);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return list;
    }

    static void addInterfaces(List list, Class[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                addInterfaces(list, cls.getInterfaces());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void invokeSubscriber(PendingPost pendingPost) {
        Object obj = pendingPost.event;
        Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        if (subscription.active) {
            invokeSubscriber(subscription, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void invokeSubscriber(Subscription subscription, Object obj) {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, new Object[]{obj});
        } catch (InvocationTargetException e) {
            handleSubscriberException(subscription, obj, e.getCause());
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    private void handleSubscriberException(Subscription subscription, Object obj, Throwable th) {
        if (obj instanceof SubscriberExceptionEvent) {
            if (this.logSubscriberExceptions) {
                Logger logger2 = this.logger;
                Level level = Level.SEVERE;
                logger2.log(level, "SubscriberExceptionEvent subscriber " + subscription.subscriber.getClass() + " threw an exception", th);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                Logger logger3 = this.logger;
                logger3.log(level, "Initial event " + subscriberExceptionEvent.causingEvent + " caused exception in " + subscriberExceptionEvent.causingSubscriber, subscriberExceptionEvent.throwable);
            }
        } else if (!this.throwSubscriberException) {
            if (this.logSubscriberExceptions) {
                Logger logger4 = this.logger;
                Level level2 = Level.SEVERE;
                logger4.log(level2, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + subscription.subscriber.getClass(), th);
            }
            if (this.sendSubscriberExceptionEvent) {
                post(new SubscriberExceptionEvent(this, th, obj, subscription.subscriber));
            }
        } else {
            throw new EventBusException("Invoking subscriber failed", th);
        }
    }

    static final class PostingThreadState {
        boolean canceled;
        Object event;
        final List eventQueue = new ArrayList();
        boolean isMainThread;
        boolean isPosting;
        Subscription subscription;

        PostingThreadState() {
        }
    }

    /* access modifiers changed from: package-private */
    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public String toString() {
        return "EventBus[indexCount=" + this.indexCount + ", eventInheritance=" + this.eventInheritance + "]";
    }
}
