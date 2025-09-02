package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;

public final class JobCancellationException extends CancellationException {
    public final transient Job job;

    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public JobCancellationException(String str, Throwable th, Job job2) {
        super(str);
        this.job = job2;
        if (th != null) {
            initCause(th);
        }
    }

    public String toString() {
        return super.toString() + "; job=" + this.job;
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof JobCancellationException) {
                JobCancellationException jobCancellationException = (JobCancellationException) obj;
                if (!Intrinsics.areEqual((Object) jobCancellationException.getMessage(), (Object) getMessage()) || !Intrinsics.areEqual((Object) jobCancellationException.job, (Object) this.job) || !Intrinsics.areEqual((Object) jobCancellationException.getCause(), (Object) getCause())) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String message = getMessage();
        Intrinsics.checkNotNull(message);
        int hashCode = ((message.hashCode() * 31) + this.job.hashCode()) * 31;
        Throwable cause = getCause();
        return hashCode + (cause != null ? cause.hashCode() : 0);
    }
}
