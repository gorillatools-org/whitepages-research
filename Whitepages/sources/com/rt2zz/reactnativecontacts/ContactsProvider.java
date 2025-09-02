package com.rt2zz.reactnativecontacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.common.Scopes;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactsProvider {
    private static final List FULL_PROJECTION = new ArrayList() {
        {
            addAll(ContactsProvider.JUST_ME_PROJECTION);
        }
    };
    /* access modifiers changed from: private */
    public static final List JUST_ME_PROJECTION = new ArrayList() {
        {
            add("_id");
            add("contact_id");
            add("raw_contact_id");
            add(Constants.LOCALEMATCHER_LOOKUP);
            add("starred");
            add("mimetype");
            add("display_name");
            add("photo_uri");
            add("data1");
            add("data2");
            add("data5");
            add("data3");
            add("data4");
            add("data6");
            add("data1");
            add("data4");
            add("data2");
            add("data3");
            add("data1");
            add("data1");
            add("data2");
            add("data3");
            add("data1");
            add("data4");
            add("data5");
            add("data1");
            add("data2");
            add("data3");
            add("data4");
            add("data5");
            add("data6");
            add("data7");
            add("data8");
            add("data9");
            add("data10");
            add("data1");
            add("data1");
            add("data1");
            add("data1");
            add("data2");
        }
    };
    private static final List PHOTO_PROJECTION = new ArrayList() {
        {
            add("photo_uri");
        }
    };
    private final ContentResolver contentResolver;

    public ContactsProvider(ContentResolver contentResolver2) {
        this.contentResolver = contentResolver2;
    }

    public WritableArray getContactsMatchingString(String str) {
        ContentResolver contentResolver2 = this.contentResolver;
        Uri uri = ContactsContract.Data.CONTENT_URI;
        List list = FULL_PROJECTION;
        Cursor query = contentResolver2.query(uri, (String[]) list.toArray(new String[list.size()]), "display_name LIKE ? OR data1 LIKE ?", new String[]{"%" + str + "%", "%" + str + "%"}, (String) null);
        try {
            Map loadContactsFrom = loadContactsFrom(query);
            WritableArray createArray = Arguments.createArray();
            for (Contact map : loadContactsFrom.values()) {
                createArray.pushMap(map.toMap());
            }
            return createArray;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public WritableArray getContactsByPhoneNumber(String str) {
        ContentResolver contentResolver2 = this.contentResolver;
        Uri uri = ContactsContract.Data.CONTENT_URI;
        List list = FULL_PROJECTION;
        Cursor query = contentResolver2.query(uri, (String[]) list.toArray(new String[list.size()]), "data1 LIKE ? OR data4 LIKE ?", new String[]{"%" + str + "%", "%" + str + "%"}, (String) null);
        try {
            Map loadContactsFrom = loadContactsFrom(query);
            WritableArray createArray = Arguments.createArray();
            for (Contact map : loadContactsFrom.values()) {
                createArray.pushMap(map.toMap());
            }
            return createArray;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public WritableArray getContactsByEmailAddress(String str) {
        ContentResolver contentResolver2 = this.contentResolver;
        Uri uri = ContactsContract.Data.CONTENT_URI;
        List list = FULL_PROJECTION;
        Cursor query = contentResolver2.query(uri, (String[]) list.toArray(new String[list.size()]), "data1 LIKE ?", new String[]{"%" + str + "%"}, (String) null);
        try {
            Map loadContactsFrom = loadContactsFrom(query);
            WritableArray createArray = Arguments.createArray();
            for (Contact map : loadContactsFrom.values()) {
                createArray.pushMap(map.toMap());
            }
            return createArray;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0022, code lost:
        r0 = r8.getColumnIndex("contact_id");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.WritableMap getContactByRawId(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "contact_id"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            java.lang.String[] r5 = new java.lang.String[]{r8}
            android.content.ContentResolver r1 = r7.contentResolver
            android.net.Uri r2 = android.provider.ContactsContract.RawContacts.CONTENT_URI
            r6 = 0
            java.lang.String r4 = "_id= ?"
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)
            r8.getCount()
            java.lang.Boolean r1 = r7.cursorMoveToNext(r8)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x002f
            int r0 = r8.getColumnIndex(r0)
            r1 = -1
            if (r0 != r1) goto L_0x002a
            goto L_0x002f
        L_0x002a:
            java.lang.String r0 = r8.getString(r0)
            goto L_0x0030
        L_0x002f:
            r0 = 0
        L_0x0030:
            r8.close()
            com.facebook.react.bridge.WritableMap r8 = r7.getContactById(r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rt2zz.reactnativecontacts.ContactsProvider.getContactByRawId(java.lang.String):com.facebook.react.bridge.WritableMap");
    }

    public WritableMap getContactById(String str) {
        ContentResolver contentResolver2 = this.contentResolver;
        Uri uri = ContactsContract.Data.CONTENT_URI;
        List list = FULL_PROJECTION;
        Cursor query = contentResolver2.query(uri, (String[]) list.toArray(new String[list.size()]), "contact_id = ?", new String[]{str}, (String) null);
        try {
            Map loadContactsFrom = loadContactsFrom(query);
            if (loadContactsFrom.values().size() > 0) {
                return ((Contact) loadContactsFrom.values().iterator().next()).toMap();
            }
            return null;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public Integer getContactsCount() {
        return Integer.valueOf(this.contentResolver.query(ContactsContract.Contacts.CONTENT_URI, (String[]) null, (String) null, (String[]) null, (String) null).getCount());
    }

    public WritableArray getContacts() {
        ContentResolver contentResolver2 = this.contentResolver;
        Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        List list = JUST_ME_PROJECTION;
        Cursor query = contentResolver2.query(withAppendedPath, (String[]) list.toArray(new String[list.size()]), (String) null, (String[]) null, (String) null);
        try {
            Map loadContactsFrom = loadContactsFrom(query);
            ContentResolver contentResolver3 = this.contentResolver;
            Uri uri = ContactsContract.Data.CONTENT_URI;
            List list2 = FULL_PROJECTION;
            Cursor query2 = contentResolver3.query(uri, (String[]) list2.toArray(new String[list2.size()]), "mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=?", new String[]{"vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/name", "vnd.android.cursor.item/organization", "vnd.android.cursor.item/postal-address_v2", "vnd.android.cursor.item/note", "vnd.android.cursor.item/website", "vnd.android.cursor.item/im", "vnd.android.cursor.item/contact_event"}, (String) null);
            try {
                Map loadContactsFrom2 = loadContactsFrom(query2);
                WritableArray createArray = Arguments.createArray();
                for (Contact map : loadContactsFrom.values()) {
                    createArray.pushMap(map.toMap());
                }
                for (Contact map2 : loadContactsFrom2.values()) {
                    createArray.pushMap(map2.toMap());
                }
                return createArray;
            } finally {
                if (query2 != null) {
                    query2.close();
                }
            }
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    private Boolean cursorMoveToNext(Cursor cursor) {
        try {
            return Boolean.valueOf(cursor.moveToNext());
        } catch (RuntimeException unused) {
            return Boolean.FALSE;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x038a, code lost:
        if (r2 != 4) goto L_0x038c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0142, code lost:
        r5 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map loadContactsFrom(android.database.Cursor r19) {
        /*
            r18 = this;
            r1 = r19
            r5 = 1
            r7 = -1
            java.util.LinkedHashMap r8 = new java.util.LinkedHashMap
            r8.<init>()
        L_0x0009:
            if (r1 == 0) goto L_0x03c5
            java.lang.Boolean r0 = r18.cursorMoveToNext(r19)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x03c5
            java.lang.String r0 = "contact_id"
            int r0 = r1.getColumnIndex(r0)
            java.lang.String r9 = "_id"
            int r9 = r1.getColumnIndex(r9)
            java.lang.String r10 = "raw_contact_id"
            int r10 = r1.getColumnIndex(r10)
            if (r0 == r7) goto L_0x002e
            java.lang.String r0 = r1.getString(r0)
            goto L_0x0032
        L_0x002e:
            java.lang.String r0 = java.lang.String.valueOf(r7)
        L_0x0032:
            if (r9 == r7) goto L_0x0039
            java.lang.String r9 = r1.getString(r9)
            goto L_0x003d
        L_0x0039:
            java.lang.String r9 = java.lang.String.valueOf(r7)
        L_0x003d:
            if (r10 == r7) goto L_0x0044
            java.lang.String r10 = r1.getString(r10)
            goto L_0x0048
        L_0x0044:
            java.lang.String r10 = java.lang.String.valueOf(r7)
        L_0x0048:
            boolean r11 = r8.containsKey(r0)
            if (r11 != 0) goto L_0x0056
            com.rt2zz.reactnativecontacts.ContactsProvider$Contact r11 = new com.rt2zz.reactnativecontacts.ContactsProvider$Contact
            r11.<init>(r0)
            r8.put(r0, r11)
        L_0x0056:
            java.lang.Object r0 = r8.get(r0)
            com.rt2zz.reactnativecontacts.ContactsProvider$Contact r0 = (com.rt2zz.reactnativecontacts.ContactsProvider.Contact) r0
            java.lang.String r11 = "mimetype"
            int r11 = r1.getColumnIndex(r11)
            java.lang.String r11 = r1.getString(r11)
            java.lang.String r12 = "display_name"
            int r12 = r1.getColumnIndex(r12)
            java.lang.String r12 = r1.getString(r12)
            java.lang.String r13 = "starred"
            int r13 = r1.getColumnIndex(r13)
            int r13 = r1.getInt(r13)
            if (r13 != r5) goto L_0x007e
            r13 = r5
            goto L_0x007f
        L_0x007e:
            r13 = 0
        L_0x007f:
            java.lang.String unused = r0.rawContactId = r10
            boolean r10 = android.text.TextUtils.isEmpty(r12)
            if (r10 != 0) goto L_0x0095
            java.lang.String r10 = r0.displayName
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 == 0) goto L_0x0095
            java.lang.String unused = r0.displayName = r12
        L_0x0095:
            boolean unused = r0.isStarred = r13
            java.lang.String r10 = r0.photoUri
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 == 0) goto L_0x00b8
            java.lang.String r10 = "photo_uri"
            int r10 = r1.getColumnIndex(r10)
            java.lang.String r10 = r1.getString(r10)
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 != 0) goto L_0x00b8
            java.lang.String unused = r0.photoUri = r10
            boolean unused = r0.hasPhoto = r5
        L_0x00b8:
            r11.hashCode()
            java.lang.String r10 = "mobile"
            java.lang.String r12 = "data4"
            java.lang.String r13 = "home"
            java.lang.String r14 = "work"
            java.lang.String r15 = "data6"
            java.lang.String r7 = "data5"
            java.lang.String r2 = "data2"
            java.lang.String r6 = "data3"
            java.lang.String r4 = ""
            java.lang.String r16 = "other"
            java.lang.String r3 = "data1"
            int r17 = r11.hashCode()
            switch(r17) {
                case -1569536764: goto L_0x0135;
                case -1328682538: goto L_0x012a;
                case -1079224304: goto L_0x011f;
                case -1079210633: goto L_0x0114;
                case -601229436: goto L_0x0109;
                case 456415478: goto L_0x00fe;
                case 684173810: goto L_0x00f3;
                case 689862072: goto L_0x00e8;
                case 950831081: goto L_0x00db;
                default: goto L_0x00d8;
            }
        L_0x00d8:
            r5 = -1
            goto L_0x013f
        L_0x00db:
            java.lang.String r5 = "vnd.android.cursor.item/im"
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x00e4
            goto L_0x00d8
        L_0x00e4:
            r5 = 8
            goto L_0x013f
        L_0x00e8:
            java.lang.String r5 = "vnd.android.cursor.item/organization"
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x00f1
            goto L_0x00d8
        L_0x00f1:
            r5 = 7
            goto L_0x013f
        L_0x00f3:
            java.lang.String r5 = "vnd.android.cursor.item/phone_v2"
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x00fc
            goto L_0x00d8
        L_0x00fc:
            r5 = 6
            goto L_0x013f
        L_0x00fe:
            java.lang.String r5 = "vnd.android.cursor.item/website"
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x0107
            goto L_0x00d8
        L_0x0107:
            r5 = 5
            goto L_0x013f
        L_0x0109:
            java.lang.String r5 = "vnd.android.cursor.item/postal-address_v2"
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x0112
            goto L_0x00d8
        L_0x0112:
            r5 = 4
            goto L_0x013f
        L_0x0114:
            java.lang.String r5 = "vnd.android.cursor.item/note"
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x011d
            goto L_0x00d8
        L_0x011d:
            r5 = 3
            goto L_0x013f
        L_0x011f:
            java.lang.String r5 = "vnd.android.cursor.item/name"
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x0128
            goto L_0x00d8
        L_0x0128:
            r5 = 2
            goto L_0x013f
        L_0x012a:
            java.lang.String r5 = "vnd.android.cursor.item/contact_event"
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x0133
            goto L_0x00d8
        L_0x0133:
            r5 = 1
            goto L_0x013f
        L_0x0135:
            java.lang.String r5 = "vnd.android.cursor.item/email_v2"
            boolean r5 = r11.equals(r5)
            if (r5 != 0) goto L_0x013e
            goto L_0x00d8
        L_0x013e:
            r5 = 0
        L_0x013f:
            switch(r5) {
                case 0: goto L_0x0367;
                case 1: goto L_0x02b4;
                case 2: goto L_0x025f;
                case 3: goto L_0x0252;
                case 4: goto L_0x0244;
                case 5: goto L_0x01f4;
                case 6: goto L_0x01c1;
                case 7: goto L_0x019f;
                case 8: goto L_0x0149;
                default: goto L_0x0142;
            }
        L_0x0142:
            r5 = 1
            r7 = 0
        L_0x0144:
            r11 = 2
            r12 = 3
        L_0x0146:
            r15 = 4
            goto L_0x03c2
        L_0x0149:
            int r2 = r1.getColumnIndex(r3)
            java.lang.String r2 = r1.getString(r2)
            int r3 = r1.getColumnIndex(r7)
            int r3 = r1.getInt(r3)
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 != 0) goto L_0x0142
            switch(r3) {
                case -1: goto L_0x0180;
                case 0: goto L_0x017d;
                case 1: goto L_0x017a;
                case 2: goto L_0x0177;
                case 3: goto L_0x0174;
                case 4: goto L_0x0171;
                case 5: goto L_0x016e;
                case 6: goto L_0x016b;
                case 7: goto L_0x0168;
                case 8: goto L_0x0165;
                default: goto L_0x0162;
            }
        L_0x0162:
            r4 = r16
            goto L_0x0192
        L_0x0165:
            java.lang.String r4 = "NetMeeting"
            goto L_0x0192
        L_0x0168:
            java.lang.String r4 = "Jabber"
            goto L_0x0192
        L_0x016b:
            java.lang.String r4 = "ICQ"
            goto L_0x0192
        L_0x016e:
            java.lang.String r4 = "Google Talk"
            goto L_0x0192
        L_0x0171:
            java.lang.String r4 = "QQ"
            goto L_0x0192
        L_0x0174:
            java.lang.String r4 = "Skype"
            goto L_0x0192
        L_0x0177:
            java.lang.String r4 = "Yahoo"
            goto L_0x0192
        L_0x017a:
            java.lang.String r4 = "MSN"
            goto L_0x0192
        L_0x017d:
            java.lang.String r4 = "AIM"
            goto L_0x0192
        L_0x0180:
            int r3 = r1.getColumnIndex(r15)
            java.lang.String r3 = r1.getString(r3)
            if (r3 == 0) goto L_0x0192
            int r3 = r1.getColumnIndex(r15)
            java.lang.String r4 = r1.getString(r3)
        L_0x0192:
            java.util.List r0 = r0.instantMessengers
            com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Item r3 = new com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Item
            r3.<init>(r4, r2, r9)
            r0.add(r3)
            goto L_0x0142
        L_0x019f:
            int r2 = r1.getColumnIndex(r3)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String unused = r0.company = r2
            int r2 = r1.getColumnIndex(r12)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String unused = r0.jobTitle = r2
            int r2 = r1.getColumnIndex(r7)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String unused = r0.department = r2
            goto L_0x0142
        L_0x01c1:
            int r3 = r1.getColumnIndex(r3)
            java.lang.String r3 = r1.getString(r3)
            int r2 = r1.getColumnIndex(r2)
            int r2 = r1.getInt(r2)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x0142
            r4 = 1
            if (r2 == r4) goto L_0x01e5
            r4 = 2
            if (r2 == r4) goto L_0x01e6
            r4 = 3
            if (r2 == r4) goto L_0x01e3
            r10 = r16
            goto L_0x01e6
        L_0x01e3:
            r10 = r14
            goto L_0x01e6
        L_0x01e5:
            r10 = r13
        L_0x01e6:
            java.util.List r0 = r0.phones
            com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Item r2 = new com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Item
            r2.<init>(r10, r3, r9)
            r0.add(r2)
            goto L_0x0142
        L_0x01f4:
            int r3 = r1.getColumnIndex(r3)
            java.lang.String r3 = r1.getString(r3)
            int r2 = r1.getColumnIndex(r2)
            int r2 = r1.getInt(r2)
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 != 0) goto L_0x0142
            switch(r2) {
                case 0: goto L_0x021e;
                case 1: goto L_0x021b;
                case 2: goto L_0x0218;
                case 3: goto L_0x0215;
                case 4: goto L_0x0236;
                case 5: goto L_0x0213;
                case 6: goto L_0x0210;
                default: goto L_0x020d;
            }
        L_0x020d:
            r13 = r16
            goto L_0x0236
        L_0x0210:
            java.lang.String r13 = "ftp"
            goto L_0x0236
        L_0x0213:
            r13 = r14
            goto L_0x0236
        L_0x0215:
            java.lang.String r13 = "profile"
            goto L_0x0236
        L_0x0218:
            java.lang.String r13 = "blog"
            goto L_0x0236
        L_0x021b:
            java.lang.String r13 = "homepage"
            goto L_0x0236
        L_0x021e:
            int r2 = r1.getColumnIndex(r6)
            java.lang.String r2 = r1.getString(r2)
            if (r2 == 0) goto L_0x0235
            int r2 = r1.getColumnIndex(r6)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String r13 = r2.toLowerCase()
            goto L_0x0236
        L_0x0235:
            r13 = r4
        L_0x0236:
            java.util.List r0 = r0.urls
            com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Item r2 = new com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Item
            r2.<init>(r13, r3, r9)
            r0.add(r2)
            goto L_0x0142
        L_0x0244:
            java.util.List r0 = r0.postalAddresses
            com.rt2zz.reactnativecontacts.ContactsProvider$Contact$PostalAddressItem r2 = new com.rt2zz.reactnativecontacts.ContactsProvider$Contact$PostalAddressItem
            r2.<init>(r1)
            r0.add(r2)
            goto L_0x0142
        L_0x0252:
            int r2 = r1.getColumnIndex(r3)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String unused = r0.note = r2
            goto L_0x0142
        L_0x025f:
            int r2 = r1.getColumnIndex(r2)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String unused = r0.givenName = r2
            int r2 = r1.getColumnIndex(r7)
            java.lang.String r2 = r1.getString(r2)
            if (r2 == 0) goto L_0x0280
            int r2 = r1.getColumnIndex(r7)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String unused = r0.middleName = r2
            goto L_0x0283
        L_0x0280:
            java.lang.String unused = r0.middleName = r4
        L_0x0283:
            int r2 = r1.getColumnIndex(r6)
            java.lang.String r2 = r1.getString(r2)
            if (r2 == 0) goto L_0x0299
            int r2 = r1.getColumnIndex(r6)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String unused = r0.familyName = r2
            goto L_0x029c
        L_0x0299:
            java.lang.String unused = r0.familyName = r4
        L_0x029c:
            int r2 = r1.getColumnIndex(r12)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String unused = r0.prefix = r2
            int r2 = r1.getColumnIndex(r15)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String unused = r0.suffix = r2
            goto L_0x0142
        L_0x02b4:
            int r2 = r1.getColumnIndex(r2)
            int r2 = r1.getInt(r2)
            r5 = 3
            if (r2 != r5) goto L_0x0361
            int r2 = r1.getColumnIndex(r3)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            java.lang.String r3 = "--"
            java.lang.String r2 = r2.replace(r3, r4)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            java.lang.String r3 = "-"
            java.lang.String[] r2 = r2.split(r3)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            int r3 = r2.size()     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            r4 = 31
            r5 = 12
            r6 = 2
            if (r3 != r6) goto L_0x0312
            r3 = 0
            java.lang.Object r6 = r2.get(r3)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            int r3 = java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            r6 = 1
            java.lang.Object r2 = r2.get(r6)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            if (r3 < r6) goto L_0x0310
            if (r3 > r5) goto L_0x0310
            if (r2 < r6) goto L_0x0310
            if (r2 > r4) goto L_0x0310
            com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Birthday r4 = new com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Birthday     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            r4.<init>(r3, r2)     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            com.rt2zz.reactnativecontacts.ContactsProvider.Contact.Birthday unused = r0.birthday = r4     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            goto L_0x0310
        L_0x0309:
            r0 = move-exception
        L_0x030a:
            r7 = 0
            goto L_0x0357
        L_0x030c:
            r0 = move-exception
            goto L_0x030a
        L_0x030e:
            r0 = move-exception
            goto L_0x030a
        L_0x0310:
            r7 = 0
            goto L_0x0354
        L_0x0312:
            int r3 = r2.size()     // Catch:{ NumberFormatException -> 0x030e, ArrayIndexOutOfBoundsException -> 0x030c, NullPointerException -> 0x0309 }
            r6 = 3
            if (r3 != r6) goto L_0x0310
            r7 = 0
            java.lang.Object r3 = r2.get(r7)     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            r6 = 1
            java.lang.Object r9 = r2.get(r6)     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            int r6 = java.lang.Integer.parseInt(r9)     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            r9 = 2
            java.lang.Object r2 = r2.get(r9)     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            if (r3 <= 0) goto L_0x0354
            r9 = 1
            if (r6 < r9) goto L_0x0354
            if (r6 > r5) goto L_0x0354
            if (r2 < r9) goto L_0x0354
            if (r2 > r4) goto L_0x0354
            com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Birthday r4 = new com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Birthday     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            r4.<init>(r3, r6, r2)     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            com.rt2zz.reactnativecontacts.ContactsProvider.Contact.Birthday unused = r0.birthday = r4     // Catch:{ NumberFormatException -> 0x0352, ArrayIndexOutOfBoundsException -> 0x0350, NullPointerException -> 0x034e }
            goto L_0x0354
        L_0x034e:
            r0 = move-exception
            goto L_0x0357
        L_0x0350:
            r0 = move-exception
            goto L_0x0357
        L_0x0352:
            r0 = move-exception
            goto L_0x0357
        L_0x0354:
            r5 = 1
            goto L_0x0144
        L_0x0357:
            java.lang.String r2 = "ContactsProvider"
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r2, r0)
            goto L_0x0354
        L_0x0361:
            r7 = 0
            r12 = r5
            r5 = 1
            r11 = 2
            goto L_0x0146
        L_0x0367:
            r7 = 0
            int r3 = r1.getColumnIndex(r3)
            java.lang.String r3 = r1.getString(r3)
            int r2 = r1.getColumnIndex(r2)
            int r2 = r1.getInt(r2)
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 != 0) goto L_0x0354
            if (r2 == 0) goto L_0x039a
            r5 = 1
            if (r2 == r5) goto L_0x0395
            r11 = 2
            if (r2 == r11) goto L_0x0391
            r12 = 3
            if (r2 == r12) goto L_0x038f
            r15 = 4
            if (r2 == r15) goto L_0x03b6
        L_0x038c:
            r10 = r16
            goto L_0x03b6
        L_0x038f:
            r15 = 4
            goto L_0x038c
        L_0x0391:
            r12 = 3
            r15 = 4
            r10 = r14
            goto L_0x03b6
        L_0x0395:
            r11 = 2
            r12 = 3
            r15 = 4
            r10 = r13
            goto L_0x03b6
        L_0x039a:
            r5 = 1
            r11 = 2
            r12 = 3
            r15 = 4
            int r2 = r1.getColumnIndex(r6)
            java.lang.String r2 = r1.getString(r2)
            if (r2 == 0) goto L_0x03b5
            int r2 = r1.getColumnIndex(r6)
            java.lang.String r2 = r1.getString(r2)
            java.lang.String r10 = r2.toLowerCase()
            goto L_0x03b6
        L_0x03b5:
            r10 = r4
        L_0x03b6:
            java.util.List r0 = r0.emails
            com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Item r2 = new com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Item
            r2.<init>(r10, r3, r9)
            r0.add(r2)
        L_0x03c2:
            r7 = -1
            goto L_0x0009
        L_0x03c5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rt2zz.reactnativecontacts.ContactsProvider.loadContactsFrom(android.database.Cursor):java.util.Map");
    }

    public String getPhotoUriFromContactId(String str) {
        ContentResolver contentResolver2 = this.contentResolver;
        Uri uri = ContactsContract.Data.CONTENT_URI;
        List list = PHOTO_PROJECTION;
        Cursor query = contentResolver2.query(uri, (String[]) list.toArray(new String[list.size()]), "contact_id = ?", new String[]{str}, (String) null);
        if (query != null) {
            try {
                if (cursorMoveToNext(query).booleanValue()) {
                    String string = query.getString(query.getColumnIndex("photo_uri"));
                    if (!TextUtils.isEmpty(string)) {
                        return string;
                    }
                }
            } finally {
                query.close();
            }
        }
        if (query == null) {
            return null;
        }
        query.close();
        return null;
    }

    private static class Contact {
        /* access modifiers changed from: private */
        public Birthday birthday;
        /* access modifiers changed from: private */
        public String company = "";
        private String contactId;
        /* access modifiers changed from: private */
        public String department = "";
        /* access modifiers changed from: private */
        public String displayName;
        /* access modifiers changed from: private */
        public List emails = new ArrayList();
        /* access modifiers changed from: private */
        public String familyName = "";
        /* access modifiers changed from: private */
        public String givenName = "";
        /* access modifiers changed from: private */
        public boolean hasPhoto = false;
        /* access modifiers changed from: private */
        public List instantMessengers = new ArrayList();
        /* access modifiers changed from: private */
        public boolean isStarred = false;
        /* access modifiers changed from: private */
        public String jobTitle = "";
        /* access modifiers changed from: private */
        public String middleName = "";
        /* access modifiers changed from: private */
        public String note = "";
        /* access modifiers changed from: private */
        public List phones = new ArrayList();
        /* access modifiers changed from: private */
        public String photoUri;
        /* access modifiers changed from: private */
        public List postalAddresses = new ArrayList();
        /* access modifiers changed from: private */
        public String prefix = "";
        /* access modifiers changed from: private */
        public String rawContactId;
        /* access modifiers changed from: private */
        public String suffix = "";
        /* access modifiers changed from: private */
        public List urls = new ArrayList();

        public Contact(String str) {
            this.contactId = str;
        }

        public WritableMap toMap() {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("recordID", this.contactId);
            createMap.putString("rawContactId", this.rawContactId);
            createMap.putString("givenName", TextUtils.isEmpty(this.givenName) ? this.displayName : this.givenName);
            createMap.putString("displayName", this.displayName);
            createMap.putString("middleName", this.middleName);
            createMap.putString("familyName", this.familyName);
            createMap.putString("prefix", this.prefix);
            createMap.putString("suffix", this.suffix);
            createMap.putString("company", this.company);
            createMap.putString("jobTitle", this.jobTitle);
            createMap.putString("department", this.department);
            createMap.putString("note", this.note);
            createMap.putBoolean("hasThumbnail", this.hasPhoto);
            String str = this.photoUri;
            if (str == null) {
                str = "";
            }
            createMap.putString("thumbnailPath", str);
            createMap.putBoolean("isStarred", this.isStarred);
            WritableArray createArray = Arguments.createArray();
            for (Item item : this.phones) {
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putString("number", item.value);
                createMap2.putString(Constants.ScionAnalytics.PARAM_LABEL, item.label);
                createMap2.putString("id", item.id);
                createArray.pushMap(createMap2);
            }
            createMap.putArray("phoneNumbers", createArray);
            WritableArray createArray2 = Arguments.createArray();
            for (Item item2 : this.urls) {
                WritableMap createMap3 = Arguments.createMap();
                createMap3.putString("url", item2.value);
                createMap3.putString("id", item2.id);
                createArray2.pushMap(createMap3);
            }
            createMap.putArray("urlAddresses", createArray2);
            WritableArray createArray3 = Arguments.createArray();
            for (Item item3 : this.instantMessengers) {
                WritableMap createMap4 = Arguments.createMap();
                createMap4.putString("username", item3.value);
                createMap4.putString("service", item3.label);
                createArray3.pushMap(createMap4);
            }
            createMap.putArray("imAddresses", createArray3);
            WritableArray createArray4 = Arguments.createArray();
            for (Item item4 : this.emails) {
                WritableMap createMap5 = Arguments.createMap();
                createMap5.putString(Scopes.EMAIL, item4.value);
                createMap5.putString(Constants.ScionAnalytics.PARAM_LABEL, item4.label);
                createMap5.putString("id", item4.id);
                createArray4.pushMap(createMap5);
            }
            createMap.putArray("emailAddresses", createArray4);
            WritableArray createArray5 = Arguments.createArray();
            for (PostalAddressItem postalAddressItem : this.postalAddresses) {
                createArray5.pushMap(postalAddressItem.map);
            }
            createMap.putArray("postalAddresses", createArray5);
            WritableMap createMap6 = Arguments.createMap();
            Birthday birthday2 = this.birthday;
            if (birthday2 != null) {
                int i = birthday2.year;
                if (i > 0) {
                    createMap6.putInt("year", i);
                }
                createMap6.putInt("month", this.birthday.month);
                createMap6.putInt("day", this.birthday.day);
                createMap.putMap("birthday", createMap6);
            }
            return createMap;
        }

        public static class Item {
            public String id;
            public String label;
            public String value;

            public Item(String str, String str2, String str3) {
                this.id = str3;
                this.label = str;
                this.value = str2;
            }
        }

        public static class Birthday {
            public int day;
            public int month;
            public int year;

            public Birthday(int i, int i2, int i3) {
                this.year = i;
                this.month = i2;
                this.day = i3;
            }

            public Birthday(int i, int i2) {
                this.year = 0;
                this.month = i;
                this.day = i2;
            }
        }

        public static class PostalAddressItem {
            public final WritableMap map;

            public PostalAddressItem(Cursor cursor) {
                WritableMap createMap = Arguments.createMap();
                this.map = createMap;
                createMap.putString(Constants.ScionAnalytics.PARAM_LABEL, getLabel(cursor));
                putString(cursor, "formattedAddress", "data1");
                putString(cursor, "street", "data4");
                putString(cursor, "pobox", "data5");
                putString(cursor, "neighborhood", "data6");
                putString(cursor, "city", "data7");
                putString(cursor, "region", "data8");
                putString(cursor, RemoteConfigConstants.ResponseFieldKey.STATE, "data8");
                putString(cursor, "postCode", "data9");
                putString(cursor, "country", "data10");
            }

            private void putString(Cursor cursor, String str, String str2) {
                String string = cursor.getString(cursor.getColumnIndex(str2));
                if (!TextUtils.isEmpty(string)) {
                    this.map.putString(str, string);
                }
            }

            static String getLabel(Cursor cursor) {
                int i = cursor.getInt(cursor.getColumnIndex("data2"));
                if (i == 0) {
                    String string = cursor.getString(cursor.getColumnIndex("data3"));
                    return string != null ? string : "";
                } else if (i == 1) {
                    return "home";
                } else {
                    if (i != 2) {
                        return "other";
                    }
                    return "work";
                }
            }
        }
    }
}
