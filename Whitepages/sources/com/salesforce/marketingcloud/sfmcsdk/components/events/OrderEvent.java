package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class OrderEvent extends EngagementEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Order order;

    public /* synthetic */ OrderEvent(String str, Order order2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, order2);
    }

    public static final CancelOrderEvent cancel(Order order2) {
        return Companion.cancel(order2);
    }

    public static final DeliverOrderEvent deliver(Order order2) {
        return Companion.deliver(order2);
    }

    public static final ExchangeOrderEvent exchange(Order order2) {
        return Companion.exchange(order2);
    }

    public static final PreorderEvent preorder(Order order2) {
        return Companion.preorder(order2);
    }

    public static final PurchaseOrderEvent purchase(Order order2) {
        return Companion.purchase(order2);
    }

    public static final ReturnOrderEvent returnOrder(Order order2) {
        return Companion.returnOrder(order2);
    }

    public static final ShipOrderEvent ship(Order order2) {
        return Companion.ship(order2);
    }

    public final Order getOrder() {
        return this.order;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PurchaseOrderEvent purchase(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new PurchaseOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        public final PreorderEvent preorder(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new PreorderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        public final CancelOrderEvent cancel(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new CancelOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        public final ShipOrderEvent ship(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new ShipOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        public final DeliverOrderEvent deliver(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new DeliverOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        public final ReturnOrderEvent returnOrder(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new ReturnOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }

        public final ExchangeOrderEvent exchange(Order order) {
            Intrinsics.checkNotNullParameter(order, "order");
            try {
                return new ExchangeOrderEvent(order);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    private OrderEvent(String str, Order order2) {
        super(str, (DefaultConstructorMarker) null);
        this.order = order2;
    }
}
