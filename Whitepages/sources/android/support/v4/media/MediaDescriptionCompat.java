package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompatApi21;
import android.support.v4.media.MediaDescriptionCompatApi23;

public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new Parcelable.Creator() {
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            return MediaDescriptionCompat.fromMediaDescription(MediaDescriptionCompatApi21.fromParcel(parcel));
        }

        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    };
    private final CharSequence mDescription;
    private Object mDescriptionObj;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final Uri mIconUri;
    private final String mMediaId;
    private final Uri mMediaUri;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

    public int describeContents() {
        return 0;
    }

    MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.mMediaId = str;
        this.mTitle = charSequence;
        this.mSubtitle = charSequence2;
        this.mDescription = charSequence3;
        this.mIcon = bitmap;
        this.mIconUri = uri;
        this.mExtras = bundle;
        this.mMediaUri = uri2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        MediaDescriptionCompatApi21.writeToParcel(getMediaDescription(), parcel, i);
    }

    public String toString() {
        return this.mTitle + ", " + this.mSubtitle + ", " + this.mDescription;
    }

    public Object getMediaDescription() {
        Object obj = this.mDescriptionObj;
        if (obj != null) {
            return obj;
        }
        Object newInstance = MediaDescriptionCompatApi21.Builder.newInstance();
        MediaDescriptionCompatApi21.Builder.setMediaId(newInstance, this.mMediaId);
        MediaDescriptionCompatApi21.Builder.setTitle(newInstance, this.mTitle);
        MediaDescriptionCompatApi21.Builder.setSubtitle(newInstance, this.mSubtitle);
        MediaDescriptionCompatApi21.Builder.setDescription(newInstance, this.mDescription);
        MediaDescriptionCompatApi21.Builder.setIconBitmap(newInstance, this.mIcon);
        MediaDescriptionCompatApi21.Builder.setIconUri(newInstance, this.mIconUri);
        MediaDescriptionCompatApi21.Builder.setExtras(newInstance, this.mExtras);
        MediaDescriptionCompatApi23.Builder.setMediaUri(newInstance, this.mMediaUri);
        Object build = MediaDescriptionCompatApi21.Builder.build(newInstance);
        this.mDescriptionObj = build;
        return build;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.v4.media.MediaDescriptionCompat fromMediaDescription(java.lang.Object r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L_0x0074
            android.support.v4.media.MediaDescriptionCompat$Builder r1 = new android.support.v4.media.MediaDescriptionCompat$Builder
            r1.<init>()
            java.lang.String r2 = android.support.v4.media.MediaDescriptionCompatApi21.getMediaId(r8)
            r1.setMediaId(r2)
            java.lang.CharSequence r2 = android.support.v4.media.MediaDescriptionCompatApi21.getTitle(r8)
            r1.setTitle(r2)
            java.lang.CharSequence r2 = android.support.v4.media.MediaDescriptionCompatApi21.getSubtitle(r8)
            r1.setSubtitle(r2)
            java.lang.CharSequence r2 = android.support.v4.media.MediaDescriptionCompatApi21.getDescription(r8)
            r1.setDescription(r2)
            android.graphics.Bitmap r2 = android.support.v4.media.MediaDescriptionCompatApi21.getIconBitmap(r8)
            r1.setIconBitmap(r2)
            android.net.Uri r2 = android.support.v4.media.MediaDescriptionCompatApi21.getIconUri(r8)
            r1.setIconUri(r2)
            android.os.Bundle r2 = android.support.v4.media.MediaDescriptionCompatApi21.getExtras(r8)
            java.lang.String r3 = "android.support.v4.media.description.MEDIA_URI"
            if (r2 == 0) goto L_0x0044
            android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r2)
            android.os.Parcelable r4 = r2.getParcelable(r3)
            android.net.Uri r4 = (android.net.Uri) r4
            goto L_0x0045
        L_0x0044:
            r4 = r0
        L_0x0045:
            if (r4 == 0) goto L_0x005d
            java.lang.String r5 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            boolean r6 = r2.containsKey(r5)
            if (r6 == 0) goto L_0x0057
            int r6 = r2.size()
            r7 = 2
            if (r6 != r7) goto L_0x0057
            goto L_0x005e
        L_0x0057:
            r2.remove(r3)
            r2.remove(r5)
        L_0x005d:
            r0 = r2
        L_0x005e:
            r1.setExtras(r0)
            if (r4 == 0) goto L_0x0067
            r1.setMediaUri(r4)
            goto L_0x006e
        L_0x0067:
            android.net.Uri r0 = android.support.v4.media.MediaDescriptionCompatApi23.getMediaUri(r8)
            r1.setMediaUri(r0)
        L_0x006e:
            android.support.v4.media.MediaDescriptionCompat r0 = r1.build()
            r0.mDescriptionObj = r8
        L_0x0074:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.fromMediaDescription(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    public static final class Builder {
        private CharSequence mDescription;
        private Bundle mExtras;
        private Bitmap mIcon;
        private Uri mIconUri;
        private String mMediaId;
        private Uri mMediaUri;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        public Builder setMediaId(String str) {
            this.mMediaId = str;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.mSubtitle = charSequence;
            return this;
        }

        public Builder setDescription(CharSequence charSequence) {
            this.mDescription = charSequence;
            return this;
        }

        public Builder setIconBitmap(Bitmap bitmap) {
            this.mIcon = bitmap;
            return this;
        }

        public Builder setIconUri(Uri uri) {
            this.mIconUri = uri;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Builder setMediaUri(Uri uri) {
            this.mMediaUri = uri;
            return this;
        }

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
        }
    }
}
