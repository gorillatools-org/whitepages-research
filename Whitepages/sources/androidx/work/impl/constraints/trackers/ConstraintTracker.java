package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class ConstraintTracker {
    private final Context appContext;
    private Object currentState;
    private final LinkedHashSet listeners = new LinkedHashSet();
    private final Object lock = new Object();
    private final TaskExecutor taskExecutor;

    public abstract Object getInitialState();

    public abstract void startTracking();

    public abstract void stopTracking();

    protected ConstraintTracker(Context context, TaskExecutor taskExecutor2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(taskExecutor2, "taskExecutor");
        this.taskExecutor = taskExecutor2;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        this.appContext = applicationContext;
    }

    /* access modifiers changed from: protected */
    public final Context getAppContext() {
        return this.appContext;
    }

    public final void addListener(ConstraintListener constraintListener) {
        Intrinsics.checkNotNullParameter(constraintListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.lock) {
            try {
                if (this.listeners.add(constraintListener)) {
                    if (this.listeners.size() == 1) {
                        this.currentState = getInitialState();
                        Logger logger = Logger.get();
                        String access$getTAG$p = ConstraintTrackerKt.TAG;
                        logger.debug(access$getTAG$p, getClass().getSimpleName() + ": initial state = " + this.currentState);
                        startTracking();
                    }
                    constraintListener.onConstraintChanged(this.currentState);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void removeListener(ConstraintListener constraintListener) {
        Intrinsics.checkNotNullParameter(constraintListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        synchronized (this.lock) {
            try {
                if (this.listeners.remove(constraintListener) && this.listeners.isEmpty()) {
                    stopTracking();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void setState(Object obj) {
        synchronized (this.lock) {
            Object obj2 = this.currentState;
            if (obj2 == null || !Intrinsics.areEqual(obj2, obj)) {
                this.currentState = obj;
                this.taskExecutor.getMainThreadExecutor().execute(new ConstraintTracker$$ExternalSyntheticLambda0(CollectionsKt.toList(this.listeners), this));
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void _set_state_$lambda$4$lambda$3(List list, ConstraintTracker constraintTracker) {
        Intrinsics.checkNotNullParameter(list, "$listenersList");
        Intrinsics.checkNotNullParameter(constraintTracker, "this$0");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ConstraintListener) it.next()).onConstraintChanged(constraintTracker.currentState);
        }
    }
}
