package kotlinx.coroutines.channels;

import kotlinx.coroutines.Waiter;

final class WaiterEB {
    public final Waiter waiter;

    public WaiterEB(Waiter waiter2) {
        this.waiter = waiter2;
    }

    public String toString() {
        return "WaiterEB(" + this.waiter + ')';
    }
}
