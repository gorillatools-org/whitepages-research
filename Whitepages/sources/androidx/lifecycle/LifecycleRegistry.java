package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class LifecycleRegistry extends Lifecycle {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int addingObserverCounter;
    private final boolean enforceMainThread;
    private boolean handlingEvent;
    private final WeakReference lifecycleOwner;
    private boolean newEventOccurred;
    private FastSafeIterableMap observerMap;
    private ArrayList parentStates;
    private Lifecycle.State state;

    private LifecycleRegistry(LifecycleOwner lifecycleOwner2, boolean z) {
        this.enforceMainThread = z;
        this.observerMap = new FastSafeIterableMap();
        this.state = Lifecycle.State.INITIALIZED;
        this.parentStates = new ArrayList();
        this.lifecycleOwner = new WeakReference(lifecycleOwner2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LifecycleRegistry(LifecycleOwner lifecycleOwner2) {
        this(lifecycleOwner2, true);
        Intrinsics.checkNotNullParameter(lifecycleOwner2, "provider");
    }

    public Lifecycle.State getCurrentState() {
        return this.state;
    }

    public void setCurrentState(Lifecycle.State state2) {
        Intrinsics.checkNotNullParameter(state2, RemoteConfigConstants.ResponseFieldKey.STATE);
        enforceMainThreadIfNeeded("setCurrentState");
        moveToState(state2);
    }

    public void handleLifecycleEvent(Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(event, "event");
        enforceMainThreadIfNeeded("handleLifecycleEvent");
        moveToState(event.getTargetState());
    }

    private final void moveToState(Lifecycle.State state2) {
        Lifecycle.State state3 = this.state;
        if (state3 != state2) {
            if (state3 == Lifecycle.State.INITIALIZED && state2 == Lifecycle.State.DESTROYED) {
                throw new IllegalStateException(("no event down from " + this.state + " in component " + this.lifecycleOwner.get()).toString());
            }
            this.state = state2;
            if (this.handlingEvent || this.addingObserverCounter != 0) {
                this.newEventOccurred = true;
                return;
            }
            this.handlingEvent = true;
            sync();
            this.handlingEvent = false;
            if (this.state == Lifecycle.State.DESTROYED) {
                this.observerMap = new FastSafeIterableMap();
            }
        }
    }

    private final boolean isSynced() {
        if (this.observerMap.size() == 0) {
            return true;
        }
        Map.Entry eldest = this.observerMap.eldest();
        Intrinsics.checkNotNull(eldest);
        Lifecycle.State state2 = ((ObserverWithState) eldest.getValue()).getState();
        Map.Entry newest = this.observerMap.newest();
        Intrinsics.checkNotNull(newest);
        Lifecycle.State state3 = ((ObserverWithState) newest.getValue()).getState();
        if (state2 == state3 && this.state == state3) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r4 = (androidx.lifecycle.LifecycleRegistry.ObserverWithState) r4.getValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final androidx.lifecycle.Lifecycle.State calculateTargetState(androidx.lifecycle.LifecycleObserver r4) {
        /*
            r3 = this;
            androidx.arch.core.internal.FastSafeIterableMap r0 = r3.observerMap
            java.util.Map$Entry r4 = r0.ceil(r4)
            r0 = 0
            if (r4 == 0) goto L_0x0016
            java.lang.Object r4 = r4.getValue()
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r4 = (androidx.lifecycle.LifecycleRegistry.ObserverWithState) r4
            if (r4 == 0) goto L_0x0016
            androidx.lifecycle.Lifecycle$State r4 = r4.getState()
            goto L_0x0017
        L_0x0016:
            r4 = r0
        L_0x0017:
            java.util.ArrayList r1 = r3.parentStates
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x002d
            java.util.ArrayList r0 = r3.parentStates
            int r1 = r0.size()
            int r1 = r1 + -1
            java.lang.Object r0 = r0.get(r1)
            androidx.lifecycle.Lifecycle$State r0 = (androidx.lifecycle.Lifecycle.State) r0
        L_0x002d:
            androidx.lifecycle.LifecycleRegistry$Companion r1 = Companion
            androidx.lifecycle.Lifecycle$State r2 = r3.state
            androidx.lifecycle.Lifecycle$State r4 = r1.min$lifecycle_runtime_release(r2, r4)
            androidx.lifecycle.Lifecycle$State r4 = r1.min$lifecycle_runtime_release(r4, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.LifecycleRegistry.calculateTargetState(androidx.lifecycle.LifecycleObserver):androidx.lifecycle.Lifecycle$State");
    }

    public void addObserver(LifecycleObserver lifecycleObserver) {
        LifecycleOwner lifecycleOwner2;
        Intrinsics.checkNotNullParameter(lifecycleObserver, "observer");
        enforceMainThreadIfNeeded("addObserver");
        Lifecycle.State state2 = this.state;
        Lifecycle.State state3 = Lifecycle.State.DESTROYED;
        if (state2 != state3) {
            state3 = Lifecycle.State.INITIALIZED;
        }
        ObserverWithState observerWithState = new ObserverWithState(lifecycleObserver, state3);
        if (((ObserverWithState) this.observerMap.putIfAbsent(lifecycleObserver, observerWithState)) == null && (lifecycleOwner2 = (LifecycleOwner) this.lifecycleOwner.get()) != null) {
            boolean z = this.addingObserverCounter != 0 || this.handlingEvent;
            Lifecycle.State calculateTargetState = calculateTargetState(lifecycleObserver);
            this.addingObserverCounter++;
            while (observerWithState.getState().compareTo(calculateTargetState) < 0 && this.observerMap.contains(lifecycleObserver)) {
                pushParentState(observerWithState.getState());
                Lifecycle.Event upFrom = Lifecycle.Event.Companion.upFrom(observerWithState.getState());
                if (upFrom != null) {
                    observerWithState.dispatchEvent(lifecycleOwner2, upFrom);
                    popParentState();
                    calculateTargetState = calculateTargetState(lifecycleObserver);
                } else {
                    throw new IllegalStateException("no event up from " + observerWithState.getState());
                }
            }
            if (!z) {
                sync();
            }
            this.addingObserverCounter--;
        }
    }

    private final void popParentState() {
        ArrayList arrayList = this.parentStates;
        arrayList.remove(arrayList.size() - 1);
    }

    private final void pushParentState(Lifecycle.State state2) {
        this.parentStates.add(state2);
    }

    public void removeObserver(LifecycleObserver lifecycleObserver) {
        Intrinsics.checkNotNullParameter(lifecycleObserver, "observer");
        enforceMainThreadIfNeeded("removeObserver");
        this.observerMap.remove(lifecycleObserver);
    }

    private final void forwardPass(LifecycleOwner lifecycleOwner2) {
        SafeIterableMap.IteratorWithAdditions iteratorWithAdditions = this.observerMap.iteratorWithAdditions();
        Intrinsics.checkNotNullExpressionValue(iteratorWithAdditions, "observerMap.iteratorWithAdditions()");
        while (iteratorWithAdditions.hasNext() && !this.newEventOccurred) {
            Map.Entry entry = (Map.Entry) iteratorWithAdditions.next();
            LifecycleObserver lifecycleObserver = (LifecycleObserver) entry.getKey();
            ObserverWithState observerWithState = (ObserverWithState) entry.getValue();
            while (observerWithState.getState().compareTo(this.state) < 0 && !this.newEventOccurred && this.observerMap.contains(lifecycleObserver)) {
                pushParentState(observerWithState.getState());
                Lifecycle.Event upFrom = Lifecycle.Event.Companion.upFrom(observerWithState.getState());
                if (upFrom != null) {
                    observerWithState.dispatchEvent(lifecycleOwner2, upFrom);
                    popParentState();
                } else {
                    throw new IllegalStateException("no event up from " + observerWithState.getState());
                }
            }
        }
    }

    private final void backwardPass(LifecycleOwner lifecycleOwner2) {
        Iterator descendingIterator = this.observerMap.descendingIterator();
        Intrinsics.checkNotNullExpressionValue(descendingIterator, "observerMap.descendingIterator()");
        while (descendingIterator.hasNext() && !this.newEventOccurred) {
            Map.Entry entry = (Map.Entry) descendingIterator.next();
            Intrinsics.checkNotNullExpressionValue(entry, "next()");
            LifecycleObserver lifecycleObserver = (LifecycleObserver) entry.getKey();
            ObserverWithState observerWithState = (ObserverWithState) entry.getValue();
            while (observerWithState.getState().compareTo(this.state) > 0 && !this.newEventOccurred && this.observerMap.contains(lifecycleObserver)) {
                Lifecycle.Event downFrom = Lifecycle.Event.Companion.downFrom(observerWithState.getState());
                if (downFrom != null) {
                    pushParentState(downFrom.getTargetState());
                    observerWithState.dispatchEvent(lifecycleOwner2, downFrom);
                    popParentState();
                } else {
                    throw new IllegalStateException("no event down from " + observerWithState.getState());
                }
            }
        }
    }

    private final void sync() {
        LifecycleOwner lifecycleOwner2 = (LifecycleOwner) this.lifecycleOwner.get();
        if (lifecycleOwner2 != null) {
            while (!isSynced()) {
                this.newEventOccurred = false;
                Lifecycle.State state2 = this.state;
                Map.Entry eldest = this.observerMap.eldest();
                Intrinsics.checkNotNull(eldest);
                if (state2.compareTo(((ObserverWithState) eldest.getValue()).getState()) < 0) {
                    backwardPass(lifecycleOwner2);
                }
                Map.Entry newest = this.observerMap.newest();
                if (!this.newEventOccurred && newest != null && this.state.compareTo(((ObserverWithState) newest.getValue()).getState()) > 0) {
                    forwardPass(lifecycleOwner2);
                }
            }
            this.newEventOccurred = false;
            return;
        }
        throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
    }

    private final void enforceMainThreadIfNeeded(String str) {
        if (this.enforceMainThread && !ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException(("Method " + str + " must be called on the main thread").toString());
        }
    }

    public static final class ObserverWithState {
        private LifecycleEventObserver lifecycleObserver;
        private Lifecycle.State state;

        public ObserverWithState(LifecycleObserver lifecycleObserver2, Lifecycle.State state2) {
            Intrinsics.checkNotNullParameter(state2, "initialState");
            Intrinsics.checkNotNull(lifecycleObserver2);
            this.lifecycleObserver = Lifecycling.lifecycleEventObserver(lifecycleObserver2);
            this.state = state2;
        }

        public final Lifecycle.State getState() {
            return this.state;
        }

        public final void dispatchEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Intrinsics.checkNotNullParameter(event, "event");
            Lifecycle.State targetState = event.getTargetState();
            this.state = LifecycleRegistry.Companion.min$lifecycle_runtime_release(this.state, targetState);
            LifecycleEventObserver lifecycleEventObserver = this.lifecycleObserver;
            Intrinsics.checkNotNull(lifecycleOwner);
            lifecycleEventObserver.onStateChanged(lifecycleOwner, event);
            this.state = targetState;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Lifecycle.State min$lifecycle_runtime_release(Lifecycle.State state, Lifecycle.State state2) {
            Intrinsics.checkNotNullParameter(state, "state1");
            return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
        }
    }
}
