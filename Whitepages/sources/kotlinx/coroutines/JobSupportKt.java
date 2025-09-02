package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;

public abstract class JobSupportKt {
    /* access modifiers changed from: private */
    public static final Symbol COMPLETING_ALREADY = new Symbol("COMPLETING_ALREADY");
    /* access modifiers changed from: private */
    public static final Symbol COMPLETING_RETRY = new Symbol("COMPLETING_RETRY");
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");
    /* access modifiers changed from: private */
    public static final Empty EMPTY_ACTIVE = new Empty(true);
    /* access modifiers changed from: private */
    public static final Empty EMPTY_NEW = new Empty(false);
    /* access modifiers changed from: private */
    public static final Symbol SEALED = new Symbol("SEALED");
    /* access modifiers changed from: private */
    public static final Symbol TOO_LATE_TO_CANCEL = new Symbol("TOO_LATE_TO_CANCEL");

    public static final Object boxIncomplete(Object obj) {
        return obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj;
    }

    public static final Object unboxState(Object obj) {
        Incomplete incomplete;
        IncompleteStateBox incompleteStateBox = obj instanceof IncompleteStateBox ? (IncompleteStateBox) obj : null;
        return (incompleteStateBox == null || (incomplete = incompleteStateBox.state) == null) ? obj : incomplete;
    }
}
