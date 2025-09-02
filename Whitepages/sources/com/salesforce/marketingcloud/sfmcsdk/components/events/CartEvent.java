package com.salesforce.marketingcloud.sfmcsdk.components.events;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class CartEvent extends EngagementEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<LineItem> lineItems;

    public /* synthetic */ CartEvent(String str, List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list);
    }

    public static final AddToCartEvent add(LineItem lineItem) {
        return Companion.add(lineItem);
    }

    public static final RemoveFromCartEvent remove(LineItem lineItem) {
        return Companion.remove(lineItem);
    }

    public static final ReplaceCartEvent replace(List<LineItem> list) {
        return Companion.replace(list);
    }

    public final List<LineItem> getLineItems() {
        return this.lineItems;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AddToCartEvent add(LineItem lineItem) {
            Intrinsics.checkNotNullParameter(lineItem, "lineItem");
            try {
                return new AddToCartEvent(lineItem);
            } catch (Exception unused) {
                return null;
            }
        }

        public final RemoveFromCartEvent remove(LineItem lineItem) {
            Intrinsics.checkNotNullParameter(lineItem, "lineItem");
            try {
                return new RemoveFromCartEvent(lineItem);
            } catch (Exception unused) {
                return null;
            }
        }

        public final ReplaceCartEvent replace(List<LineItem> list) {
            Intrinsics.checkNotNullParameter(list, "lineItems");
            try {
                return new ReplaceCartEvent(list);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    private CartEvent(String str, List<LineItem> list) {
        super(str, (DefaultConstructorMarker) null);
        this.lineItems = list;
    }
}
