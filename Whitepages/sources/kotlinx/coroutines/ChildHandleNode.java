package kotlinx.coroutines;

import kotlin.Unit;

public final class ChildHandleNode extends JobCancellingNode implements ChildHandle {
    public final ChildJob childJob;

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public ChildHandleNode(ChildJob childJob2) {
        this.childJob = childJob2;
    }

    public Job getParent() {
        return getJob();
    }

    public void invoke(Throwable th) {
        this.childJob.parentCancelled(getJob());
    }

    public boolean childCancelled(Throwable th) {
        return getJob().childCancelled(th);
    }
}
