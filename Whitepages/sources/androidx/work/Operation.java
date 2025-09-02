package androidx.work;

public interface Operation {
    public static final State.IN_PROGRESS IN_PROGRESS = new State.IN_PROGRESS();
    public static final State.SUCCESS SUCCESS = new State.SUCCESS();

    public static abstract class State {
        State() {
        }

        public static final class SUCCESS extends State {
            private SUCCESS() {
            }

            public String toString() {
                return "SUCCESS";
            }
        }

        public static final class IN_PROGRESS extends State {
            private IN_PROGRESS() {
            }

            public String toString() {
                return "IN_PROGRESS";
            }
        }

        public static final class FAILURE extends State {
            private final Throwable mThrowable;

            public FAILURE(Throwable th) {
                this.mThrowable = th;
            }

            public Throwable getThrowable() {
                return this.mThrowable;
            }

            public String toString() {
                return "FAILURE (" + this.mThrowable.getMessage() + ")";
            }
        }
    }
}
