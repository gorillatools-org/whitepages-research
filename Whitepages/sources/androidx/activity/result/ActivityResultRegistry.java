package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public abstract class ActivityResultRegistry {
    final transient Map mKeyToCallback = new HashMap();
    private final Map mKeyToLifecycleContainers = new HashMap();
    final Map mKeyToRc = new HashMap();
    ArrayList mLaunchedKeys = new ArrayList();
    final Map mParsedPendingResults = new HashMap();
    final Bundle mPendingResults = new Bundle();
    private Random mRandom = new Random();
    private final Map mRcToKey = new HashMap();

    public abstract void onLaunch(int i, ActivityResultContract activityResultContract, Object obj, ActivityOptionsCompat activityOptionsCompat);

    public final ActivityResultLauncher register(final String str, LifecycleOwner lifecycleOwner, final ActivityResultContract activityResultContract, final ActivityResultCallback activityResultCallback) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (!lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            registerKey(str);
            LifecycleContainer lifecycleContainer = (LifecycleContainer) this.mKeyToLifecycleContainers.get(str);
            if (lifecycleContainer == null) {
                lifecycleContainer = new LifecycleContainer(lifecycle);
            }
            lifecycleContainer.addObserver(new LifecycleEventObserver() {
                public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    if (Lifecycle.Event.ON_START.equals(event)) {
                        ActivityResultRegistry.this.mKeyToCallback.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
                        if (ActivityResultRegistry.this.mParsedPendingResults.containsKey(str)) {
                            Object obj = ActivityResultRegistry.this.mParsedPendingResults.get(str);
                            ActivityResultRegistry.this.mParsedPendingResults.remove(str);
                            activityResultCallback.onActivityResult(obj);
                        }
                        ActivityResult activityResult = (ActivityResult) ActivityResultRegistry.this.mPendingResults.getParcelable(str);
                        if (activityResult != null) {
                            ActivityResultRegistry.this.mPendingResults.remove(str);
                            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
                        }
                    } else if (Lifecycle.Event.ON_STOP.equals(event)) {
                        ActivityResultRegistry.this.mKeyToCallback.remove(str);
                    } else if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                        ActivityResultRegistry.this.unregister(str);
                    }
                }
            });
            this.mKeyToLifecycleContainers.put(str, lifecycleContainer);
            return new ActivityResultLauncher() {
                public void launch(Object obj, ActivityOptionsCompat activityOptionsCompat) {
                    Integer num = (Integer) ActivityResultRegistry.this.mKeyToRc.get(str);
                    if (num != null) {
                        ActivityResultRegistry.this.mLaunchedKeys.add(str);
                        try {
                            ActivityResultRegistry.this.onLaunch(num.intValue(), activityResultContract, obj, activityOptionsCompat);
                        } catch (Exception e) {
                            ActivityResultRegistry.this.mLaunchedKeys.remove(str);
                            throw e;
                        }
                    } else {
                        throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
                    }
                }

                public void unregister() {
                    ActivityResultRegistry.this.unregister(str);
                }
            };
        }
        throw new IllegalStateException("LifecycleOwner " + lifecycleOwner + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.");
    }

    public final ActivityResultLauncher register(final String str, final ActivityResultContract activityResultContract, ActivityResultCallback activityResultCallback) {
        registerKey(str);
        this.mKeyToCallback.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
        if (this.mParsedPendingResults.containsKey(str)) {
            Object obj = this.mParsedPendingResults.get(str);
            this.mParsedPendingResults.remove(str);
            activityResultCallback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.mPendingResults.getParcelable(str);
        if (activityResult != null) {
            this.mPendingResults.remove(str);
            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
        }
        return new ActivityResultLauncher() {
            public void launch(Object obj, ActivityOptionsCompat activityOptionsCompat) {
                Integer num = (Integer) ActivityResultRegistry.this.mKeyToRc.get(str);
                if (num != null) {
                    ActivityResultRegistry.this.mLaunchedKeys.add(str);
                    try {
                        ActivityResultRegistry.this.onLaunch(num.intValue(), activityResultContract, obj, activityOptionsCompat);
                    } catch (Exception e) {
                        ActivityResultRegistry.this.mLaunchedKeys.remove(str);
                        throw e;
                    }
                } else {
                    throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
                }
            }

            public void unregister() {
                ActivityResultRegistry.this.unregister(str);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public final void unregister(String str) {
        Integer num;
        if (!this.mLaunchedKeys.contains(str) && (num = (Integer) this.mKeyToRc.remove(str)) != null) {
            this.mRcToKey.remove(num);
        }
        this.mKeyToCallback.remove(str);
        if (this.mParsedPendingResults.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.mParsedPendingResults.get(str));
            this.mParsedPendingResults.remove(str);
        }
        if (this.mPendingResults.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.mPendingResults.getParcelable(str));
            this.mPendingResults.remove(str);
        }
        LifecycleContainer lifecycleContainer = (LifecycleContainer) this.mKeyToLifecycleContainers.get(str);
        if (lifecycleContainer != null) {
            lifecycleContainer.clearObservers();
            this.mKeyToLifecycleContainers.remove(str);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList(this.mKeyToRc.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList(this.mKeyToRc.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList(this.mLaunchedKeys));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.mPendingResults.clone());
        bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", this.mRandom);
    }

    public final void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList != null && integerArrayList != null) {
                this.mLaunchedKeys = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
                this.mRandom = (Random) bundle.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
                this.mPendingResults.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
                for (int i = 0; i < stringArrayList.size(); i++) {
                    String str = stringArrayList.get(i);
                    if (this.mKeyToRc.containsKey(str)) {
                        Integer num = (Integer) this.mKeyToRc.remove(str);
                        if (!this.mPendingResults.containsKey(str)) {
                            this.mRcToKey.remove(num);
                        }
                    }
                    bindRcKey(integerArrayList.get(i).intValue(), stringArrayList.get(i));
                }
            }
        }
    }

    public final boolean dispatchResult(int i, int i2, Intent intent) {
        String str = (String) this.mRcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        doDispatch(str, i2, intent, (CallbackAndContract) this.mKeyToCallback.get(str));
        return true;
    }

    public final boolean dispatchResult(int i, Object obj) {
        ActivityResultCallback activityResultCallback;
        String str = (String) this.mRcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        CallbackAndContract callbackAndContract = (CallbackAndContract) this.mKeyToCallback.get(str);
        if (callbackAndContract == null || (activityResultCallback = callbackAndContract.mCallback) == null) {
            this.mPendingResults.remove(str);
            this.mParsedPendingResults.put(str, obj);
            return true;
        } else if (!this.mLaunchedKeys.remove(str)) {
            return true;
        } else {
            activityResultCallback.onActivityResult(obj);
            return true;
        }
    }

    private void doDispatch(String str, int i, Intent intent, CallbackAndContract callbackAndContract) {
        if (callbackAndContract == null || callbackAndContract.mCallback == null || !this.mLaunchedKeys.contains(str)) {
            this.mParsedPendingResults.remove(str);
            this.mPendingResults.putParcelable(str, new ActivityResult(i, intent));
            return;
        }
        callbackAndContract.mCallback.onActivityResult(callbackAndContract.mContract.parseResult(i, intent));
        this.mLaunchedKeys.remove(str);
    }

    private void registerKey(String str) {
        if (((Integer) this.mKeyToRc.get(str)) == null) {
            bindRcKey(generateRandomNumber(), str);
        }
    }

    private int generateRandomNumber() {
        int nextInt = this.mRandom.nextInt(2147418112);
        while (true) {
            int i = nextInt + 65536;
            if (!this.mRcToKey.containsKey(Integer.valueOf(i))) {
                return i;
            }
            nextInt = this.mRandom.nextInt(2147418112);
        }
    }

    private void bindRcKey(int i, String str) {
        this.mRcToKey.put(Integer.valueOf(i), str);
        this.mKeyToRc.put(str, Integer.valueOf(i));
    }

    private static class CallbackAndContract {
        final ActivityResultCallback mCallback;
        final ActivityResultContract mContract;

        CallbackAndContract(ActivityResultCallback activityResultCallback, ActivityResultContract activityResultContract) {
            this.mCallback = activityResultCallback;
            this.mContract = activityResultContract;
        }
    }

    private static class LifecycleContainer {
        final Lifecycle mLifecycle;
        private final ArrayList mObservers = new ArrayList();

        LifecycleContainer(Lifecycle lifecycle) {
            this.mLifecycle = lifecycle;
        }

        /* access modifiers changed from: package-private */
        public void addObserver(LifecycleEventObserver lifecycleEventObserver) {
            this.mLifecycle.addObserver(lifecycleEventObserver);
            this.mObservers.add(lifecycleEventObserver);
        }

        /* access modifiers changed from: package-private */
        public void clearObservers() {
            Iterator it = this.mObservers.iterator();
            while (it.hasNext()) {
                this.mLifecycle.removeObserver((LifecycleEventObserver) it.next());
            }
            this.mObservers.clear();
        }
    }
}
