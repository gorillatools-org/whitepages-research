package kotlinx.coroutines;

final class SupervisorJobImpl extends JobImpl {
    public boolean childCancelled(Throwable th) {
        return false;
    }

    public SupervisorJobImpl(Job job) {
        super(job);
    }
}
