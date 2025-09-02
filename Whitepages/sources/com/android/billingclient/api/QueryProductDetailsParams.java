package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzai;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public final class QueryProductDetailsParams {
    private final zzai zza;

    public static class Builder {
        /* access modifiers changed from: private */
        public zzai zza;

        /* synthetic */ Builder(zzdc zzdc) {
        }

        public QueryProductDetailsParams build() {
            return new QueryProductDetailsParams(this, (zzdf) null);
        }

        public Builder setProductList(List list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("Product list cannot be empty.");
            }
            HashSet hashSet = new HashSet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Product product = (Product) it.next();
                if (!"play_pass_subs".equals(product.zzb())) {
                    hashSet.add(product.zzb());
                }
            }
            if (hashSet.size() <= 1) {
                this.zza = zzai.zzj(list);
                return this;
            }
            throw new IllegalArgumentException("All products should be of the same product type.");
        }
    }

    public static class Product {
        private final String zza;
        private final String zzb;

        public static class Builder {
            /* access modifiers changed from: private */
            public String zza;
            /* access modifiers changed from: private */
            public String zzb;

            /* synthetic */ Builder(zzdd zzdd) {
            }

            public Product build() {
                if ("first_party".equals(this.zzb)) {
                    throw new IllegalArgumentException("Serialized doc id must be provided for first party products.");
                } else if (this.zza == null) {
                    throw new IllegalArgumentException("Product id must be provided.");
                } else if (this.zzb != null) {
                    return new Product(this, (zzde) null);
                } else {
                    throw new IllegalArgumentException("Product type must be provided.");
                }
            }

            public Builder setProductId(String str) {
                this.zza = str;
                return this;
            }

            public Builder setProductType(String str) {
                this.zzb = str;
                return this;
            }
        }

        /* synthetic */ Product(Builder builder, zzde zzde) {
            this.zza = builder.zza;
            this.zzb = builder.zzb;
        }

        public static Builder newBuilder() {
            return new Builder((zzdd) null);
        }

        public final String zza() {
            return this.zza;
        }

        public final String zzb() {
            return this.zzb;
        }
    }

    /* synthetic */ QueryProductDetailsParams(Builder builder, zzdf zzdf) {
        this.zza = builder.zza;
    }

    public static Builder newBuilder() {
        return new Builder((zzdc) null);
    }

    public final zzai zza() {
        return this.zza;
    }

    public final String zzb() {
        return ((Product) this.zza.get(0)).zzb();
    }
}
