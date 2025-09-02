package com.salesforce.marketingcloud.sfmcsdk.components.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class CatalogEvent extends EngagementEvent {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final CatalogObject catalogObject;

    public /* synthetic */ CatalogEvent(String str, CatalogObject catalogObject2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, catalogObject2);
    }

    public static final CommentCatalogEvent comment(CatalogObject catalogObject2) {
        return Companion.comment(catalogObject2);
    }

    public static final FavoriteCatalogEvent favorite(CatalogObject catalogObject2) {
        return Companion.favorite(catalogObject2);
    }

    public static final QuickViewCatalogEvent quickView(CatalogObject catalogObject2) {
        return Companion.quickView(catalogObject2);
    }

    public static final ReviewCatalogEvent review(CatalogObject catalogObject2) {
        return Companion.review(catalogObject2);
    }

    public static final ShareCatalogEvent share(CatalogObject catalogObject2) {
        return Companion.share(catalogObject2);
    }

    public static final ViewCatalogEvent view(CatalogObject catalogObject2) {
        return Companion.view(catalogObject2);
    }

    public static final ViewCatalogDetailEvent viewDetail(CatalogObject catalogObject2) {
        return Companion.viewDetail(catalogObject2);
    }

    public final CatalogObject getCatalogObject() {
        return this.catalogObject;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ViewCatalogEvent view(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new ViewCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        public final ViewCatalogDetailEvent viewDetail(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new ViewCatalogDetailEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        public final QuickViewCatalogEvent quickView(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new QuickViewCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        public final ShareCatalogEvent share(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new ShareCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        public final ReviewCatalogEvent review(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new ReviewCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        public final CommentCatalogEvent comment(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new CommentCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }

        public final FavoriteCatalogEvent favorite(CatalogObject catalogObject) {
            Intrinsics.checkNotNullParameter(catalogObject, "catalogObject");
            try {
                return new FavoriteCatalogEvent(catalogObject);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    private CatalogEvent(String str, CatalogObject catalogObject2) {
        super(str, (DefaultConstructorMarker) null);
        this.catalogObject = catalogObject2;
    }
}
