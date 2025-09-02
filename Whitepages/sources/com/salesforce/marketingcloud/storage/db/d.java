package com.salesforce.marketingcloud.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.react.devsupport.StackTraceHelper;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

public final class d {

    static final class a extends Lambda implements Function0 {
        public static final a a = new a();

        a() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Failed to read InboxMessage from our local storage.";
        }
    }

    static final class b extends Lambda implements Function0 {
        public static final b a = new b();

        b() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Unable to read region from DB";
        }
    }

    static final class c extends Lambda implements Function0 {
        public static final c a = new c();

        c() {
            super(0);
        }

        /* renamed from: a */
        public final String invoke() {
            return "Unable to create ContentValues for InboxMessage.  Update failed";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0130 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0131 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0151 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0158 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01c3 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01c4 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01e4 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01eb A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0256 A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0257 A[Catch:{ Exception -> 0x002e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.salesforce.marketingcloud.messages.inbox.InboxMessage a(android.database.Cursor r8, com.salesforce.marketingcloud.util.c r9) {
        /*
            java.lang.Class<java.lang.Integer> r0 = java.lang.Integer.class
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            java.lang.String r2 = "cursor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
            java.lang.String r2 = "crypto"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r2)
            com.salesforce.marketingcloud.messages.inbox.InboxMessage r2 = new com.salesforce.marketingcloud.messages.inbox.InboxMessage     // Catch:{ Exception -> 0x002e }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x002e }
            java.lang.String r4 = "message_json"
            int r4 = r8.getColumnIndex(r4)     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            java.lang.String r7 = "Unsupported type"
            if (r6 == 0) goto L_0x0031
            java.lang.String r4 = r8.getString(r4)     // Catch:{ Exception -> 0x002e }
            goto L_0x009c
        L_0x002e:
            r8 = move-exception
            goto L_0x0282
        L_0x0031:
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x0048
            int r4 = r8.getInt(r4)     // Catch:{ Exception -> 0x002e }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x002e }
        L_0x0045:
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x002e }
            goto L_0x009c
        L_0x0048:
            java.lang.Class r6 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x005d
            double r4 = r8.getDouble(r4)     // Catch:{ Exception -> 0x002e }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x002e }
            goto L_0x0045
        L_0x005d:
            java.lang.Class r6 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x0072
            float r4 = r8.getFloat(r4)     // Catch:{ Exception -> 0x002e }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x002e }
            goto L_0x0045
        L_0x0072:
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x0087
            long r4 = r8.getLong(r4)     // Catch:{ Exception -> 0x002e }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x002e }
            goto L_0x0045
        L_0x0087:
            java.lang.Class r6 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r5 == 0) goto L_0x027c
            short r4 = r8.getShort(r4)     // Catch:{ Exception -> 0x002e }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x002e }
            goto L_0x0045
        L_0x009c:
            java.lang.String r9 = r9.b(r4)     // Catch:{ Exception -> 0x002e }
            if (r9 == 0) goto L_0x0274
            r3.<init>(r9)     // Catch:{ Exception -> 0x002e }
            r2.<init>((org.json.JSONObject) r3)     // Catch:{ Exception -> 0x002e }
            java.lang.String r9 = "is_deleted"
            int r9 = r8.getColumnIndex(r9)     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x002e }
            if (r4 == 0) goto L_0x00c3
            java.lang.String r9 = r8.getString(r9)     // Catch:{ Exception -> 0x002e }
        L_0x00c0:
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ Exception -> 0x002e }
            goto L_0x012c
        L_0x00c3:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x002e }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x002e }
            if (r4 == 0) goto L_0x00d8
            int r9 = r8.getInt(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x002e }
            goto L_0x012c
        L_0x00d8:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x002e }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x002e }
            if (r4 == 0) goto L_0x00ed
            double r3 = r8.getDouble(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Double r9 = java.lang.Double.valueOf(r3)     // Catch:{ Exception -> 0x002e }
            goto L_0x00c0
        L_0x00ed:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x002e }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x002e }
            if (r4 == 0) goto L_0x0102
            float r9 = r8.getFloat(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Float r9 = java.lang.Float.valueOf(r9)     // Catch:{ Exception -> 0x002e }
            goto L_0x00c0
        L_0x0102:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x002e }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x002e }
            if (r4 == 0) goto L_0x0117
            long r3 = r8.getLong(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Long r9 = java.lang.Long.valueOf(r3)     // Catch:{ Exception -> 0x002e }
            goto L_0x00c0
        L_0x0117:
            java.lang.Class r4 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x002e }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x002e }
            if (r3 == 0) goto L_0x026e
            short r9 = r8.getShort(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Short r9 = java.lang.Short.valueOf(r9)     // Catch:{ Exception -> 0x002e }
            goto L_0x00c0
        L_0x012c:
            r3 = 0
            r4 = 1
            if (r9 != 0) goto L_0x0131
            goto L_0x0139
        L_0x0131:
            int r9 = r9.intValue()     // Catch:{ Exception -> 0x002e }
            if (r9 != r4) goto L_0x0139
            r9 = r4
            goto L_0x013a
        L_0x0139:
            r9 = r3
        L_0x013a:
            r2.m747deleted(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.String r9 = "is_read"
            int r9 = r8.getColumnIndex(r9)     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x0158
            java.lang.String r9 = r8.getString(r9)     // Catch:{ Exception -> 0x002e }
        L_0x0155:
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ Exception -> 0x002e }
            goto L_0x01c1
        L_0x0158:
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x016d
            int r9 = r8.getInt(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x002e }
            goto L_0x01c1
        L_0x016d:
            java.lang.Class r6 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x0182
            double r5 = r8.getDouble(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Double r9 = java.lang.Double.valueOf(r5)     // Catch:{ Exception -> 0x002e }
            goto L_0x0155
        L_0x0182:
            java.lang.Class r6 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x0197
            float r9 = r8.getFloat(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Float r9 = java.lang.Float.valueOf(r9)     // Catch:{ Exception -> 0x002e }
            goto L_0x0155
        L_0x0197:
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r6 == 0) goto L_0x01ac
            long r5 = r8.getLong(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Long r9 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x002e }
            goto L_0x0155
        L_0x01ac:
            java.lang.Class r6 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x002e }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x002e }
            if (r5 == 0) goto L_0x0268
            short r9 = r8.getShort(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Short r9 = java.lang.Short.valueOf(r9)     // Catch:{ Exception -> 0x002e }
            goto L_0x0155
        L_0x01c1:
            if (r9 != 0) goto L_0x01c4
            goto L_0x01cc
        L_0x01c4:
            int r9 = r9.intValue()     // Catch:{ Exception -> 0x002e }
            if (r9 != r4) goto L_0x01cc
            r9 = r4
            goto L_0x01cd
        L_0x01cc:
            r9 = r3
        L_0x01cd:
            r2.m765read(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.String r9 = "is_dirty"
            int r9 = r8.getColumnIndex(r9)     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)     // Catch:{ Exception -> 0x002e }
            if (r1 == 0) goto L_0x01eb
            java.lang.String r8 = r8.getString(r9)     // Catch:{ Exception -> 0x002e }
        L_0x01e8:
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ Exception -> 0x002e }
            goto L_0x0254
        L_0x01eb:
            java.lang.Class r1 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)     // Catch:{ Exception -> 0x002e }
            if (r1 == 0) goto L_0x0200
            int r8 = r8.getInt(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x002e }
            goto L_0x0254
        L_0x0200:
            java.lang.Class r1 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)     // Catch:{ Exception -> 0x002e }
            if (r1 == 0) goto L_0x0215
            double r8 = r8.getDouble(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Double r8 = java.lang.Double.valueOf(r8)     // Catch:{ Exception -> 0x002e }
            goto L_0x01e8
        L_0x0215:
            java.lang.Class r1 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)     // Catch:{ Exception -> 0x002e }
            if (r1 == 0) goto L_0x022a
            float r8 = r8.getFloat(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Float r8 = java.lang.Float.valueOf(r8)     // Catch:{ Exception -> 0x002e }
            goto L_0x01e8
        L_0x022a:
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)     // Catch:{ Exception -> 0x002e }
            if (r1 == 0) goto L_0x023f
            long r8 = r8.getLong(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ Exception -> 0x002e }
            goto L_0x01e8
        L_0x023f:
            java.lang.Class r1 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x002e }
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)     // Catch:{ Exception -> 0x002e }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)     // Catch:{ Exception -> 0x002e }
            if (r0 == 0) goto L_0x0262
            short r8 = r8.getShort(r9)     // Catch:{ Exception -> 0x002e }
            java.lang.Short r8 = java.lang.Short.valueOf(r8)     // Catch:{ Exception -> 0x002e }
            goto L_0x01e8
        L_0x0254:
            if (r8 != 0) goto L_0x0257
            goto L_0x025e
        L_0x0257:
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x002e }
            if (r8 != r4) goto L_0x025e
            r3 = r4
        L_0x025e:
            r2.m762dirty(r3)     // Catch:{ Exception -> 0x002e }
            goto L_0x0291
        L_0x0262:
            java.lang.UnsupportedOperationException r8 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x002e }
            r8.<init>(r7)     // Catch:{ Exception -> 0x002e }
            throw r8     // Catch:{ Exception -> 0x002e }
        L_0x0268:
            java.lang.UnsupportedOperationException r8 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x002e }
            r8.<init>(r7)     // Catch:{ Exception -> 0x002e }
            throw r8     // Catch:{ Exception -> 0x002e }
        L_0x026e:
            java.lang.UnsupportedOperationException r8 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x002e }
            r8.<init>(r7)     // Catch:{ Exception -> 0x002e }
            throw r8     // Catch:{ Exception -> 0x002e }
        L_0x0274:
            java.lang.String r8 = "Required value was null."
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x002e }
            r9.<init>(r8)     // Catch:{ Exception -> 0x002e }
            throw r9     // Catch:{ Exception -> 0x002e }
        L_0x027c:
            java.lang.UnsupportedOperationException r8 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x002e }
            r8.<init>(r7)     // Catch:{ Exception -> 0x002e }
            throw r8     // Catch:{ Exception -> 0x002e }
        L_0x0282:
            com.salesforce.marketingcloud.g r9 = com.salesforce.marketingcloud.g.a
            java.lang.String r0 = com.salesforce.marketingcloud.storage.db.g.g
            java.lang.String r1 = "TAG"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.salesforce.marketingcloud.storage.db.d$a r1 = com.salesforce.marketingcloud.storage.db.d.a.a
            r9.b((java.lang.String) r0, (java.lang.Throwable) r8, (kotlin.jvm.functions.Function0) r1)
            r2 = 0
        L_0x0291:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.d.a(android.database.Cursor, com.salesforce.marketingcloud.util.c):com.salesforce.marketingcloud.messages.inbox.InboxMessage");
    }

    /* JADX WARNING: Removed duplicated region for block: B:134:0x036a A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x036f A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x03dc A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x03e2 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x03f7 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x03fc A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0469 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0470 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0486 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x048d A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x04f8 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x0582 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0695 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x069c A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x06b2 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x06b9 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x0724 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x072b A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:282:0x0741 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:284:0x0748 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:301:0x07b4 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x07bb A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x07d1 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x07d8 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x0843 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x0844 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x0864 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x086b A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:348:0x08d6 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x08dd A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x08f3 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x08fa A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0965 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:371:0x096c A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x0982 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x0987 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:393:0x0a0a A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:394:0x0a0f A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:412:0x0a80 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:413:0x0a87 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:416:0x0a9d A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:417:0x0aa2 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:435:0x0b2b A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:437:0x0b32 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:453:0x0b9d A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:456:0x0bb8 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:457:0x0bbd A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:474:0x0c2a A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:475:0x0c2f A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:478:0x0c47 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:479:0x0c4c A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:496:0x0cb9 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:497:0x0cbe A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:500:0x0cd6 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:502:0x0cdd A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:518:0x0d48 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:519:0x0d4d A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:522:0x0d65 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:524:0x0d6c A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:540:0x0dd7 A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:572:0x0e3b A[Catch:{ Exception -> 0x0e8f }] */
    /* JADX WARNING: Removed duplicated region for block: B:576:0x0e47 A[Catch:{ Exception -> 0x0e8f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.salesforce.marketingcloud.messages.Message b(android.database.Cursor r29, com.salesforce.marketingcloud.util.c r30) {
        /*
            r0 = r29
            r1 = r30
            java.lang.Class<java.lang.Integer> r2 = java.lang.Integer.class
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            java.lang.String r4 = "cursor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            java.lang.String r4 = "crypto"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r5 = "id"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r8 = "Unsupported type"
            if (r7 == 0) goto L_0x002e
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0e8f }
        L_0x002c:
            r10 = r5
            goto L_0x0099
        L_0x002e:
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0045
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
        L_0x0042:
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x0e8f }
            goto L_0x002c
        L_0x0045:
            java.lang.Class r7 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x005a
            double r5 = r0.getDouble(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0042
        L_0x005a:
            java.lang.Class r7 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x006f
            float r5 = r0.getFloat(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0042
        L_0x006f:
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0084
            long r5 = r0.getLong(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0042
        L_0x0084:
            java.lang.Class r7 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0e89
            short r5 = r0.getShort(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r5 = java.lang.Short.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0042
        L_0x0099:
            java.lang.String r5 = "Required value was null."
            if (r10 == 0) goto L_0x0e83
            java.lang.String r6 = "title"
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x00b6
            java.lang.String r6 = r0.getString(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0121
        L_0x00b6:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x00cd
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
        L_0x00ca:
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0121
        L_0x00cd:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x00e2
            double r6 = r0.getDouble(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x00ca
        L_0x00e2:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x00f7
            float r6 = r0.getFloat(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x00ca
        L_0x00f7:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x010c
            long r6 = r0.getLong(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x00ca
        L_0x010c:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e7d
            short r6 = r0.getShort(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r6 = java.lang.Short.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x00ca
        L_0x0121:
            java.lang.String r11 = r1.b(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r6 = "alert"
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x013e
            java.lang.String r6 = r0.getString(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x01a9
        L_0x013e:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0155
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
        L_0x0152:
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0e8f }
            goto L_0x01a9
        L_0x0155:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x016a
            double r6 = r0.getDouble(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0152
        L_0x016a:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x017f
            float r6 = r0.getFloat(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0152
        L_0x017f:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0194
            long r6 = r0.getLong(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0152
        L_0x0194:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e77
            short r6 = r0.getShort(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r6 = java.lang.Short.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0152
        L_0x01a9:
            java.lang.String r12 = r1.b(r6)     // Catch:{ Exception -> 0x0e8f }
            if (r12 == 0) goto L_0x0e71
            java.lang.String r6 = "checkNotNull(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r6 = "sound"
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x01ce
            java.lang.String r6 = r0.getString(r6)     // Catch:{ Exception -> 0x0e8f }
        L_0x01cc:
            r13 = r6
            goto L_0x0239
        L_0x01ce:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x01e5
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
        L_0x01e2:
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0e8f }
            goto L_0x01cc
        L_0x01e5:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x01fa
            double r6 = r0.getDouble(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x01e2
        L_0x01fa:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x020f
            float r6 = r0.getFloat(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x01e2
        L_0x020f:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0224
            long r6 = r0.getLong(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x01e2
        L_0x0224:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e6b
            short r6 = r0.getShort(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r6 = java.lang.Short.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x01e2
        L_0x0239:
            java.lang.String r6 = "mediaUrl"
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0252
            java.lang.String r6 = r0.getString(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x02bd
        L_0x0252:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0269
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
        L_0x0266:
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0e8f }
            goto L_0x02bd
        L_0x0269:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x027e
            double r6 = r0.getDouble(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0266
        L_0x027e:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0293
            float r6 = r0.getFloat(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0266
        L_0x0293:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x02a8
            long r6 = r0.getLong(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0266
        L_0x02a8:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e65
            short r6 = r0.getShort(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r6 = java.lang.Short.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0266
        L_0x02bd:
            java.lang.String r6 = r1.b(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r7 = "mediaAlt"
            int r7 = r0.getColumnIndex(r7)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0e8f }
            if (r14 == 0) goto L_0x02da
            java.lang.String r7 = r0.getString(r7)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0345
        L_0x02da:
            java.lang.Class r14 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0e8f }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0e8f }
            if (r14 == 0) goto L_0x02f1
            int r7 = r0.getInt(r7)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x0e8f }
        L_0x02ee:
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0345
        L_0x02f1:
            java.lang.Class r14 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0e8f }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0e8f }
            if (r14 == 0) goto L_0x0306
            double r14 = r0.getDouble(r7)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r7 = java.lang.Double.valueOf(r14)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x02ee
        L_0x0306:
            java.lang.Class r14 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0e8f }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0e8f }
            if (r14 == 0) goto L_0x031b
            float r7 = r0.getFloat(r7)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r7 = java.lang.Float.valueOf(r7)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x02ee
        L_0x031b:
            java.lang.Class r14 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0e8f }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0e8f }
            if (r14 == 0) goto L_0x0330
            long r14 = r0.getLong(r7)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r7 = java.lang.Long.valueOf(r14)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x02ee
        L_0x0330:
            java.lang.Class r14 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0e5f
            short r7 = r0.getShort(r7)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r7 = java.lang.Short.valueOf(r7)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x02ee
        L_0x0345:
            java.lang.String r7 = r1.b(r7)     // Catch:{ Exception -> 0x0e8f }
            if (r6 != 0) goto L_0x0350
            if (r7 == 0) goto L_0x034e
            goto L_0x0350
        L_0x034e:
            r14 = 0
            goto L_0x0356
        L_0x0350:
            com.salesforce.marketingcloud.messages.Message$Media r9 = new com.salesforce.marketingcloud.messages.Message$Media     // Catch:{ Exception -> 0x0e8f }
            r9.<init>(r6, r7)     // Catch:{ Exception -> 0x0e8f }
            r14 = r9
        L_0x0356:
            java.lang.String r6 = "start_date"
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x036f
            java.lang.String r6 = r0.getString(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x03da
        L_0x036f:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0386
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
        L_0x0383:
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0e8f }
            goto L_0x03da
        L_0x0386:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x039b
            double r6 = r0.getDouble(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0383
        L_0x039b:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x03b0
            float r6 = r0.getFloat(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0383
        L_0x03b0:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x03c5
            long r6 = r0.getLong(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0383
        L_0x03c5:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e59
            short r6 = r0.getShort(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r6 = java.lang.Short.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0383
        L_0x03da:
            if (r6 == 0) goto L_0x03e2
            java.util.Date r6 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r6)     // Catch:{ Exception -> 0x0e8f }
            r15 = r6
            goto L_0x03e3
        L_0x03e2:
            r15 = 0
        L_0x03e3:
            java.lang.String r6 = "end_date"
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x03fc
            java.lang.String r6 = r0.getString(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0467
        L_0x03fc:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0413
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
        L_0x0410:
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0467
        L_0x0413:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0428
            double r6 = r0.getDouble(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0410
        L_0x0428:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x043d
            float r6 = r0.getFloat(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0410
        L_0x043d:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0452
            long r6 = r0.getLong(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0410
        L_0x0452:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e53
            short r6 = r0.getShort(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r6 = java.lang.Short.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0410
        L_0x0467:
            if (r6 == 0) goto L_0x0470
            java.util.Date r6 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r6)     // Catch:{ Exception -> 0x0e8f }
            r16 = r6
            goto L_0x0472
        L_0x0470:
            r16 = 0
        L_0x0472:
            java.lang.String r6 = "message_type"
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x048d
            java.lang.String r6 = r0.getString(r6)     // Catch:{ Exception -> 0x0e8f }
        L_0x048a:
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ Exception -> 0x0e8f }
            goto L_0x04f6
        L_0x048d:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x04a2
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x04f6
        L_0x04a2:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x04b7
            double r6 = r0.getDouble(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x048a
        L_0x04b7:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x04cc
            float r6 = r0.getFloat(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x048a
        L_0x04cc:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x04e1
            long r6 = r0.getLong(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x048a
        L_0x04e1:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e4d
            short r6 = r0.getShort(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r6 = java.lang.Short.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x048a
        L_0x04f6:
            if (r6 == 0) goto L_0x0e47
            int r17 = r6.intValue()     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r6 = "content_type"
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0517
            java.lang.String r6 = r0.getString(r6)     // Catch:{ Exception -> 0x0e8f }
        L_0x0514:
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0580
        L_0x0517:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x052c
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0580
        L_0x052c:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0541
            double r6 = r0.getDouble(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0514
        L_0x0541:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0556
            float r6 = r0.getFloat(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0514
        L_0x0556:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x056b
            long r6 = r0.getLong(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0514
        L_0x056b:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e41
            short r6 = r0.getShort(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r6 = java.lang.Short.valueOf(r6)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0514
        L_0x0580:
            if (r6 == 0) goto L_0x0e3b
            int r18 = r6.intValue()     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r5 = "url"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x059f
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x060a
        L_0x059f:
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x05b6
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
        L_0x05b3:
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Exception -> 0x0e8f }
            goto L_0x060a
        L_0x05b6:
            java.lang.Class r7 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x05cb
            double r5 = r0.getDouble(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x05b3
        L_0x05cb:
            java.lang.Class r7 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x05e0
            float r5 = r0.getFloat(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x05b3
        L_0x05e0:
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x05f5
            long r5 = r0.getLong(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x05b3
        L_0x05f5:
            java.lang.Class r7 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0e35
            short r5 = r0.getShort(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r5 = java.lang.Short.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x05b3
        L_0x060a:
            java.lang.String r19 = r1.b(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r5 = "messages_per_period"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0629
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0e8f }
        L_0x0626:
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0692
        L_0x0629:
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x063e
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0692
        L_0x063e:
            java.lang.Class r7 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0653
            double r5 = r0.getDouble(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0626
        L_0x0653:
            java.lang.Class r7 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0668
            float r5 = r0.getFloat(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0626
        L_0x0668:
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x067d
            long r5 = r0.getLong(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0626
        L_0x067d:
            java.lang.Class r7 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0e2f
            short r5 = r0.getShort(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r5 = java.lang.Short.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0626
        L_0x0692:
            r6 = -1
            if (r5 == 0) goto L_0x069c
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0e8f }
            r20 = r5
            goto L_0x069e
        L_0x069c:
            r20 = r6
        L_0x069e:
            java.lang.String r5 = "number_of_periods"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x06b9
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0e8f }
        L_0x06b6:
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0722
        L_0x06b9:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x06ce
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0722
        L_0x06ce:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x06e3
            double r21 = r0.getDouble(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r5 = java.lang.Double.valueOf(r21)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x06b6
        L_0x06e3:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x06f8
            float r5 = r0.getFloat(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x06b6
        L_0x06f8:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x070d
            long r21 = r0.getLong(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r5 = java.lang.Long.valueOf(r21)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x06b6
        L_0x070d:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e29
            short r5 = r0.getShort(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r5 = java.lang.Short.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x06b6
        L_0x0722:
            if (r5 == 0) goto L_0x072b
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0e8f }
            r21 = r5
            goto L_0x072d
        L_0x072b:
            r21 = r6
        L_0x072d:
            java.lang.String r5 = "period_type"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0748
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0e8f }
        L_0x0745:
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ Exception -> 0x0e8f }
            goto L_0x07b1
        L_0x0748:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x075d
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x07b1
        L_0x075d:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0772
            double r22 = r0.getDouble(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r5 = java.lang.Double.valueOf(r22)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0745
        L_0x0772:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0787
            float r5 = r0.getFloat(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0745
        L_0x0787:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x079c
            long r22 = r0.getLong(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r5 = java.lang.Long.valueOf(r22)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0745
        L_0x079c:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r7 == 0) goto L_0x0e23
            short r5 = r0.getShort(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r5 = java.lang.Short.valueOf(r5)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0745
        L_0x07b1:
            r7 = 0
            if (r5 == 0) goto L_0x07bb
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0e8f }
            r22 = r5
            goto L_0x07bd
        L_0x07bb:
            r22 = r7
        L_0x07bd:
            java.lang.String r5 = "rolling_period"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0e8f }
            if (r4 == 0) goto L_0x07d8
            java.lang.String r4 = r0.getString(r5)     // Catch:{ Exception -> 0x0e8f }
        L_0x07d5:
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0841
        L_0x07d8:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0e8f }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0e8f }
            if (r4 == 0) goto L_0x07ed
            int r4 = r0.getInt(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0841
        L_0x07ed:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0e8f }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0e8f }
            if (r4 == 0) goto L_0x0802
            double r4 = r0.getDouble(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x07d5
        L_0x0802:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0e8f }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0e8f }
            if (r4 == 0) goto L_0x0817
            float r4 = r0.getFloat(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x07d5
        L_0x0817:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0e8f }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0e8f }
            if (r4 == 0) goto L_0x082c
            long r4 = r0.getLong(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x07d5
        L_0x082c:
            java.lang.Class r4 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0e8f }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0e8f }
            if (r4 == 0) goto L_0x0e1d
            short r4 = r0.getShort(r5)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x07d5
        L_0x0841:
            if (r4 != 0) goto L_0x0844
            goto L_0x084e
        L_0x0844:
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x0e8f }
            r5 = 1
            if (r4 != r5) goto L_0x084e
            r23 = r5
            goto L_0x0850
        L_0x084e:
            r23 = r7
        L_0x0850:
            java.lang.String r4 = "message_limit"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x086b
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x0868:
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x08d4
        L_0x086b:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0880
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x08d4
        L_0x0880:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0895
            double r4 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0868
        L_0x0895:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x08aa
            float r4 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0868
        L_0x08aa:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x08bf
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0868
        L_0x08bf:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r5 == 0) goto L_0x0e17
            short r4 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0868
        L_0x08d4:
            if (r4 == 0) goto L_0x08dd
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x0e8f }
            r24 = r4
            goto L_0x08df
        L_0x08dd:
            r24 = r6
        L_0x08df:
            java.lang.String r4 = "proximity"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x08fa
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x08f7:
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0963
        L_0x08fa:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x090f
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0963
        L_0x090f:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0924
            double r4 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x08f7
        L_0x0924:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0939
            float r4 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x08f7
        L_0x0939:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x094e
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x08f7
        L_0x094e:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r5 == 0) goto L_0x0e11
            short r4 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x08f7
        L_0x0963:
            if (r4 == 0) goto L_0x096c
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x0e8f }
            r25 = r4
            goto L_0x096e
        L_0x096c:
            r25 = r7
        L_0x096e:
            java.lang.String r4 = "open_direct"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0987
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x09f2
        L_0x0987:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x099e
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x099b:
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x09f2
        L_0x099e:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x09b3
            double r4 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x099b
        L_0x09b3:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x09c8
            float r4 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x099b
        L_0x09c8:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x09dd
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x099b
        L_0x09dd:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r5 == 0) goto L_0x0e0b
            short r4 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x099b
        L_0x09f2:
            java.lang.String r26 = r1.b(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r4 = "keys"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0a0f
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0a7a
        L_0x0a0f:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0a26
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x0a23:
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0a7a
        L_0x0a26:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0a3b
            double r4 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0a23
        L_0x0a3b:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0a50
            float r4 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0a23
        L_0x0a50:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0a65
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0a23
        L_0x0a65:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r5 == 0) goto L_0x0e05
            short r4 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0a23
        L_0x0a7a:
            java.lang.String r4 = r1.b(r4)     // Catch:{ Exception -> 0x0e8f }
            if (r4 == 0) goto L_0x0a87
            java.util.Map r4 = com.salesforce.marketingcloud.util.l.c((java.lang.String) r4)     // Catch:{ Exception -> 0x0e8f }
            r27 = r4
            goto L_0x0a89
        L_0x0a87:
            r27 = 0
        L_0x0a89:
            java.lang.String r4 = "custom"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0aa2
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0b0d
        L_0x0aa2:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0ab9
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x0ab6:
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0b0d
        L_0x0ab9:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0ace
            double r4 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0ab6
        L_0x0ace:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0ae3
            float r4 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0ab6
        L_0x0ae3:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0af8
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0ab6
        L_0x0af8:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r5 == 0) goto L_0x0dff
            short r4 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0ab6
        L_0x0b0d:
            java.lang.String r28 = r1.b(r4)     // Catch:{ Exception -> 0x0e8f }
            com.salesforce.marketingcloud.messages.Message r1 = new com.salesforce.marketingcloud.messages.Message     // Catch:{ Exception -> 0x0e8f }
            r9 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r4 = "notify_id"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0b32
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x0b2f:
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0b9b
        L_0x0b32:
            java.lang.Class r9 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0b47
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0b9b
        L_0x0b47:
            java.lang.Class r9 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0b5c
            double r4 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0b2f
        L_0x0b5c:
            java.lang.Class r9 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0b71
            float r4 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0b2f
        L_0x0b71:
            java.lang.Class r9 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r9 == 0) goto L_0x0b86
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0b2f
        L_0x0b86:
            java.lang.Class r9 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x0e8f }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ Exception -> 0x0e8f }
            if (r5 == 0) goto L_0x0df9
            short r4 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0b2f
        L_0x0b9b:
            if (r4 == 0) goto L_0x0ba1
            int r6 = r4.intValue()     // Catch:{ Exception -> 0x0e8f }
        L_0x0ba1:
            r1.m674notificationId(r6)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r4 = "last_shown_date"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0bbd
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0c28
        L_0x0bbd:
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0bd4
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x0bd1:
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0c28
        L_0x0bd4:
            java.lang.Class r6 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0be9
            double r4 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0bd1
        L_0x0be9:
            java.lang.Class r6 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0bfe
            float r4 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0bd1
        L_0x0bfe:
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0c13
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0bd1
        L_0x0c13:
            java.lang.Class r6 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r5 == 0) goto L_0x0df3
            short r4 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0bd1
        L_0x0c28:
            if (r4 == 0) goto L_0x0c2f
            java.util.Date r4 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0c30
        L_0x0c2f:
            r4 = 0
        L_0x0c30:
            r1.m670lastShownDate(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r4 = "next_allowed_show"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0c4c
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0cb7
        L_0x0c4c:
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0c63
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x0c60:
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0cb7
        L_0x0c63:
            java.lang.Class r6 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0c78
            double r4 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0c60
        L_0x0c78:
            java.lang.Class r6 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0c8d
            float r4 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0c60
        L_0x0c8d:
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0ca2
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0c60
        L_0x0ca2:
            java.lang.Class r6 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r5 == 0) goto L_0x0ded
            short r4 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0c60
        L_0x0cb7:
            if (r4 == 0) goto L_0x0cbe
            java.util.Date r4 = com.salesforce.marketingcloud.internal.m.a((java.lang.String) r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0cbf
        L_0x0cbe:
            r4 = 0
        L_0x0cbf:
            r1.m672nextAllowedShow(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r4 = "period_show_count"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0cdd
            java.lang.String r4 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x0cda:
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0d46
        L_0x0cdd:
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0cf2
            int r4 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0d46
        L_0x0cf2:
            java.lang.Class r6 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0d07
            double r4 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r4 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0cda
        L_0x0d07:
            java.lang.Class r6 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0d1c
            float r4 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0cda
        L_0x0d1c:
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r6 == 0) goto L_0x0d31
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0cda
        L_0x0d31:
            java.lang.Class r6 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x0e8f }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0e8f }
            if (r5 == 0) goto L_0x0de7
            short r4 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r4 = java.lang.Short.valueOf(r4)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0cda
        L_0x0d46:
            if (r4 == 0) goto L_0x0d4d
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0d4e
        L_0x0d4d:
            r4 = r7
        L_0x0d4e:
            r1.m676periodShowCount(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.String r4 = "show_count"
            int r4 = r0.getColumnIndex(r4)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x0e8f }
            if (r3 == 0) goto L_0x0d6c
            java.lang.String r0 = r0.getString(r4)     // Catch:{ Exception -> 0x0e8f }
        L_0x0d69:
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0dd5
        L_0x0d6c:
            java.lang.Class r3 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x0e8f }
            if (r3 == 0) goto L_0x0d81
            int r0 = r0.getInt(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0dd5
        L_0x0d81:
            java.lang.Class r3 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x0e8f }
            if (r3 == 0) goto L_0x0d96
            double r2 = r0.getDouble(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Double r0 = java.lang.Double.valueOf(r2)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0d69
        L_0x0d96:
            java.lang.Class r3 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x0e8f }
            if (r3 == 0) goto L_0x0dab
            float r0 = r0.getFloat(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Float r0 = java.lang.Float.valueOf(r0)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0d69
        L_0x0dab:
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x0e8f }
            if (r3 == 0) goto L_0x0dc0
            long r2 = r0.getLong(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0d69
        L_0x0dc0:
            java.lang.Class r3 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0e8f }
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0e8f }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x0e8f }
            if (r2 == 0) goto L_0x0de1
            short r0 = r0.getShort(r4)     // Catch:{ Exception -> 0x0e8f }
            java.lang.Short r0 = java.lang.Short.valueOf(r0)     // Catch:{ Exception -> 0x0e8f }
            goto L_0x0d69
        L_0x0dd5:
            if (r0 == 0) goto L_0x0ddb
            int r7 = r0.intValue()     // Catch:{ Exception -> 0x0e8f }
        L_0x0ddb:
            r1.m678showCount(r7)     // Catch:{ Exception -> 0x0e8f }
            r4 = r1
            goto L_0x0e90
        L_0x0de1:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0de7:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0ded:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0df3:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0df9:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0dff:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e05:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e0b:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e11:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e17:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e1d:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e23:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e29:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e2f:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e35:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e3b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e41:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e47:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e4d:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e53:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e59:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e5f:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e65:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e6b:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e71:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e77:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e7d:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e83:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r5)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e89:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0e8f }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0e8f }
            throw r0     // Catch:{ Exception -> 0x0e8f }
        L_0x0e8f:
            r4 = 0
        L_0x0e90:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.d.b(android.database.Cursor, com.salesforce.marketingcloud.util.c):com.salesforce.marketingcloud.messages.Message");
    }

    /* JADX WARNING: Removed duplicated region for block: B:131:0x0366 A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x036c A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0382 A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0389 A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x03f4 A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x03fa A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0410 A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0417 A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0482 A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x062b A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x062c A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:245:0x0650 A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0672 A[Catch:{ Exception -> 0x0033 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0252 A[Catch:{ Exception -> 0x0033 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.salesforce.marketingcloud.messages.Region c(android.database.Cursor r20, com.salesforce.marketingcloud.util.c r21) {
        /*
            r0 = r20
            r1 = r21
            java.lang.String r2 = "checkNotNull(...)"
            java.lang.Class<java.lang.Integer> r3 = java.lang.Integer.class
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            java.lang.String r5 = "cursor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r5)
            java.lang.String r5 = "crypto"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r5)
            com.salesforce.marketingcloud.messages.Region r5 = new com.salesforce.marketingcloud.messages.Region     // Catch:{ Exception -> 0x0033 }
            java.lang.String r6 = "id"
            int r6 = r0.getColumnIndex(r6)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r15 = "Unsupported type"
            if (r8 == 0) goto L_0x0036
            java.lang.String r6 = r0.getString(r6)     // Catch:{ Exception -> 0x0033 }
        L_0x0030:
            r7 = r6
            goto L_0x00a1
        L_0x0033:
            r0 = move-exception
            goto L_0x06a6
        L_0x0036:
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)     // Catch:{ Exception -> 0x0033 }
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)     // Catch:{ Exception -> 0x0033 }
            if (r8 == 0) goto L_0x004d
            int r6 = r0.getInt(r6)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0033 }
        L_0x004a:
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x0033 }
            goto L_0x0030
        L_0x004d:
            java.lang.Class r8 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)     // Catch:{ Exception -> 0x0033 }
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)     // Catch:{ Exception -> 0x0033 }
            if (r8 == 0) goto L_0x0062
            double r6 = r0.getDouble(r6)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x0033 }
            goto L_0x004a
        L_0x0062:
            java.lang.Class r8 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)     // Catch:{ Exception -> 0x0033 }
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)     // Catch:{ Exception -> 0x0033 }
            if (r8 == 0) goto L_0x0077
            float r6 = r0.getFloat(r6)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x0033 }
            goto L_0x004a
        L_0x0077:
            java.lang.Class r8 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)     // Catch:{ Exception -> 0x0033 }
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)     // Catch:{ Exception -> 0x0033 }
            if (r8 == 0) goto L_0x008c
            long r6 = r0.getLong(r6)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0033 }
            goto L_0x004a
        L_0x008c:
            java.lang.Class r8 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)     // Catch:{ Exception -> 0x0033 }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)     // Catch:{ Exception -> 0x0033 }
            if (r7 == 0) goto L_0x069f
            short r6 = r0.getShort(r6)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r6 = java.lang.Short.valueOf(r6)     // Catch:{ Exception -> 0x0033 }
            goto L_0x004a
        L_0x00a1:
            java.lang.String r6 = "Required value was null."
            if (r7 == 0) goto L_0x0699
            com.salesforce.marketingcloud.location.LatLon r8 = new com.salesforce.marketingcloud.location.LatLon     // Catch:{ Exception -> 0x0033 }
            java.lang.String r9 = "latitude"
            int r9 = r0.getColumnIndex(r9)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x00c0
            java.lang.String r9 = r0.getString(r9)     // Catch:{ Exception -> 0x0033 }
            goto L_0x012b
        L_0x00c0:
            java.lang.Class r11 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x00d7
            int r9 = r0.getInt(r9)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0033 }
        L_0x00d4:
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x0033 }
            goto L_0x012b
        L_0x00d7:
            java.lang.Class r11 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x00ec
            double r9 = r0.getDouble(r9)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r9 = java.lang.Double.valueOf(r9)     // Catch:{ Exception -> 0x0033 }
            goto L_0x00d4
        L_0x00ec:
            java.lang.Class r11 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x0101
            float r9 = r0.getFloat(r9)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r9 = java.lang.Float.valueOf(r9)     // Catch:{ Exception -> 0x0033 }
            goto L_0x00d4
        L_0x0101:
            java.lang.Class r11 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x0116
            long r9 = r0.getLong(r9)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ Exception -> 0x0033 }
            goto L_0x00d4
        L_0x0116:
            java.lang.Class r11 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r10 == 0) goto L_0x0692
            short r9 = r0.getShort(r9)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r9 = java.lang.Short.valueOf(r9)     // Catch:{ Exception -> 0x0033 }
            goto L_0x00d4
        L_0x012b:
            java.lang.String r9 = r1.b(r9)     // Catch:{ Exception -> 0x0033 }
            if (r9 == 0) goto L_0x068c
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r2)     // Catch:{ Exception -> 0x0033 }
            double r9 = java.lang.Double.parseDouble(r9)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r11 = "longitude"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x0151
            java.lang.String r11 = r0.getString(r11)     // Catch:{ Exception -> 0x0033 }
            goto L_0x01bc
        L_0x0151:
            java.lang.Class r13 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x0168
            int r11 = r0.getInt(r11)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ Exception -> 0x0033 }
        L_0x0165:
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ Exception -> 0x0033 }
            goto L_0x01bc
        L_0x0168:
            java.lang.Class r13 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x017d
            double r11 = r0.getDouble(r11)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r11 = java.lang.Double.valueOf(r11)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0165
        L_0x017d:
            java.lang.Class r13 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x0192
            float r11 = r0.getFloat(r11)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r11 = java.lang.Float.valueOf(r11)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0165
        L_0x0192:
            java.lang.Class r13 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x01a7
            long r11 = r0.getLong(r11)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0165
        L_0x01a7:
            java.lang.Class r13 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r12 == 0) goto L_0x0685
            short r11 = r0.getShort(r11)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r11 = java.lang.Short.valueOf(r11)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0165
        L_0x01bc:
            java.lang.String r11 = r1.b(r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x067f
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r2)     // Catch:{ Exception -> 0x0033 }
            double r11 = java.lang.Double.parseDouble(r11)     // Catch:{ Exception -> 0x0033 }
            r8.<init>(r9, r11)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r2 = "radius"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)     // Catch:{ Exception -> 0x0033 }
            if (r10 == 0) goto L_0x01e7
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x0033 }
        L_0x01e4:
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Exception -> 0x0033 }
            goto L_0x0250
        L_0x01e7:
            java.lang.Class r10 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)     // Catch:{ Exception -> 0x0033 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)     // Catch:{ Exception -> 0x0033 }
            if (r10 == 0) goto L_0x01fc
            int r2 = r0.getInt(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0250
        L_0x01fc:
            java.lang.Class r10 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)     // Catch:{ Exception -> 0x0033 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)     // Catch:{ Exception -> 0x0033 }
            if (r10 == 0) goto L_0x0211
            double r9 = r0.getDouble(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r2 = java.lang.Double.valueOf(r9)     // Catch:{ Exception -> 0x0033 }
            goto L_0x01e4
        L_0x0211:
            java.lang.Class r10 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)     // Catch:{ Exception -> 0x0033 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)     // Catch:{ Exception -> 0x0033 }
            if (r10 == 0) goto L_0x0226
            float r2 = r0.getFloat(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x01e4
        L_0x0226:
            java.lang.Class r10 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)     // Catch:{ Exception -> 0x0033 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)     // Catch:{ Exception -> 0x0033 }
            if (r10 == 0) goto L_0x023b
            long r9 = r0.getLong(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r2 = java.lang.Long.valueOf(r9)     // Catch:{ Exception -> 0x0033 }
            goto L_0x01e4
        L_0x023b:
            java.lang.Class r10 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)     // Catch:{ Exception -> 0x0033 }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)     // Catch:{ Exception -> 0x0033 }
            if (r9 == 0) goto L_0x0678
            short r2 = r0.getShort(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r2 = java.lang.Short.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x01e4
        L_0x0250:
            if (r2 == 0) goto L_0x0672
            int r9 = r2.intValue()     // Catch:{ Exception -> 0x0033 }
            java.lang.String r2 = "beacon_guid"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x026f
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x02da
        L_0x026f:
            java.lang.Class r11 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x0286
            int r2 = r0.getInt(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
        L_0x0283:
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0033 }
            goto L_0x02da
        L_0x0286:
            java.lang.Class r11 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x029b
            double r10 = r0.getDouble(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r2 = java.lang.Double.valueOf(r10)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0283
        L_0x029b:
            java.lang.Class r11 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x02b0
            float r2 = r0.getFloat(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0283
        L_0x02b0:
            java.lang.Class r11 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x02c5
            long r10 = r0.getLong(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r2 = java.lang.Long.valueOf(r10)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0283
        L_0x02c5:
            java.lang.Class r11 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)     // Catch:{ Exception -> 0x0033 }
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)     // Catch:{ Exception -> 0x0033 }
            if (r10 == 0) goto L_0x066b
            short r2 = r0.getShort(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r2 = java.lang.Short.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0283
        L_0x02da:
            java.lang.String r10 = r1.b(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r2 = "beacon_major"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)     // Catch:{ Exception -> 0x0033 }
            if (r12 == 0) goto L_0x02f9
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x0033 }
        L_0x02f6:
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Exception -> 0x0033 }
            goto L_0x0362
        L_0x02f9:
            java.lang.Class r12 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r12)     // Catch:{ Exception -> 0x0033 }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)     // Catch:{ Exception -> 0x0033 }
            if (r12 == 0) goto L_0x030e
            int r2 = r0.getInt(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0362
        L_0x030e:
            java.lang.Class r12 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r12)     // Catch:{ Exception -> 0x0033 }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)     // Catch:{ Exception -> 0x0033 }
            if (r12 == 0) goto L_0x0323
            double r11 = r0.getDouble(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r2 = java.lang.Double.valueOf(r11)     // Catch:{ Exception -> 0x0033 }
            goto L_0x02f6
        L_0x0323:
            java.lang.Class r12 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r12)     // Catch:{ Exception -> 0x0033 }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)     // Catch:{ Exception -> 0x0033 }
            if (r12 == 0) goto L_0x0338
            float r2 = r0.getFloat(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x02f6
        L_0x0338:
            java.lang.Class r12 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r12)     // Catch:{ Exception -> 0x0033 }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)     // Catch:{ Exception -> 0x0033 }
            if (r12 == 0) goto L_0x034d
            long r11 = r0.getLong(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r2 = java.lang.Long.valueOf(r11)     // Catch:{ Exception -> 0x0033 }
            goto L_0x02f6
        L_0x034d:
            java.lang.Class r12 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r12)     // Catch:{ Exception -> 0x0033 }
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)     // Catch:{ Exception -> 0x0033 }
            if (r11 == 0) goto L_0x0664
            short r2 = r0.getShort(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r2 = java.lang.Short.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x02f6
        L_0x0362:
            r19 = 0
            if (r2 == 0) goto L_0x036c
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x0033 }
            r11 = r2
            goto L_0x036e
        L_0x036c:
            r11 = r19
        L_0x036e:
            java.lang.String r2 = "beacon_minor"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x0389
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x0033 }
        L_0x0386:
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Exception -> 0x0033 }
            goto L_0x03f2
        L_0x0389:
            java.lang.Class r13 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x039e
            int r2 = r0.getInt(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x03f2
        L_0x039e:
            java.lang.Class r13 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x03b3
            double r12 = r0.getDouble(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r2 = java.lang.Double.valueOf(r12)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0386
        L_0x03b3:
            java.lang.Class r13 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x03c8
            float r2 = r0.getFloat(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0386
        L_0x03c8:
            java.lang.Class r13 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x03dd
            long r12 = r0.getLong(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r2 = java.lang.Long.valueOf(r12)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0386
        L_0x03dd:
            java.lang.Class r13 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)     // Catch:{ Exception -> 0x0033 }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)     // Catch:{ Exception -> 0x0033 }
            if (r12 == 0) goto L_0x065d
            short r2 = r0.getShort(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r2 = java.lang.Short.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0386
        L_0x03f2:
            if (r2 == 0) goto L_0x03fa
            int r2 = r2.intValue()     // Catch:{ Exception -> 0x0033 }
            r12 = r2
            goto L_0x03fc
        L_0x03fa:
            r12 = r19
        L_0x03fc:
            java.lang.String r2 = "location_type"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x0417
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x0033 }
        L_0x0414:
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Exception -> 0x0033 }
            goto L_0x0480
        L_0x0417:
            java.lang.Class r14 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x042c
            int r2 = r0.getInt(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0480
        L_0x042c:
            java.lang.Class r14 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x0441
            double r13 = r0.getDouble(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r2 = java.lang.Double.valueOf(r13)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0414
        L_0x0441:
            java.lang.Class r14 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x0456
            float r2 = r0.getFloat(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0414
        L_0x0456:
            java.lang.Class r14 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x046b
            long r13 = r0.getLong(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r2 = java.lang.Long.valueOf(r13)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0414
        L_0x046b:
            java.lang.Class r14 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r13 == 0) goto L_0x0656
            short r2 = r0.getShort(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r2 = java.lang.Short.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0414
        L_0x0480:
            if (r2 == 0) goto L_0x0650
            int r13 = r2.intValue()     // Catch:{ Exception -> 0x0033 }
            java.lang.String r2 = "name"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x049f
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x050a
        L_0x049f:
            java.lang.Class r14 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x04b6
            int r2 = r0.getInt(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
        L_0x04b3:
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0033 }
            goto L_0x050a
        L_0x04b6:
            java.lang.Class r14 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x04cb
            double r16 = r0.getDouble(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r2 = java.lang.Double.valueOf(r16)     // Catch:{ Exception -> 0x0033 }
            goto L_0x04b3
        L_0x04cb:
            java.lang.Class r14 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x04e0
            float r2 = r0.getFloat(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x04b3
        L_0x04e0:
            java.lang.Class r14 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r14 == 0) goto L_0x04f5
            long r16 = r0.getLong(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r2 = java.lang.Long.valueOf(r16)     // Catch:{ Exception -> 0x0033 }
            goto L_0x04b3
        L_0x04f5:
            java.lang.Class r14 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)     // Catch:{ Exception -> 0x0033 }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r14)     // Catch:{ Exception -> 0x0033 }
            if (r6 == 0) goto L_0x0649
            short r2 = r0.getShort(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r2 = java.lang.Short.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x04b3
        L_0x050a:
            java.lang.String r14 = r1.b(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r2 = "description"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            r16 = r15
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r15)     // Catch:{ Exception -> 0x0033 }
            if (r15 == 0) goto L_0x0529
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0594
        L_0x0529:
            java.lang.Class r15 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ Exception -> 0x0033 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r15)     // Catch:{ Exception -> 0x0033 }
            if (r15 == 0) goto L_0x0540
            int r2 = r0.getInt(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
        L_0x053d:
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0033 }
            goto L_0x0594
        L_0x0540:
            java.lang.Class r15 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ Exception -> 0x0033 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r15)     // Catch:{ Exception -> 0x0033 }
            if (r15 == 0) goto L_0x0555
            double r17 = r0.getDouble(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r2 = java.lang.Double.valueOf(r17)     // Catch:{ Exception -> 0x0033 }
            goto L_0x053d
        L_0x0555:
            java.lang.Class r15 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ Exception -> 0x0033 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r15)     // Catch:{ Exception -> 0x0033 }
            if (r15 == 0) goto L_0x056a
            float r2 = r0.getFloat(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x053d
        L_0x056a:
            java.lang.Class r15 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ Exception -> 0x0033 }
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r15)     // Catch:{ Exception -> 0x0033 }
            if (r15 == 0) goto L_0x057f
            long r17 = r0.getLong(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r2 = java.lang.Long.valueOf(r17)     // Catch:{ Exception -> 0x0033 }
            goto L_0x053d
        L_0x057f:
            java.lang.Class r15 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ Exception -> 0x0033 }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r15)     // Catch:{ Exception -> 0x0033 }
            if (r6 == 0) goto L_0x0641
            short r2 = r0.getShort(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r2 = java.lang.Short.valueOf(r2)     // Catch:{ Exception -> 0x0033 }
            goto L_0x053d
        L_0x0594:
            java.lang.String r15 = r1.b(r2)     // Catch:{ Exception -> 0x0033 }
            r17 = 512(0x200, float:7.175E-43)
            r18 = 0
            r1 = 0
            r6 = r5
            r2 = r16
            r16 = r1
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r1 = "is_inside"
            int r1 = r0.getColumnIndex(r1)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0033 }
            if (r4 == 0) goto L_0x05c0
            java.lang.String r0 = r0.getString(r1)     // Catch:{ Exception -> 0x0033 }
        L_0x05bd:
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x0033 }
            goto L_0x0629
        L_0x05c0:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0033 }
            if (r4 == 0) goto L_0x05d5
            int r0 = r0.getInt(r1)     // Catch:{ Exception -> 0x0033 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0629
        L_0x05d5:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0033 }
            if (r4 == 0) goto L_0x05ea
            double r0 = r0.getDouble(r1)     // Catch:{ Exception -> 0x0033 }
            java.lang.Double r0 = java.lang.Double.valueOf(r0)     // Catch:{ Exception -> 0x0033 }
            goto L_0x05bd
        L_0x05ea:
            java.lang.Class r4 = java.lang.Float.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0033 }
            if (r4 == 0) goto L_0x05ff
            float r0 = r0.getFloat(r1)     // Catch:{ Exception -> 0x0033 }
            java.lang.Float r0 = java.lang.Float.valueOf(r0)     // Catch:{ Exception -> 0x0033 }
            goto L_0x05bd
        L_0x05ff:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0033 }
            if (r4 == 0) goto L_0x0614
            long r0 = r0.getLong(r1)     // Catch:{ Exception -> 0x0033 }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ Exception -> 0x0033 }
            goto L_0x05bd
        L_0x0614:
            java.lang.Class r4 = java.lang.Short.TYPE     // Catch:{ Exception -> 0x0033 }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ Exception -> 0x0033 }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0033 }
            if (r3 == 0) goto L_0x063b
            short r0 = r0.getShort(r1)     // Catch:{ Exception -> 0x0033 }
            java.lang.Short r0 = java.lang.Short.valueOf(r0)     // Catch:{ Exception -> 0x0033 }
            goto L_0x05bd
        L_0x0629:
            if (r0 != 0) goto L_0x062c
            goto L_0x0634
        L_0x062c:
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x0033 }
            r1 = 1
            if (r0 != r1) goto L_0x0634
            goto L_0x0636
        L_0x0634:
            r1 = r19
        L_0x0636:
            r5.m692isInside(r1)     // Catch:{ Exception -> 0x0033 }
            goto L_0x06b5
        L_0x063b:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0641:
            r2 = r16
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0649:
            r2 = r15
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0650:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0656:
            r2 = r15
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x065d:
            r2 = r15
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0664:
            r2 = r15
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x066b:
            r2 = r15
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0672:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0678:
            r2 = r15
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x067f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0685:
            r2 = r15
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x068c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0692:
            r2 = r15
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x0699:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x069f:
            r2 = r15
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0033 }
            throw r0     // Catch:{ Exception -> 0x0033 }
        L_0x06a6:
            com.salesforce.marketingcloud.g r1 = com.salesforce.marketingcloud.g.a
            java.lang.String r2 = com.salesforce.marketingcloud.storage.db.j.g
            java.lang.String r3 = "TAG"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            com.salesforce.marketingcloud.storage.db.d$b r3 = com.salesforce.marketingcloud.storage.db.d.b.a
            r1.b((java.lang.String) r2, (java.lang.Throwable) r0, (kotlin.jvm.functions.Function0) r3)
            r5 = 0
        L_0x06b5:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.d.c(android.database.Cursor, com.salesforce.marketingcloud.util.c):com.salesforce.marketingcloud.messages.Region");
    }

    /* JADX WARNING: Removed duplicated region for block: B:145:0x03d6  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x03d8  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x03ef  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x03f6  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x0469  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x046b  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0482  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0489  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x04fc  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x04ff  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0515  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x051c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x0589  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0617  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x061a  */
    /* JADX WARNING: Removed duplicated region for block: B:242:0x0630  */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x0637  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x06a2  */
    /* JADX WARNING: Removed duplicated region for block: B:433:0x0b08  */
    /* JADX WARNING: Removed duplicated region for block: B:439:0x0b20  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:459:0x0b6a  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.salesforce.marketingcloud.registration.Registration d(android.database.Cursor r27, com.salesforce.marketingcloud.util.c r28) throws java.lang.Exception {
        /*
            r0 = r27
            r1 = r28
            java.lang.String r2 = "cursor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            java.lang.String r2 = "crypto"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            com.salesforce.marketingcloud.registration.Registration r2 = new com.salesforce.marketingcloud.registration.Registration
            java.lang.String r3 = "id"
            int r3 = r0.getColumnIndex(r3)
            java.lang.Class<java.lang.Integer> r4 = java.lang.Integer.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r7)
            java.lang.String r8 = "Unsupported type"
            if (r7 == 0) goto L_0x0031
            java.lang.String r3 = r0.getString(r3)
        L_0x002e:
            java.lang.Integer r3 = (java.lang.Integer) r3
            goto L_0x009a
        L_0x0031:
            java.lang.Class r7 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x0046
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x009a
        L_0x0046:
            java.lang.Class r7 = java.lang.Double.TYPE
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x005b
            double r9 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r9)
            goto L_0x002e
        L_0x005b:
            java.lang.Class r7 = java.lang.Float.TYPE
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x0070
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x002e
        L_0x0070:
            java.lang.Class r7 = java.lang.Long.TYPE
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x0085
            long r9 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r9)
            goto L_0x002e
        L_0x0085:
            java.lang.Class r7 = java.lang.Short.TYPE
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r7)
            if (r5 == 0) goto L_0x0b7f
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x002e
        L_0x009a:
            if (r3 == 0) goto L_0x00a2
            int r3 = r3.intValue()
            r7 = r3
            goto L_0x00a3
        L_0x00a2:
            r7 = 0
        L_0x00a3:
            java.lang.String r3 = "signed_string"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x00bc
            java.lang.String r3 = r0.getString(r3)
            goto L_0x0127
        L_0x00bc:
            java.lang.Class r10 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x00d3
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x00d0:
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x0127
        L_0x00d3:
            java.lang.Class r10 = java.lang.Double.TYPE
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x00e8
            double r9 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r9)
            goto L_0x00d0
        L_0x00e8:
            java.lang.Class r10 = java.lang.Float.TYPE
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x00fd
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x00d0
        L_0x00fd:
            java.lang.Class r10 = java.lang.Long.TYPE
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x0112
            long r9 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r9)
            goto L_0x00d0
        L_0x0112:
            java.lang.Class r10 = java.lang.Short.TYPE
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r9 == 0) goto L_0x0b78
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x00d0
        L_0x0127:
            java.lang.String r9 = r1.b(r3)
            java.lang.String r3 = "device_id"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)
            if (r11 == 0) goto L_0x0145
            java.lang.String r3 = r0.getString(r3)
        L_0x0143:
            r10 = r3
            goto L_0x01b0
        L_0x0145:
            java.lang.Class r11 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)
            if (r11 == 0) goto L_0x015c
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x0159:
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x0143
        L_0x015c:
            java.lang.Class r11 = java.lang.Double.TYPE
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)
            if (r11 == 0) goto L_0x0171
            double r10 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r10)
            goto L_0x0159
        L_0x0171:
            java.lang.Class r11 = java.lang.Float.TYPE
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)
            if (r11 == 0) goto L_0x0186
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x0159
        L_0x0186:
            java.lang.Class r11 = java.lang.Long.TYPE
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)
            if (r11 == 0) goto L_0x019b
            long r10 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r10)
            goto L_0x0159
        L_0x019b:
            java.lang.Class r11 = java.lang.Short.TYPE
            kotlin.reflect.KClass r11 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r11)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)
            if (r10 == 0) goto L_0x0b71
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x0159
        L_0x01b0:
            java.lang.String r3 = "Required value was null."
            if (r10 == 0) goto L_0x0b6a
            java.lang.String r11 = "system_token"
            int r11 = r0.getColumnIndex(r11)
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)
            if (r13 == 0) goto L_0x01cd
            java.lang.String r11 = r0.getString(r11)
            goto L_0x0238
        L_0x01cd:
            java.lang.Class r13 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)
            if (r13 == 0) goto L_0x01e4
            int r11 = r0.getInt(r11)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
        L_0x01e1:
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x0238
        L_0x01e4:
            java.lang.Class r13 = java.lang.Double.TYPE
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)
            if (r13 == 0) goto L_0x01f9
            double r11 = r0.getDouble(r11)
            java.lang.Double r11 = java.lang.Double.valueOf(r11)
            goto L_0x01e1
        L_0x01f9:
            java.lang.Class r13 = java.lang.Float.TYPE
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)
            if (r13 == 0) goto L_0x020e
            float r11 = r0.getFloat(r11)
            java.lang.Float r11 = java.lang.Float.valueOf(r11)
            goto L_0x01e1
        L_0x020e:
            java.lang.Class r13 = java.lang.Long.TYPE
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)
            if (r13 == 0) goto L_0x0223
            long r11 = r0.getLong(r11)
            java.lang.Long r11 = java.lang.Long.valueOf(r11)
            goto L_0x01e1
        L_0x0223:
            java.lang.Class r13 = java.lang.Short.TYPE
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r13)
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)
            if (r12 == 0) goto L_0x0b63
            short r11 = r0.getShort(r11)
            java.lang.Short r11 = java.lang.Short.valueOf(r11)
            goto L_0x01e1
        L_0x0238:
            java.lang.String r11 = r1.b(r11)
            java.lang.String r12 = "sdk_version"
            int r12 = r0.getColumnIndex(r12)
            kotlin.reflect.KClass r13 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x0255
            java.lang.String r12 = r0.getString(r12)
            goto L_0x02c0
        L_0x0255:
            java.lang.Class r14 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x026c
            int r12 = r0.getInt(r12)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
        L_0x0269:
            java.lang.String r12 = (java.lang.String) r12
            goto L_0x02c0
        L_0x026c:
            java.lang.Class r14 = java.lang.Double.TYPE
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x0281
            double r12 = r0.getDouble(r12)
            java.lang.Double r12 = java.lang.Double.valueOf(r12)
            goto L_0x0269
        L_0x0281:
            java.lang.Class r14 = java.lang.Float.TYPE
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x0296
            float r12 = r0.getFloat(r12)
            java.lang.Float r12 = java.lang.Float.valueOf(r12)
            goto L_0x0269
        L_0x0296:
            java.lang.Class r14 = java.lang.Long.TYPE
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x02ab
            long r12 = r0.getLong(r12)
            java.lang.Long r12 = java.lang.Long.valueOf(r12)
            goto L_0x0269
        L_0x02ab:
            java.lang.Class r14 = java.lang.Short.TYPE
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r14)
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)
            if (r13 == 0) goto L_0x0b5c
            short r12 = r0.getShort(r12)
            java.lang.Short r12 = java.lang.Short.valueOf(r12)
            goto L_0x0269
        L_0x02c0:
            if (r12 == 0) goto L_0x0b55
            java.lang.String r13 = "app_version"
            int r13 = r0.getColumnIndex(r13)
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x02db
            java.lang.String r13 = r0.getString(r13)
            goto L_0x0346
        L_0x02db:
            java.lang.Class r15 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x02f2
            int r13 = r0.getInt(r13)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
        L_0x02ef:
            java.lang.String r13 = (java.lang.String) r13
            goto L_0x0346
        L_0x02f2:
            java.lang.Class r15 = java.lang.Double.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x0307
            double r13 = r0.getDouble(r13)
            java.lang.Double r13 = java.lang.Double.valueOf(r13)
            goto L_0x02ef
        L_0x0307:
            java.lang.Class r15 = java.lang.Float.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x031c
            float r13 = r0.getFloat(r13)
            java.lang.Float r13 = java.lang.Float.valueOf(r13)
            goto L_0x02ef
        L_0x031c:
            java.lang.Class r15 = java.lang.Long.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r15 == 0) goto L_0x0331
            long r13 = r0.getLong(r13)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            goto L_0x02ef
        L_0x0331:
            java.lang.Class r15 = java.lang.Short.TYPE
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r14 == 0) goto L_0x0b4e
            short r13 = r0.getShort(r13)
            java.lang.Short r13 = java.lang.Short.valueOf(r13)
            goto L_0x02ef
        L_0x0346:
            if (r13 == 0) goto L_0x0b47
            java.lang.String r14 = "dst"
            int r14 = r0.getColumnIndex(r14)
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0363
            java.lang.String r5 = r0.getString(r14)
        L_0x0360:
            java.lang.Integer r5 = (java.lang.Integer) r5
            goto L_0x03cc
        L_0x0363:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0378
            int r5 = r0.getInt(r14)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto L_0x03cc
        L_0x0378:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x038d
            double r14 = r0.getDouble(r14)
            java.lang.Double r5 = java.lang.Double.valueOf(r14)
            goto L_0x0360
        L_0x038d:
            java.lang.Class r5 = java.lang.Float.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x03a2
            float r5 = r0.getFloat(r14)
            java.lang.Float r5 = java.lang.Float.valueOf(r5)
            goto L_0x0360
        L_0x03a2:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x03b7
            long r14 = r0.getLong(r14)
            java.lang.Long r5 = java.lang.Long.valueOf(r14)
            goto L_0x0360
        L_0x03b7:
            java.lang.Class r5 = java.lang.Short.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0b40
            short r5 = r0.getShort(r14)
            java.lang.Short r5 = java.lang.Short.valueOf(r5)
            goto L_0x0360
        L_0x03cc:
            r14 = 1
            if (r5 != 0) goto L_0x03d0
            goto L_0x03d8
        L_0x03d0:
            int r5 = r5.intValue()
            if (r5 != r14) goto L_0x03d8
            r15 = r14
            goto L_0x03d9
        L_0x03d8:
            r15 = 0
        L_0x03d9:
            java.lang.String r5 = "location_enabled"
            int r5 = r0.getColumnIndex(r5)
            kotlin.reflect.KClass r14 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            r18 = r8
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x03f6
            java.lang.String r5 = r0.getString(r5)
        L_0x03f3:
            java.lang.Integer r5 = (java.lang.Integer) r5
            goto L_0x045f
        L_0x03f6:
            java.lang.Class r8 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x040b
            int r5 = r0.getInt(r5)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto L_0x045f
        L_0x040b:
            java.lang.Class r8 = java.lang.Double.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x0420
            double r19 = r0.getDouble(r5)
            java.lang.Double r5 = java.lang.Double.valueOf(r19)
            goto L_0x03f3
        L_0x0420:
            java.lang.Class r8 = java.lang.Float.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x0435
            float r5 = r0.getFloat(r5)
            java.lang.Float r5 = java.lang.Float.valueOf(r5)
            goto L_0x03f3
        L_0x0435:
            java.lang.Class r8 = java.lang.Long.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x044a
            long r19 = r0.getLong(r5)
            java.lang.Long r5 = java.lang.Long.valueOf(r19)
            goto L_0x03f3
        L_0x044a:
            java.lang.Class r8 = java.lang.Short.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x0b38
            short r5 = r0.getShort(r5)
            java.lang.Short r5 = java.lang.Short.valueOf(r5)
            goto L_0x03f3
        L_0x045f:
            if (r5 != 0) goto L_0x0462
            goto L_0x046b
        L_0x0462:
            int r5 = r5.intValue()
            r8 = 1
            if (r5 != r8) goto L_0x046b
            r14 = 1
            goto L_0x046c
        L_0x046b:
            r14 = 0
        L_0x046c:
            java.lang.String r5 = "proximity_enabled"
            int r5 = r0.getColumnIndex(r5)
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            r19 = r3
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x0489
            java.lang.String r3 = r0.getString(r5)
        L_0x0486:
            java.lang.Integer r3 = (java.lang.Integer) r3
            goto L_0x04f2
        L_0x0489:
            java.lang.Class r3 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x049e
            int r3 = r0.getInt(r5)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x04f2
        L_0x049e:
            java.lang.Class r3 = java.lang.Double.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x04b3
            double r20 = r0.getDouble(r5)
            java.lang.Double r3 = java.lang.Double.valueOf(r20)
            goto L_0x0486
        L_0x04b3:
            java.lang.Class r3 = java.lang.Float.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x04c8
            float r3 = r0.getFloat(r5)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x0486
        L_0x04c8:
            java.lang.Class r3 = java.lang.Long.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x04dd
            long r20 = r0.getLong(r5)
            java.lang.Long r3 = java.lang.Long.valueOf(r20)
            goto L_0x0486
        L_0x04dd:
            java.lang.Class r3 = java.lang.Short.TYPE
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x0b30
            short r3 = r0.getShort(r5)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x0486
        L_0x04f2:
            if (r3 != 0) goto L_0x04f5
            goto L_0x04ff
        L_0x04f5:
            int r3 = r3.intValue()
            r5 = 1
            if (r3 != r5) goto L_0x04ff
            r20 = 1
            goto L_0x0501
        L_0x04ff:
            r20 = 0
        L_0x0501:
            java.lang.String r3 = "platform_version"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x051c
            java.lang.String r3 = r0.getString(r3)
        L_0x0519:
            r21 = r3
            goto L_0x0587
        L_0x051c:
            java.lang.Class r8 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x0533
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x0530:
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x0519
        L_0x0533:
            java.lang.Class r8 = java.lang.Double.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x0548
            double r21 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r21)
            goto L_0x0530
        L_0x0548:
            java.lang.Class r8 = java.lang.Float.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x055d
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x0530
        L_0x055d:
            java.lang.Class r8 = java.lang.Long.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x0572
            long r21 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r21)
            goto L_0x0530
        L_0x0572:
            java.lang.Class r8 = java.lang.Short.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r5 == 0) goto L_0x0b28
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x0530
        L_0x0587:
            if (r21 == 0) goto L_0x0b20
            java.lang.String r3 = "push_enabled"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x05a4
            java.lang.String r3 = r0.getString(r3)
        L_0x05a1:
            java.lang.Integer r3 = (java.lang.Integer) r3
            goto L_0x060d
        L_0x05a4:
            java.lang.Class r8 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x05b9
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x060d
        L_0x05b9:
            java.lang.Class r8 = java.lang.Double.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x05ce
            double r22 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r22)
            goto L_0x05a1
        L_0x05ce:
            java.lang.Class r8 = java.lang.Float.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x05e3
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x05a1
        L_0x05e3:
            java.lang.Class r8 = java.lang.Long.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r8 == 0) goto L_0x05f8
            long r22 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r22)
            goto L_0x05a1
        L_0x05f8:
            java.lang.Class r8 = java.lang.Short.TYPE
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r8)
            if (r5 == 0) goto L_0x0b18
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x05a1
        L_0x060d:
            if (r3 != 0) goto L_0x0610
            goto L_0x061a
        L_0x0610:
            int r3 = r3.intValue()
            r5 = 1
            if (r3 != r5) goto L_0x061a
            r16 = r5
            goto L_0x061c
        L_0x061a:
            r16 = 0
        L_0x061c:
            java.lang.String r3 = "timezone"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0637
            java.lang.String r3 = r0.getString(r3)
        L_0x0634:
            java.lang.Integer r3 = (java.lang.Integer) r3
            goto L_0x06a0
        L_0x0637:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x064c
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x06a0
        L_0x064c:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0661
            double r3 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            goto L_0x0634
        L_0x0661:
            java.lang.Class r5 = java.lang.Float.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0676
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x0634
        L_0x0676:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x068b
            long r3 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x0634
        L_0x068b:
            java.lang.Class r5 = java.lang.Short.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0b10
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x0634
        L_0x06a0:
            if (r3 == 0) goto L_0x0b08
            int r17 = r3.intValue()
            java.lang.String r3 = "subscriber_key"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x06bf
            java.lang.String r3 = r0.getString(r3)
            goto L_0x072a
        L_0x06bf:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x06d6
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x06d3:
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x072a
        L_0x06d6:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x06eb
            double r3 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            goto L_0x06d3
        L_0x06eb:
            java.lang.Class r5 = java.lang.Float.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0700
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x06d3
        L_0x0700:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0715
            long r3 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x06d3
        L_0x0715:
            java.lang.Class r5 = java.lang.Short.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0b00
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x06d3
        L_0x072a:
            java.lang.String r23 = r1.b(r3)
            java.lang.String r3 = "platform"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0749
            java.lang.String r3 = r0.getString(r3)
        L_0x0746:
            r24 = r3
            goto L_0x07b4
        L_0x0749:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0760
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x075d:
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x0746
        L_0x0760:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0775
            double r3 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            goto L_0x075d
        L_0x0775:
            java.lang.Class r5 = java.lang.Float.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x078a
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x075d
        L_0x078a:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x079f
            long r3 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x075d
        L_0x079f:
            java.lang.Class r5 = java.lang.Short.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0af8
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x075d
        L_0x07b4:
            if (r24 == 0) goto L_0x0af0
            java.lang.String r3 = "hwid"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x07d1
            java.lang.String r3 = r0.getString(r3)
        L_0x07ce:
            r25 = r3
            goto L_0x083c
        L_0x07d1:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x07e8
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x07e5:
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x07ce
        L_0x07e8:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x07fd
            double r3 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            goto L_0x07e5
        L_0x07fd:
            java.lang.Class r5 = java.lang.Float.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0812
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x07e5
        L_0x0812:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0827
            long r3 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x07e5
        L_0x0827:
            java.lang.Class r5 = java.lang.Short.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0ae8
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x07e5
        L_0x083c:
            if (r25 == 0) goto L_0x0ae0
            java.lang.String r3 = "et_app_id"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0857
            java.lang.String r3 = r0.getString(r3)
            goto L_0x08c2
        L_0x0857:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x086e
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x086b:
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x08c2
        L_0x086e:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0883
            double r3 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            goto L_0x086b
        L_0x0883:
            java.lang.Class r5 = java.lang.Float.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0898
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x086b
        L_0x0898:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x08ad
            long r3 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x086b
        L_0x08ad:
            java.lang.Class r5 = java.lang.Short.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0ad8
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x086b
        L_0x08c2:
            java.lang.String r8 = r1.b(r3)
            if (r8 == 0) goto L_0x0ad0
            java.lang.String r3 = "checkNotNull(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r3)
            java.lang.String r3 = "locale"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x08e8
            java.lang.String r3 = r0.getString(r3)
        L_0x08e5:
            r26 = r3
            goto L_0x0953
        L_0x08e8:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x08ff
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x08fc:
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x08e5
        L_0x08ff:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0914
            double r3 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            goto L_0x08fc
        L_0x0914:
            java.lang.Class r5 = java.lang.Float.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0929
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x08fc
        L_0x0929:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x093e
            long r3 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x08fc
        L_0x093e:
            java.lang.Class r5 = java.lang.Short.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0ac8
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x08fc
        L_0x0953:
            if (r26 == 0) goto L_0x0ac0
            java.lang.String r3 = "tags"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x096e
            java.lang.String r3 = r0.getString(r3)
            goto L_0x09d9
        L_0x096e:
            java.lang.Class r5 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0985
            int r3 = r0.getInt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L_0x0982:
            java.lang.String r3 = (java.lang.String) r3
            goto L_0x09d9
        L_0x0985:
            java.lang.Class r5 = java.lang.Double.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x099a
            double r3 = r0.getDouble(r3)
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            goto L_0x0982
        L_0x099a:
            java.lang.Class r5 = java.lang.Float.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x09af
            float r3 = r0.getFloat(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x0982
        L_0x09af:
            java.lang.Class r5 = java.lang.Long.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x09c4
            long r3 = r0.getLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            goto L_0x0982
        L_0x09c4:
            java.lang.Class r5 = java.lang.Short.TYPE
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0ab8
            short r3 = r0.getShort(r3)
            java.lang.Short r3 = java.lang.Short.valueOf(r3)
            goto L_0x0982
        L_0x09d9:
            java.lang.String r3 = r1.b(r3)
            if (r3 == 0) goto L_0x0ab0
            java.util.Set r5 = com.salesforce.marketingcloud.util.l.d(r3)
            java.lang.String r3 = "deserializeTags(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r3)
            java.lang.String r3 = "attributes"
            int r3 = r0.getColumnIndex(r3)
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0a01
            java.lang.String r0 = r0.getString(r3)
            goto L_0x0a6c
        L_0x0a01:
            java.lang.Class r6 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0a18
            int r0 = r0.getInt(r3)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L_0x0a15:
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0a6c
        L_0x0a18:
            java.lang.Class r6 = java.lang.Double.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0a2d
            double r3 = r0.getDouble(r3)
            java.lang.Double r0 = java.lang.Double.valueOf(r3)
            goto L_0x0a15
        L_0x0a2d:
            java.lang.Class r6 = java.lang.Float.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0a42
            float r0 = r0.getFloat(r3)
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            goto L_0x0a15
        L_0x0a42:
            java.lang.Class r6 = java.lang.Long.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0a57
            long r3 = r0.getLong(r3)
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
            goto L_0x0a15
        L_0x0a57:
            java.lang.Class r6 = java.lang.Short.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r4 == 0) goto L_0x0aa8
            short r0 = r0.getShort(r3)
            java.lang.Short r0 = java.lang.Short.valueOf(r0)
            goto L_0x0a15
        L_0x0a6c:
            java.lang.String r0 = r1.b(r0)
            if (r0 == 0) goto L_0x0aa0
            java.util.Map r0 = com.salesforce.marketingcloud.util.l.c((java.lang.String) r0)
            r22 = r0
            java.lang.String r1 = "deserializeKeys(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r3 = r2
            r4 = r7
            r0 = r5
            r5 = r9
            r6 = r10
            r7 = r11
            r1 = r8
            r8 = r12
            r9 = r13
            r10 = r15
            r11 = r14
            r12 = r20
            r13 = r21
            r14 = r16
            r15 = r17
            r16 = r23
            r17 = r24
            r18 = r25
            r19 = r1
            r20 = r26
            r21 = r0
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r2
        L_0x0aa0:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r19
            r0.<init>(r1)
            throw r0
        L_0x0aa8:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r1 = r18
            r0.<init>(r1)
            throw r0
        L_0x0ab0:
            r1 = r19
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0ab8:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0ac0:
            r1 = r19
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0ac8:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0ad0:
            r1 = r19
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0ad8:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0ae0:
            r1 = r19
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0ae8:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0af0:
            r1 = r19
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0af8:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b00:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b08:
            r1 = r19
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0b10:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b18:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b20:
            r1 = r19
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0b28:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b30:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b38:
            r1 = r18
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b40:
            r1 = r8
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b47:
            r1 = r3
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0b4e:
            r1 = r8
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b55:
            r1 = r3
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0b5c:
            r1 = r8
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b63:
            r1 = r8
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b6a:
            r1 = r3
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0b71:
            r1 = r8
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b78:
            r1 = r8
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        L_0x0b7f:
            r1 = r8
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.d.d(android.database.Cursor, com.salesforce.marketingcloud.util.c):com.salesforce.marketingcloud.registration.Registration");
    }

    private static final /* synthetic */ <T> T a(Cursor cursor, String str) {
        T valueOf;
        int columnIndex = cursor.getColumnIndex(str);
        Intrinsics.reifiedOperationMarker(4, "T");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            valueOf = cursor.getString(columnIndex);
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            valueOf = Integer.valueOf(cursor.getInt(columnIndex));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            valueOf = Double.valueOf(cursor.getDouble(columnIndex));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            valueOf = Float.valueOf(cursor.getFloat(columnIndex));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            valueOf = Long.valueOf(cursor.getLong(columnIndex));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Short.TYPE))) {
            valueOf = Short.valueOf(cursor.getShort(columnIndex));
        } else {
            throw new UnsupportedOperationException("Unsupported type");
        }
        Intrinsics.reifiedOperationMarker(1, "T?");
        return valueOf;
    }

    public static final ContentValues a(InboxMessage inboxMessage, com.salesforce.marketingcloud.util.c cVar) {
        Intrinsics.checkNotNullParameter(inboxMessage, StackTraceHelper.MESSAGE_KEY);
        Intrinsics.checkNotNullParameter(cVar, "crypto");
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", inboxMessage.id());
            contentValues.put("start_date", inboxMessage.startDateUtc() != null ? Long.valueOf(inboxMessage.startDateUtc().getTime()) : null);
            contentValues.put("end_date", inboxMessage.endDateUtc() != null ? Long.valueOf(inboxMessage.endDateUtc().getTime()) : null);
            contentValues.put("is_read", Integer.valueOf(inboxMessage.read() ? 1 : 0));
            contentValues.put("is_deleted", Integer.valueOf(inboxMessage.deleted() ? 1 : 0));
            contentValues.put("message_hash", inboxMessage.m764messageHash());
            contentValues.put("message_json", cVar.a(inboxMessage.toJson$sdk_release().toString()));
            if (inboxMessage.m763dirty()) {
                contentValues.put("is_dirty", 1);
            }
            return contentValues;
        } catch (Exception e) {
            g gVar = g.a;
            String str = g.g;
            Intrinsics.checkNotNullExpressionValue(str, "TAG");
            gVar.b(str, (Throwable) e, (Function0) c.a);
            return null;
        }
    }
}
