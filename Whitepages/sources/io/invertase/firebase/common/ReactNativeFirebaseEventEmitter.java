package io.invertase.firebase.common;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import io.invertase.firebase.interfaces.NativeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReactNativeFirebaseEventEmitter {
    private static ReactNativeFirebaseEventEmitter sharedInstance = new ReactNativeFirebaseEventEmitter();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private int jsListenerCount;
    private final HashMap<String, Integer> jsListeners = new HashMap<>();
    private Boolean jsReady = Boolean.FALSE;
    private final List<NativeEvent> queuedEvents = new ArrayList();
    private ReactContext reactContext;

    public static ReactNativeFirebaseEventEmitter getSharedInstance() {
        return sharedInstance;
    }

    public void attachReactContext(ReactContext reactContext2) {
        this.handler.post(new ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda1(this, reactContext2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$attachReactContext$0(ReactContext reactContext2) {
        this.reactContext = reactContext2;
        sendQueuedEvents();
    }

    public void notifyJsReady(Boolean bool) {
        this.handler.post(new ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda0(this, bool));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyJsReady$1(Boolean bool) {
        this.jsReady = bool;
        sendQueuedEvents();
    }

    public void sendEvent(NativeEvent nativeEvent) {
        this.handler.post(new ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda3(this, nativeEvent));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendEvent$2(NativeEvent nativeEvent) {
        synchronized (this.jsListeners) {
            try {
                if (this.jsListeners.containsKey(nativeEvent.getEventName())) {
                    if (!emit(nativeEvent)) {
                    }
                }
                this.queuedEvents.add(nativeEvent);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addListener(String str) {
        synchronized (this.jsListeners) {
            try {
                this.jsListenerCount++;
                if (!this.jsListeners.containsKey(str)) {
                    this.jsListeners.put(str, 1);
                } else {
                    this.jsListeners.put(str, Integer.valueOf(this.jsListeners.get(str).intValue() + 1));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.handler.post(new ReactNativeFirebaseEventEmitter$$ExternalSyntheticLambda2(this));
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003c A[Catch:{ all -> 0x002d }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003d A[Catch:{ all -> 0x002d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeListener(java.lang.String r6, java.lang.Boolean r7) {
        /*
            r5 = this;
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r5.jsListeners
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, java.lang.Integer> r1 = r5.jsListeners     // Catch:{ all -> 0x002d }
            boolean r1 = r1.containsKey(r6)     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x0041
            java.util.HashMap<java.lang.String, java.lang.Integer> r1 = r5.jsListeners     // Catch:{ all -> 0x002d }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x002d }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x002d }
            int r1 = r1.intValue()     // Catch:{ all -> 0x002d }
            r2 = 1
            if (r1 <= r2) goto L_0x002f
            boolean r3 = r7.booleanValue()     // Catch:{ all -> 0x002d }
            if (r3 == 0) goto L_0x0021
            goto L_0x002f
        L_0x0021:
            java.util.HashMap<java.lang.String, java.lang.Integer> r3 = r5.jsListeners     // Catch:{ all -> 0x002d }
            int r4 = r1 + -1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x002d }
            r3.put(r6, r4)     // Catch:{ all -> 0x002d }
            goto L_0x0034
        L_0x002d:
            r6 = move-exception
            goto L_0x0043
        L_0x002f:
            java.util.HashMap<java.lang.String, java.lang.Integer> r3 = r5.jsListeners     // Catch:{ all -> 0x002d }
            r3.remove(r6)     // Catch:{ all -> 0x002d }
        L_0x0034:
            int r6 = r5.jsListenerCount     // Catch:{ all -> 0x002d }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x002d }
            if (r7 == 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r1 = r2
        L_0x003e:
            int r6 = r6 - r1
            r5.jsListenerCount = r6     // Catch:{ all -> 0x002d }
        L_0x0041:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x0043:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.common.ReactNativeFirebaseEventEmitter.removeListener(java.lang.String, java.lang.Boolean):void");
    }

    public WritableMap getListenersMap() {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap.putInt("listeners", this.jsListenerCount);
        createMap.putInt("queued", this.queuedEvents.size());
        synchronized (this.jsListeners) {
            try {
                for (Map.Entry next : this.jsListeners.entrySet()) {
                    createMap2.putInt((String) next.getKey(), ((Integer) next.getValue()).intValue());
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        createMap.putMap("events", createMap2);
        return createMap;
    }

    /* access modifiers changed from: private */
    public void sendQueuedEvents() {
        synchronized (this.jsListeners) {
            try {
                Iterator it = new ArrayList(this.queuedEvents).iterator();
                while (it.hasNext()) {
                    NativeEvent nativeEvent = (NativeEvent) it.next();
                    if (this.jsListeners.containsKey(nativeEvent.getEventName())) {
                        this.queuedEvents.remove(nativeEvent);
                        sendEvent(nativeEvent);
                    }
                }
            } finally {
            }
        }
    }

    private boolean emit(NativeEvent nativeEvent) {
        ReactContext reactContext2;
        if (this.jsReady.booleanValue() && (reactContext2 = this.reactContext) != null && reactContext2.hasActiveCatalystInstance()) {
            try {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("rnfb_" + nativeEvent.getEventName(), nativeEvent.getEventBody());
                return true;
            } catch (Exception e) {
                Log.wtf("RNFB_EMITTER", "Error sending Event " + nativeEvent.getEventName(), e);
            }
        }
        return false;
    }
}
