package com.rt2zz.reactnativecontacts;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.core.app.ActivityCompat;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.common.Scopes;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class ContactsManager extends ReactContextBaseJavaModule implements ActivityEventListener {
    private static final String PERMISSION_AUTHORIZED = "authorized";
    private static final String PERMISSION_DENIED = "denied";
    private static final String PERMISSION_READ_CONTACTS = "android.permission.READ_CONTACTS";
    private static final int PERMISSION_REQUEST_CODE = 888;
    private static final int REQUEST_OPEN_CONTACT_FORM = 52941;
    private static final int REQUEST_OPEN_EXISTING_CONTACT = 52942;
    private static Promise requestPromise;
    private static Promise updateContactPromise;

    @ReactMethod
    public void iosEnableNotesUsage(boolean z) {
    }

    public void onNewIntent(Intent intent) {
    }

    public ContactsManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.addActivityEventListener(this);
    }

    @ReactMethod
    public void getAll(Promise promise) {
        getAllContacts(promise);
    }

    @ReactMethod
    public void getAllWithoutPhotos(Promise promise) {
        getAllContacts(promise);
    }

    private void getAllContacts(final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContacts());
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getCount(final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                try {
                    promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactsCount());
                    return null;
                } catch (Exception e) {
                    promise.reject((Throwable) e);
                    return null;
                }
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getContactsMatchingString(final String str, final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactsMatchingString(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getContactsByPhoneNumber(final String str, final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactsByPhoneNumber(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getContactsByEmailAddress(final String str, final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactsByEmailAddress(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getPhotoForId(final String str, final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getPhotoUriFromContactId(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getContactById(final String str, final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                promise.resolve(new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContactById(str));
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void writePhotoToPath(final String str, final String str2, final Promise promise) {
        new AsyncTask() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                FileOutputStream fileOutputStream;
                InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(ContactsManager.this.getReactApplicationContext().getContentResolver(), ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(str)));
                FileOutputStream fileOutputStream2 = null;
                try {
                    fileOutputStream = new FileOutputStream(str2);
                    try {
                        BitmapFactory.decodeStream(openContactPhotoInputStream).compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        promise.resolve(Boolean.TRUE);
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        try {
                            promise.reject(e.toString());
                            fileOutputStream.close();
                            openContactPhotoInputStream.close();
                            return null;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream2 = fileOutputStream;
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            throw th;
                        }
                    }
                } catch (FileNotFoundException e4) {
                    e = e4;
                    fileOutputStream = null;
                    promise.reject(e.toString());
                    fileOutputStream.close();
                    openContactPhotoInputStream.close();
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream2.close();
                    throw th;
                }
                try {
                    openContactPhotoInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    private Bitmap getThumbnailBitmap(String str) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        if (decodeFile != null) {
            return decodeFile;
        }
        try {
            InputStream open = getReactApplicationContext().getAssets().open(str);
            decodeFile = BitmapFactory.decodeStream(open);
            open.close();
            return decodeFile;
        } catch (IOException e) {
            e.printStackTrace();
            return decodeFile;
        }
    }

    @ReactMethod
    public void openContactForm(ReadableMap readableMap, Promise promise) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String[] strArr;
        Integer[] numArr;
        String[] strArr2;
        int i;
        String[] strArr3;
        String[] strArr4;
        int i2;
        String[] strArr5;
        int i3;
        Integer[] numArr2;
        String[] strArr6;
        String[] strArr7;
        Integer[] numArr3;
        int i4;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        int i5;
        int i6;
        Integer[] numArr4;
        String[] strArr8;
        String[] strArr9;
        String[] strArr10;
        String[] strArr11;
        String[] strArr12;
        String[] strArr13;
        String[] strArr14;
        String[] strArr15;
        int i7;
        Integer[] numArr5;
        String[] strArr16;
        String[] strArr17;
        int i8;
        ReadableMap readableMap2 = readableMap;
        String string = readableMap2.hasKey("givenName") ? readableMap2.getString("givenName") : null;
        String string2 = readableMap2.hasKey("middleName") ? readableMap2.getString("middleName") : null;
        String string3 = readableMap2.hasKey("displayName") ? readableMap2.getString("displayName") : null;
        String string4 = readableMap2.hasKey("familyName") ? readableMap2.getString("familyName") : null;
        String string5 = readableMap2.hasKey("prefix") ? readableMap2.getString("prefix") : null;
        String string6 = readableMap2.hasKey("suffix") ? readableMap2.getString("suffix") : null;
        String string7 = readableMap2.hasKey("company") ? readableMap2.getString("company") : null;
        String string8 = readableMap2.hasKey("jobTitle") ? readableMap2.getString("jobTitle") : null;
        String string9 = readableMap2.hasKey("department") ? readableMap2.getString("department") : null;
        String string10 = readableMap2.hasKey("note") ? readableMap2.getString("note") : null;
        String string11 = readableMap2.hasKey("thumbnailPath") ? readableMap2.getString("thumbnailPath") : null;
        ReadableArray array = readableMap2.hasKey("phoneNumbers") ? readableMap2.getArray("phoneNumbers") : null;
        if (array != null) {
            i = array.size();
            str5 = string3;
            strArr2 = new String[i];
            str4 = string11;
            strArr = new String[i];
            str3 = string10;
            numArr = new Integer[i];
            str2 = string9;
            int i9 = 0;
            while (true) {
                int i10 = i;
                if (i9 >= i) {
                    break;
                }
                strArr2[i9] = array.getMap(i9).getString("number");
                String string12 = array.getMap(i9).getString(Constants.ScionAnalytics.PARAM_LABEL);
                strArr[i9] = string12;
                numArr[i9] = Integer.valueOf(mapStringToPhoneType(string12));
                i9++;
                i = i10;
                string8 = string8;
            }
            str = string8;
        } else {
            str5 = string3;
            str = string8;
            str2 = string9;
            str3 = string10;
            str4 = string11;
            i = 0;
            strArr2 = null;
            numArr = null;
            strArr = null;
        }
        ReadableArray array2 = readableMap2.hasKey("urlAddresses") ? readableMap2.getArray("urlAddresses") : null;
        if (array2 != null) {
            i2 = array2.size();
            strArr4 = new String[i2];
            strArr3 = strArr2;
            int i11 = 0;
            while (true) {
                int i12 = i2;
                if (i11 >= i2) {
                    break;
                }
                strArr4[i11] = array2.getMap(i11).getString("url");
                i11++;
                i2 = i12;
                array2 = array2;
            }
        } else {
            strArr3 = strArr2;
            i2 = 0;
            strArr4 = null;
        }
        ReadableArray array3 = readableMap2.hasKey("emailAddresses") ? readableMap2.getArray("emailAddresses") : null;
        if (array3 != null) {
            i4 = array3.size();
            strArr6 = strArr;
            strArr7 = new String[i4];
            numArr2 = numArr;
            numArr3 = new Integer[i4];
            i3 = i;
            int i13 = 0;
            while (true) {
                int i14 = i4;
                if (i13 >= i4) {
                    break;
                }
                strArr7[i13] = array3.getMap(i13).getString(Scopes.EMAIL);
                numArr3[i13] = Integer.valueOf(mapStringToEmailType(array3.getMap(i13).getString(Constants.ScionAnalytics.PARAM_LABEL)));
                i13++;
                i4 = i14;
                strArr4 = strArr4;
            }
            strArr5 = strArr4;
        } else {
            i3 = i;
            numArr2 = numArr;
            strArr6 = strArr;
            strArr5 = strArr4;
            i4 = 0;
            numArr3 = null;
            strArr7 = null;
        }
        ReadableArray array4 = readableMap2.hasKey("postalAddresses") ? readableMap2.getArray("postalAddresses") : null;
        if (array4 != null) {
            i7 = array4.size();
            strArr9 = new String[i7];
            strArr8 = strArr7;
            strArr10 = new String[i7];
            numArr4 = numArr3;
            String[] strArr18 = new String[i7];
            i6 = i4;
            strArr12 = new String[i7];
            i5 = i2;
            strArr11 = new String[i7];
            str11 = string7;
            strArr13 = new String[i7];
            str10 = string6;
            strArr14 = new String[i7];
            str9 = string5;
            strArr15 = new String[i7];
            str8 = string2;
            numArr5 = new Integer[i7];
            str7 = string4;
            int i15 = 0;
            while (i15 < i7) {
                strArr9[i15] = array4.getMap(i15).getString("street");
                strArr10[i15] = array4.getMap(i15).getString("city");
                strArr18[i15] = array4.getMap(i15).getString(RemoteConfigConstants.ResponseFieldKey.STATE);
                strArr12[i15] = array4.getMap(i15).getString("region");
                strArr11[i15] = array4.getMap(i15).getString("postCode");
                strArr13[i15] = array4.getMap(i15).getString("country");
                strArr14[i15] = array4.getMap(i15).getString("formattedAddress");
                strArr15[i15] = array4.getMap(i15).getString(Constants.ScionAnalytics.PARAM_LABEL);
                numArr5[i15] = Integer.valueOf(mapStringToPostalAddressType(array4.getMap(i15).getString(Constants.ScionAnalytics.PARAM_LABEL)));
                i15++;
                i7 = i7;
                string = string;
            }
            str6 = string;
            int i16 = i7;
        } else {
            str6 = string;
            str8 = string2;
            str7 = string4;
            str9 = string5;
            str10 = string6;
            str11 = string7;
            i6 = i4;
            i5 = i2;
            numArr4 = numArr3;
            strArr8 = strArr7;
            i7 = 0;
            numArr5 = null;
            strArr15 = null;
            strArr14 = null;
            strArr13 = null;
            strArr12 = null;
            strArr11 = null;
            strArr10 = null;
            strArr9 = null;
        }
        ReadableArray array5 = readableMap2.hasKey("imAddresses") ? readableMap2.getArray("imAddresses") : null;
        if (array5 != null) {
            i8 = array5.size();
            strArr17 = new String[i8];
            String[] strArr19 = new String[i8];
            int i17 = 0;
            while (i17 < i8) {
                strArr17[i17] = array5.getMap(i17).getString("username");
                strArr19[i17] = array5.getMap(i17).getString("service");
                i17++;
                i8 = i8;
            }
            int i18 = i8;
            strArr16 = strArr19;
        } else {
            i8 = 0;
            strArr17 = null;
            strArr16 = null;
        }
        ArrayList arrayList = new ArrayList();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mimetype", "vnd.android.cursor.item/identity");
        contentValues.put("data2", str6);
        String[] strArr20 = strArr17;
        contentValues.put("data3", str7);
        String str12 = "data5";
        int i19 = i8;
        contentValues.put(str12, str8);
        String str13 = "data4";
        Integer[] numArr6 = numArr5;
        contentValues.put(str13, str9);
        String[] strArr21 = strArr15;
        contentValues.put("data6", str10);
        arrayList.add(contentValues);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("mimetype", "vnd.android.cursor.item/organization");
        contentValues2.put("data1", str11);
        contentValues2.put(str13, str);
        contentValues2.put(str12, str2);
        arrayList.add(contentValues2);
        int i20 = 0;
        while (true) {
            int i21 = i5;
            if (i20 >= i21) {
                break;
            }
            i5 = i21;
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("mimetype", "vnd.android.cursor.item/website");
            contentValues3.put("data1", strArr5[i20]);
            arrayList.add(contentValues3);
            i20++;
            str12 = str12;
        }
        String str14 = str12;
        int i22 = 0;
        while (true) {
            int i23 = i6;
            if (i22 >= i23) {
                break;
            }
            ContentValues contentValues4 = new ContentValues();
            i6 = i23;
            contentValues4.put("mimetype", "vnd.android.cursor.item/email_v2");
            contentValues4.put("data2", numArr4[i22]);
            contentValues4.put("data1", strArr8[i22]);
            arrayList.add(contentValues4);
            i22++;
        }
        int i24 = 0;
        while (true) {
            int i25 = i3;
            if (i24 >= i25) {
                break;
            }
            ContentValues contentValues5 = new ContentValues();
            i3 = i25;
            contentValues5.put("mimetype", "vnd.android.cursor.item/phone_v2");
            contentValues5.put("data2", numArr2[i24]);
            contentValues5.put("data3", strArr6[i24]);
            contentValues5.put("data1", strArr3[i24]);
            arrayList.add(contentValues5);
            i24++;
        }
        int i26 = 0;
        while (i26 < i7) {
            ContentValues contentValues6 = new ContentValues();
            contentValues6.put("mimetype", "vnd.android.cursor.item/postal-address_v2");
            contentValues6.put(str13, strArr9[i26]);
            contentValues6.put("data7", strArr10[i26]);
            contentValues6.put("data8", strArr12[i26]);
            contentValues6.put("data10", strArr13[i26]);
            contentValues6.put("data9", strArr11[i26]);
            contentValues6.put("data1", strArr14[i26]);
            contentValues6.put("data3", strArr21[i26]);
            contentValues6.put("data2", numArr6[i26]);
            arrayList.add(contentValues6);
            i26++;
            str13 = str13;
        }
        int i27 = 0;
        while (true) {
            int i28 = i19;
            if (i27 >= i28) {
                break;
            }
            ContentValues contentValues7 = new ContentValues();
            contentValues7.put("mimetype", "vnd.android.cursor.item/im");
            contentValues7.put("data1", strArr20[i27]);
            contentValues7.put("data2", 1);
            contentValues7.put(str14, -1);
            contentValues7.put("data6", strArr16[i27]);
            arrayList.add(contentValues7);
            i27++;
            i19 = i28;
        }
        if (str3 != null) {
            ContentValues contentValues8 = new ContentValues();
            contentValues8.put("mimetype", "vnd.android.cursor.item/note");
            contentValues8.put("data1", str3);
            arrayList.add(contentValues8);
        }
        if (str4 != null && !str4.isEmpty()) {
            Bitmap thumbnailBitmap = getThumbnailBitmap(str4);
            if (thumbnailBitmap != null) {
                ContentValues contentValues9 = new ContentValues();
                contentValues9.put("raw_contact_id", 0);
                contentValues9.put("is_super_primary", 1);
                contentValues9.put("data15", toByteArray(thumbnailBitmap));
                contentValues9.put("mimetype", "vnd.android.cursor.item/photo");
                arrayList.add(contentValues9);
            }
        }
        Intent intent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra("name", str5);
        intent.putExtra("finishActivityOnSaveCompleted", true);
        intent.putParcelableArrayListExtra(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, arrayList);
        updateContactPromise = promise;
        getReactApplicationContext().startActivityForResult(intent, REQUEST_OPEN_CONTACT_FORM, Bundle.EMPTY);
    }

    @ReactMethod
    public void openExistingContact(ReadableMap readableMap, Promise promise) {
        try {
            Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null);
            Intent intent = new Intent("android.intent.action.EDIT");
            intent.setDataAndType(withAppendedPath, "vnd.android.cursor.item/contact");
            intent.putExtra("finishActivityOnSaveCompleted", true);
            updateContactPromise = promise;
            getReactApplicationContext().startActivityForResult(intent, REQUEST_OPEN_EXISTING_CONTACT, Bundle.EMPTY);
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void viewExistingContact(ReadableMap readableMap, Promise promise) {
        try {
            Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(withAppendedPath, "vnd.android.cursor.item/contact");
            intent.putExtra("finishActivityOnSaveCompleted", true);
            updateContactPromise = promise;
            getReactApplicationContext().startActivityForResult(intent, REQUEST_OPEN_EXISTING_CONTACT, Bundle.EMPTY);
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void editExistingContact(ReadableMap readableMap, Promise promise) {
        Integer[] numArr;
        int i;
        String[] strArr = null;
        try {
            Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null);
            ReadableArray array = readableMap.hasKey("phoneNumbers") ? readableMap.getArray("phoneNumbers") : null;
            if (array != null) {
                i = array.size();
                strArr = new String[i];
                numArr = new Integer[i];
                for (int i2 = 0; i2 < i; i2++) {
                    strArr[i2] = array.getMap(i2).getString("number");
                    numArr[i2] = Integer.valueOf(mapStringToPhoneType(array.getMap(i2).getString(Constants.ScionAnalytics.PARAM_LABEL)));
                }
            } else {
                i = 0;
                numArr = null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < i; i3++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
                contentValues.put("data2", numArr[i3]);
                contentValues.put("data1", strArr[i3]);
                arrayList.add(contentValues);
            }
            Intent intent = new Intent("android.intent.action.EDIT");
            intent.setDataAndType(withAppendedPath, "vnd.android.cursor.item/contact");
            intent.putExtra("finishActivityOnSaveCompleted", true);
            intent.putParcelableArrayListExtra(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, arrayList);
            updateContactPromise = promise;
            getReactApplicationContext().startActivityForResult(intent, REQUEST_OPEN_EXISTING_CONTACT, Bundle.EMPTY);
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void addContact(ReadableMap readableMap, Promise promise) {
        String str;
        String str2;
        String str3;
        String str4;
        Integer[] numArr;
        String[] strArr;
        int i;
        String[] strArr2;
        String[] strArr3;
        String[] strArr4;
        int i2;
        int i3;
        String[] strArr5;
        Integer[] numArr2;
        int i4;
        String[] strArr6;
        String[] strArr7;
        String[] strArr8;
        Integer[] numArr3;
        int i5;
        String str5;
        String[] strArr9;
        String[] strArr10;
        int i6;
        ContactsManager contactsManager;
        Promise promise2;
        int i7;
        ReadableMap readableMap2 = readableMap;
        Promise promise3 = promise;
        if (readableMap2 == null) {
            promise3.reject("New contact cannot be null.");
            return;
        }
        String string = readableMap2.hasKey("givenName") ? readableMap2.getString("givenName") : null;
        String string2 = readableMap2.hasKey("middleName") ? readableMap2.getString("middleName") : null;
        String string3 = readableMap2.hasKey("familyName") ? readableMap2.getString("familyName") : null;
        String string4 = readableMap2.hasKey("prefix") ? readableMap2.getString("prefix") : null;
        String string5 = readableMap2.hasKey("suffix") ? readableMap2.getString("suffix") : null;
        String string6 = readableMap2.hasKey("company") ? readableMap2.getString("company") : null;
        String string7 = readableMap2.hasKey("jobTitle") ? readableMap2.getString("jobTitle") : null;
        String string8 = readableMap2.hasKey("department") ? readableMap2.getString("department") : null;
        String string9 = readableMap2.hasKey("note") ? readableMap2.getString("note") : null;
        String string10 = readableMap2.hasKey("thumbnailPath") ? readableMap2.getString("thumbnailPath") : null;
        ReadableArray array = readableMap2.hasKey("phoneNumbers") ? readableMap2.getArray("phoneNumbers") : null;
        if (array != null) {
            i = array.size();
            strArr2 = new String[i];
            str4 = string10;
            numArr = new Integer[i];
            str3 = string8;
            strArr = new String[i];
            str2 = string7;
            int i8 = 0;
            while (true) {
                int i9 = i;
                if (i8 >= i) {
                    break;
                }
                strArr2[i8] = array.getMap(i8).getString("number");
                String string11 = array.getMap(i8).getString(Constants.ScionAnalytics.PARAM_LABEL);
                numArr[i8] = Integer.valueOf(mapStringToPhoneType(string11));
                strArr[i8] = string11;
                i8++;
                i = i9;
                string6 = string6;
            }
            str = string6;
        } else {
            str = string6;
            str2 = string7;
            str3 = string8;
            str4 = string10;
            strArr2 = null;
            i = 0;
            strArr = null;
            numArr = null;
        }
        ReadableArray array2 = readableMap2.hasKey("urlAddresses") ? readableMap2.getArray("urlAddresses") : null;
        if (array2 != null) {
            int size = array2.size();
            strArr4 = new String[size];
            strArr3 = strArr;
            int i10 = 0;
            while (true) {
                i7 = size;
                if (i10 >= size) {
                    break;
                }
                strArr4[i10] = array2.getMap(i10).getString("url");
                i10++;
                size = i7;
                array2 = array2;
            }
            i2 = i7;
        } else {
            strArr3 = strArr;
            i2 = 0;
            strArr4 = null;
        }
        ReadableArray array3 = readableMap2.hasKey("emailAddresses") ? readableMap2.getArray("emailAddresses") : null;
        if (array3 != null) {
            int size2 = array3.size();
            strArr6 = strArr4;
            strArr7 = new String[size2];
            i4 = i2;
            numArr3 = new Integer[size2];
            numArr2 = numArr;
            strArr8 = new String[size2];
            strArr5 = strArr2;
            int i11 = 0;
            while (i11 < size2) {
                int i12 = i;
                strArr7[i11] = array3.getMap(i11).getString(Scopes.EMAIL);
                String string12 = array3.getMap(i11).getString(Constants.ScionAnalytics.PARAM_LABEL);
                numArr3[i11] = Integer.valueOf(mapStringToEmailType(string12));
                strArr8[i11] = string12;
                i11++;
                size2 = size2;
                i = i12;
            }
            i3 = i;
            i5 = size2;
        } else {
            strArr5 = strArr2;
            i3 = i;
            i4 = i2;
            numArr2 = numArr;
            strArr6 = strArr4;
            i5 = 0;
            numArr3 = null;
            strArr8 = null;
            strArr7 = null;
        }
        ReadableArray array4 = readableMap2.hasKey("imAddresses") ? readableMap2.getArray("imAddresses") : null;
        if (array4 != null) {
            i6 = array4.size();
            strArr10 = new String[i6];
            str5 = Constants.ScionAnalytics.PARAM_LABEL;
            strArr9 = new String[i6];
            int i13 = 0;
            while (true) {
                int i14 = i6;
                if (i13 >= i6) {
                    break;
                }
                strArr10[i13] = array4.getMap(i13).getString("username");
                strArr9[i13] = array4.getMap(i13).getString("service");
                i13++;
                i6 = i14;
            }
        } else {
            str5 = Constants.ScionAnalytics.PARAM_LABEL;
            i6 = 0;
            strArr10 = null;
            strArr9 = null;
        }
        ArrayList arrayList = new ArrayList();
        String[] strArr11 = strArr9;
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", (Object) null).withValue("account_name", (Object) null).build());
        Uri uri = ContactsContract.Data.CONTENT_URI;
        String[] strArr12 = strArr10;
        int i15 = i6;
        arrayList.add(ContentProviderOperation.newInsert(uri).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", string).withValue("data5", string2).withValue("data3", string3).withValue("data4", string4).withValue("data6", string5).build());
        arrayList.add(ContentProviderOperation.newInsert(uri).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/note").withValue("data1", string9).build());
        ContentProviderOperation.Builder withValue = ContentProviderOperation.newInsert(uri).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/organization").withValue("data1", str).withValue("data4", str2).withValue("data5", str3);
        arrayList.add(withValue.build());
        withValue.withYieldAllowed(true);
        int i16 = i3;
        for (int i17 = 0; i17 < i16; i17++) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", strArr5[i17]).withValue("data2", numArr2[i17]).withValue("data3", strArr3[i17]).build());
        }
        int i18 = i4;
        for (int i19 = 0; i19 < i18; i19++) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", strArr6[i19]).build());
        }
        for (int i20 = 0; i20 < i5; i20++) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", strArr7[i20]).withValue("data2", numArr3[i20]).withValue("data3", strArr8[i20]).build());
        }
        if (str4 == null || str4.isEmpty()) {
            contactsManager = this;
        } else {
            contactsManager = this;
            Bitmap thumbnailBitmap = contactsManager.getThumbnailBitmap(str4);
            if (thumbnailBitmap != null) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/photo").withValue("data15", contactsManager.toByteArray(thumbnailBitmap)).build());
            }
        }
        ReadableMap readableMap3 = readableMap;
        ReadableArray array5 = readableMap3.hasKey("postalAddresses") ? readableMap3.getArray("postalAddresses") : null;
        if (array5 != null) {
            for (int i21 = 0; i21 < array5.size(); i21++) {
                ReadableMap map = array5.getMap(i21);
                String str6 = str5;
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/postal-address_v2").withValue("data2", Integer.valueOf(contactsManager.mapStringToPostalAddressType(map.getString(str6)))).withValue("data3", map.getString(str6)).withValue("data4", map.getString("street")).withValue("data7", map.getString("city")).withValue("data8", map.getString(RemoteConfigConstants.ResponseFieldKey.STATE)).withValue("data9", map.getString("postCode")).withValue("data10", map.getString("country")).build());
            }
        }
        int i22 = i15;
        for (int i23 = 0; i23 < i22; i23++) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/im").withValue("data1", strArr12[i23]).withValue("data2", 1).withValue("data5", -1).withValue("data6", strArr11[i23]).build());
        }
        try {
            ContentResolver contentResolver = getReactApplicationContext().getContentResolver();
            ContentProviderResult[] applyBatch = contentResolver.applyBatch("com.android.contacts", arrayList);
            if (applyBatch != null && applyBatch.length > 0) {
                WritableMap contactByRawId = new ContactsProvider(contentResolver).getContactByRawId(String.valueOf(ContentUris.parseId(applyBatch[0].uri)));
                promise2 = promise;
                try {
                    promise2.resolve(contactByRawId);
                } catch (Exception e) {
                    e = e;
                }
            }
        } catch (Exception e2) {
            e = e2;
            promise2 = promise;
            promise2.reject(e.toString());
        }
    }

    public byte[] toByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @ReactMethod
    public void updateContact(ReadableMap readableMap, Promise promise) {
        String str;
        ReadableArray readableArray;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        int i;
        Integer[] numArr;
        String[] strArr;
        String[] strArr2;
        String[] strArr3;
        String[] strArr4;
        String[] strArr5;
        int i2;
        ReadableArray readableArray2;
        String[] strArr6;
        Integer[] numArr2;
        int i3;
        String[] strArr7;
        String[] strArr8;
        String[] strArr9;
        int i4;
        String[] strArr10;
        Integer[] numArr3;
        String str7;
        String str8;
        String str9;
        String str10;
        int i5;
        int i6;
        String[] strArr11;
        Integer[] numArr4;
        String[] strArr12;
        String[] strArr13;
        String[] strArr14;
        String[] strArr15;
        String[] strArr16;
        String[] strArr17;
        Integer[] numArr5;
        String[] strArr18;
        int i7;
        ReadableArray readableArray3;
        String[] strArr19;
        String[] strArr20;
        String[] strArr21;
        int i8;
        String str11;
        String str12;
        String str13;
        Promise promise2;
        Bitmap thumbnailBitmap;
        int i9;
        String[] strArr22;
        ContentProviderOperation.Builder builder;
        ReadableMap readableMap2 = readableMap;
        Promise promise3 = promise;
        String string = readableMap2.hasKey("recordID") ? readableMap2.getString("recordID") : null;
        String string2 = readableMap2.hasKey("rawContactId") ? readableMap2.getString("rawContactId") : null;
        if (string2 == null || string == null) {
            promise3.reject("Invalid recordId or rawContactId");
            return;
        }
        String string3 = readableMap2.hasKey("givenName") ? readableMap2.getString("givenName") : null;
        String string4 = readableMap2.hasKey("middleName") ? readableMap2.getString("middleName") : null;
        String string5 = readableMap2.hasKey("familyName") ? readableMap2.getString("familyName") : null;
        String string6 = readableMap2.hasKey("prefix") ? readableMap2.getString("prefix") : null;
        String string7 = readableMap2.hasKey("suffix") ? readableMap2.getString("suffix") : null;
        String string8 = readableMap2.hasKey("company") ? readableMap2.getString("company") : null;
        String string9 = readableMap2.hasKey("jobTitle") ? readableMap2.getString("jobTitle") : null;
        String string10 = readableMap2.hasKey("department") ? readableMap2.getString("department") : null;
        String string11 = readableMap2.hasKey("note") ? readableMap2.getString("note") : null;
        String string12 = readableMap2.hasKey("thumbnailPath") ? readableMap2.getString("thumbnailPath") : null;
        ReadableArray array = readableMap2.hasKey("phoneNumbers") ? readableMap2.getArray("phoneNumbers") : null;
        String str14 = Constants.ScionAnalytics.PARAM_LABEL;
        String str15 = string12;
        String str16 = string11;
        if (array != null) {
            i = array.size();
            str6 = string2;
            strArr2 = new String[i];
            str5 = string10;
            numArr = new Integer[i];
            str4 = string9;
            strArr = new String[i];
            str3 = string8;
            String[] strArr23 = new String[i];
            str2 = string7;
            int i10 = 0;
            while (i10 < i) {
                int i11 = i;
                ReadableMap map = array.getMap(i10);
                ReadableArray readableArray4 = array;
                String string13 = map.getString("number");
                String str17 = string6;
                String string14 = map.getString(str14);
                String string15 = map.hasKey("id") ? map.getString("id") : null;
                strArr2[i10] = string13;
                numArr[i10] = Integer.valueOf(mapStringToPhoneType(string14));
                strArr[i10] = string14;
                strArr23[i10] = string15;
                i10++;
                i = i11;
                array = readableArray4;
                string6 = str17;
            }
            readableArray = array;
            str = string6;
            int i12 = i;
        } else {
            str6 = string2;
            readableArray = array;
            str = string6;
            str2 = string7;
            str3 = string8;
            str4 = string9;
            str5 = string10;
            strArr2 = null;
            strArr = null;
            numArr = null;
            i = 0;
        }
        ReadableArray array2 = readableMap2.hasKey("urlAddresses") ? readableMap2.getArray("urlAddresses") : null;
        if (array2 != null) {
            i2 = array2.size();
            strArr5 = new String[i2];
            strArr4 = new String[i2];
            strArr3 = strArr;
            int i13 = 0;
            while (true) {
                int i14 = i2;
                if (i13 >= i2) {
                    break;
                }
                ReadableMap map2 = array2.getMap(i13);
                ReadableArray readableArray5 = array2;
                strArr5[i13] = map2.getString("url");
                strArr4[i13] = map2.hasKey("id") ? map2.getString("id") : null;
                i13++;
                i2 = i14;
                array2 = readableArray5;
            }
        } else {
            strArr3 = strArr;
            i2 = 0;
            strArr5 = null;
            strArr4 = null;
        }
        ReadableArray array3 = readableMap2.hasKey("emailAddresses") ? readableMap2.getArray("emailAddresses") : null;
        if (array3 != null) {
            i4 = array3.size();
            strArr8 = strArr5;
            strArr10 = new String[i4];
            strArr7 = strArr4;
            String[] strArr24 = new String[i4];
            i3 = i2;
            numArr3 = new Integer[i4];
            numArr2 = numArr;
            strArr9 = new String[i4];
            strArr6 = strArr2;
            int i15 = 0;
            while (i15 < i4) {
                int i16 = i4;
                ReadableMap map3 = array3.getMap(i15);
                ReadableArray readableArray6 = array3;
                strArr10[i15] = map3.getString(Scopes.EMAIL);
                String string16 = map3.getString(str14);
                numArr3[i15] = Integer.valueOf(mapStringToEmailType(string16));
                strArr9[i15] = string16;
                strArr24[i15] = map3.hasKey("id") ? map3.getString("id") : null;
                i15++;
                i4 = i16;
                array3 = readableArray6;
            }
            readableArray2 = array3;
            int i17 = i4;
        } else {
            strArr6 = strArr2;
            readableArray2 = array3;
            i3 = i2;
            strArr8 = strArr5;
            strArr7 = strArr4;
            numArr2 = numArr;
            numArr3 = null;
            strArr10 = null;
            i4 = 0;
            strArr9 = null;
        }
        ReadableArray array4 = readableMap2.hasKey("postalAddresses") ? readableMap2.getArray("postalAddresses") : null;
        if (array4 != null) {
            i7 = array4.size();
            strArr16 = new String[i7];
            strArr12 = strArr9;
            strArr14 = new String[i7];
            numArr4 = numArr3;
            strArr17 = new String[i7];
            strArr11 = strArr10;
            String[] strArr25 = new String[i7];
            i6 = i4;
            strArr15 = new String[i7];
            i5 = i;
            strArr13 = new String[i7];
            str10 = string5;
            numArr5 = new Integer[i7];
            str9 = string4;
            strArr18 = new String[i7];
            str8 = string3;
            int i18 = 0;
            while (i18 < i7) {
                int i19 = i7;
                String valueFromKey = getValueFromKey(array4.getMap(i18), str14);
                strArr16[i18] = getValueFromKey(array4.getMap(i18), "street");
                strArr14[i18] = getValueFromKey(array4.getMap(i18), "city");
                strArr17[i18] = getValueFromKey(array4.getMap(i18), RemoteConfigConstants.ResponseFieldKey.STATE);
                strArr25[i18] = getValueFromKey(array4.getMap(i18), "region");
                strArr15[i18] = getValueFromKey(array4.getMap(i18), "postCode");
                strArr13[i18] = getValueFromKey(array4.getMap(i18), "country");
                numArr5[i18] = Integer.valueOf(mapStringToPostalAddressType(valueFromKey));
                strArr18[i18] = valueFromKey;
                i18++;
                i7 = i19;
                str14 = str14;
                string = string;
            }
            str7 = string;
            int i20 = i7;
        } else {
            str7 = string;
            str8 = string3;
            str9 = string4;
            str10 = string5;
            numArr4 = numArr3;
            strArr11 = strArr10;
            i6 = i4;
            strArr12 = strArr9;
            i5 = i;
            i7 = 0;
            strArr18 = null;
            numArr5 = null;
            strArr17 = null;
            strArr16 = null;
            strArr15 = null;
            strArr14 = null;
            strArr13 = null;
        }
        ReadableArray array5 = readableMap2.hasKey("imAddresses") ? readableMap2.getArray("imAddresses") : null;
        if (array5 != null) {
            i8 = array5.size();
            String[] strArr26 = new String[i8];
            strArr21 = new String[i8];
            String[] strArr27 = new String[i8];
            strArr19 = strArr13;
            int i21 = 0;
            while (i21 < i8) {
                int i22 = i8;
                ReadableMap map4 = array5.getMap(i21);
                ReadableArray readableArray7 = array5;
                strArr26[i21] = map4.getString("username");
                strArr21[i21] = map4.getString("service");
                strArr27[i21] = map4.hasKey("id") ? map4.getString("id") : null;
                i21++;
                i8 = i22;
                array5 = readableArray7;
            }
            readableArray3 = array5;
            int i23 = i8;
            strArr20 = strArr26;
        } else {
            readableArray3 = array5;
            strArr19 = strArr13;
            i8 = 0;
            strArr21 = null;
            strArr20 = null;
        }
        ArrayList arrayList = new ArrayList();
        Uri uri = ContactsContract.Data.CONTENT_URI;
        String[] strArr28 = strArr21;
        int i24 = i8;
        String[] strArr29 = strArr15;
        String[] strArr30 = strArr17;
        String[] strArr31 = strArr14;
        arrayList.add(ContentProviderOperation.newUpdate(uri).withSelection("contact_id=?", new String[]{str7}).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", str8).withValue("data5", str9).withValue("data3", str10).withValue("data4", str).withValue("data6", str2).build());
        ContentProviderOperation.Builder withValue = ContentProviderOperation.newUpdate(uri).withSelection("contact_id=? AND mimetype = ?", new String[]{str7, "vnd.android.cursor.item/organization"}).withValue("data1", str3).withValue("data4", str4).withValue("data5", str5);
        arrayList.add(withValue.build());
        withValue.withYieldAllowed(true);
        if (readableArray != null) {
            str11 = "data5";
            str12 = "data4";
            str13 = str6;
            arrayList.add(ContentProviderOperation.newDelete(uri).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/phone_v2", str13}).build());
            int i25 = 0;
            for (int i26 = i5; i25 < i26; i26 = i26) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", str13).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", strArr6[i25]).withValue("data2", numArr2[i25]).withValue("data3", strArr3[i25]).build());
                i25++;
                strArr16 = strArr16;
            }
        } else {
            str11 = "data5";
            str12 = "data4";
            str13 = str6;
        }
        String[] strArr32 = strArr16;
        int i27 = i3;
        int i28 = 0;
        while (i28 < i27) {
            if (strArr7[i28] == null) {
                i9 = i27;
                builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", str13).withValue("mimetype", "vnd.android.cursor.item/website").withValue("data1", strArr8[i28]);
                strArr22 = strArr18;
            } else {
                i9 = i27;
                strArr22 = strArr18;
                builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection("_id=?", new String[]{String.valueOf(strArr7[i28])}).withValue("data1", strArr8[i28]);
            }
            arrayList.add(builder.build());
            i28++;
            strArr18 = strArr22;
            i27 = i9;
        }
        String[] strArr33 = strArr18;
        if (readableArray2 != null) {
            arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/email_v2", str13}).build());
            int i29 = i6;
            for (int i30 = 0; i30 < i29; i30++) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", str13).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", strArr11[i30]).withValue("data2", numArr4[i30]).withValue("data3", strArr12[i30]).build());
            }
        }
        Uri uri2 = ContactsContract.Data.CONTENT_URI;
        arrayList.add(ContentProviderOperation.newDelete(uri2).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/note", str13}).build());
        if (str16 != null) {
            arrayList.add(ContentProviderOperation.newInsert(uri2).withValue("raw_contact_id", str13).withValue("mimetype", "vnd.android.cursor.item/note").withValue("data1", str16).build());
        }
        if (!(str15 == null || str15.isEmpty() || (thumbnailBitmap = getThumbnailBitmap(str15)) == null)) {
            arrayList.add(ContentProviderOperation.newInsert(uri2).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/photo").withValue("data15", toByteArray(thumbnailBitmap)).build());
        }
        if (array4 != null) {
            arrayList.add(ContentProviderOperation.newDelete(uri2).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/postal-address_v2", str13}).build());
            for (int i31 = 0; i31 < i7; i31++) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", str13).withValue("mimetype", "vnd.android.cursor.item/postal-address_v2").withValue("data2", numArr5[i31]).withValue("data3", strArr33[i31]).withValue(str12, strArr32[i31]).withValue("data7", strArr31[i31]).withValue("data8", strArr30[i31]).withValue("data9", strArr29[i31]).withValue("data10", strArr19[i31]).build());
            }
        }
        if (readableArray3 != null) {
            arrayList.add(ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI).withSelection("mimetype=? AND raw_contact_id = ?", new String[]{"vnd.android.cursor.item/im", str13}).build());
            int i32 = i24;
            for (int i33 = 0; i33 < i32; i33++) {
                arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValue("raw_contact_id", str13).withValue("mimetype", "vnd.android.cursor.item/im").withValue("data1", strArr20[i33]).withValue("data2", 1).withValue(str11, -1).withValue("data6", strArr28[i33]).build());
            }
        }
        try {
            ContentResolver contentResolver = getReactApplicationContext().getContentResolver();
            ContentProviderResult[] applyBatch = contentResolver.applyBatch("com.android.contacts", arrayList);
            if (applyBatch != null && applyBatch.length > 0) {
                WritableMap contactById = new ContactsProvider(contentResolver).getContactById(str7);
                promise2 = promise;
                try {
                    promise2.resolve(contactById);
                } catch (Exception e) {
                    e = e;
                }
            }
        } catch (Exception e2) {
            e = e2;
            promise2 = promise;
            promise2.reject(e.toString());
        }
    }

    @ReactMethod
    public void deleteContact(ReadableMap readableMap, Promise promise) {
        String string = readableMap.hasKey("recordID") ? readableMap.getString("recordID") : null;
        try {
            if (getReactApplicationContext().getContentResolver().delete(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, string), (String) null, (String[]) null) > 0) {
                promise.resolve(string);
            } else {
                promise.resolve((Object) null);
            }
        } catch (Exception e) {
            promise.reject(e.toString());
        }
    }

    @ReactMethod
    public void checkPermission(Promise promise) {
        promise.resolve(isPermissionGranted());
    }

    @ReactMethod
    public void requestPermission(Promise promise) {
        requestReadContactsPermission(promise);
    }

    private void requestReadContactsPermission(Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.reject(PERMISSION_DENIED);
        } else if (isPermissionGranted().equals(PERMISSION_AUTHORIZED)) {
            promise.resolve(PERMISSION_AUTHORIZED);
        } else {
            requestPromise = promise;
            ActivityCompat.requestPermissions(currentActivity, new String[]{PERMISSION_READ_CONTACTS}, 888);
        }
    }

    protected static void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Promise promise = requestPromise;
        if (promise != null) {
            if (i != 888) {
                promise.resolve(PERMISSION_DENIED);
                return;
            }
            Hashtable hashtable = new Hashtable();
            for (int i2 = 0; i2 < strArr.length; i2++) {
                hashtable.put(strArr[i2], Boolean.valueOf(iArr[i2] == 0));
            }
            if (!hashtable.containsKey(PERMISSION_READ_CONTACTS) || !((Boolean) hashtable.get(PERMISSION_READ_CONTACTS)).booleanValue()) {
                requestPromise.resolve(PERMISSION_DENIED);
            } else {
                requestPromise.resolve(PERMISSION_AUTHORIZED);
            }
            requestPromise = null;
        }
    }

    private String getValueFromKey(ReadableMap readableMap, String str) {
        return readableMap.hasKey(str) ? readableMap.getString(str) : "";
    }

    private String isPermissionGranted() {
        return getReactApplicationContext().checkSelfPermission(PERMISSION_READ_CONTACTS) == 0 ? PERMISSION_AUTHORIZED : PERMISSION_DENIED;
    }

    private int mapStringToPhoneType(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1068855134:
                if (str.equals("mobile")) {
                    c = 0;
                    break;
                }
                break;
            case -557528811:
                if (str.equals("work_pager")) {
                    c = 1;
                    break;
                }
                break;
            case -487750564:
                if (str.equals("home fax")) {
                    c = 2;
                    break;
                }
                break;
            case -176627312:
                if (str.equals("work_mobile")) {
                    c = 3;
                    break;
                }
                break;
            case 3049826:
                if (str.equals("cell")) {
                    c = 4;
                    break;
                }
                break;
            case 3208415:
                if (str.equals("home")) {
                    c = 5;
                    break;
                }
                break;
            case 3343801:
                if (str.equals("main")) {
                    c = 6;
                    break;
                }
                break;
            case 3655441:
                if (str.equals("work")) {
                    c = 7;
                    break;
                }
                break;
            case 33287566:
                if (str.equals("work fax")) {
                    c = 8;
                    break;
                }
                break;
            case 106069776:
                if (str.equals("other")) {
                    c = 9;
                    break;
                }
                break;
            case 106426307:
                if (str.equals("pager")) {
                    c = 10;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 4:
                return 2;
            case 1:
                return 18;
            case 2:
                return 5;
            case 3:
                return 17;
            case 5:
                return 1;
            case 6:
                return 12;
            case 7:
                return 3;
            case 8:
                return 4;
            case 9:
                return 7;
            case 10:
                return 6;
            default:
                return 0;
        }
    }

    private int mapStringToEmailType(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1068855134:
                if (str.equals("mobile")) {
                    c = 0;
                    break;
                }
                break;
            case 3208415:
                if (str.equals("home")) {
                    c = 1;
                    break;
                }
                break;
            case 3655441:
                if (str.equals("work")) {
                    c = 2;
                    break;
                }
                break;
            case 106069776:
                if (str.equals("other")) {
                    c = 3;
                    break;
                }
                break;
            case 443164224:
                if (str.equals("personal")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 4;
            case 1:
            case 4:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return 0;
        }
    }

    private int mapStringToPostalAddressType(String str) {
        str.hashCode();
        if (!str.equals("home")) {
            return !str.equals("work") ? 0 : 2;
        }
        return 1;
    }

    public String getName() {
        return "Contacts";
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        Promise promise;
        if ((i != REQUEST_OPEN_CONTACT_FORM && i != REQUEST_OPEN_EXISTING_CONTACT) || (promise = updateContactPromise) == null) {
            return;
        }
        if (i2 != -1) {
            promise.resolve((Object) null);
            updateContactPromise = null;
        } else if (intent == null) {
            promise.reject("Error received activity result with no data!");
            updateContactPromise = null;
        } else {
            try {
                Uri data = intent.getData();
                if (data == null) {
                    updateContactPromise.reject("Error wrong data. No content uri found!");
                    updateContactPromise = null;
                    return;
                }
                updateContactPromise.resolve(new ContactsProvider(getReactApplicationContext().getContentResolver()).getContactById(data.getLastPathSegment()));
                updateContactPromise = null;
            } catch (Exception e) {
                updateContactPromise.reject(e.getMessage());
            }
        }
    }
}
