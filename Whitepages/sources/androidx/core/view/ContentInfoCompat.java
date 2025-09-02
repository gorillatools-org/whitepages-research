package androidx.core.view;

import android.content.ClipData;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContentInfo;
import androidx.core.util.Preconditions;
import java.util.Objects;

public final class ContentInfoCompat {
    private final Compat mCompat;

    private interface BuilderCompat {
        ContentInfoCompat build();

        void setExtras(Bundle bundle);

        void setFlags(int i);

        void setLinkUri(Uri uri);
    }

    private interface Compat {
        ClipData getClip();

        int getFlags();

        int getSource();

        ContentInfo getWrapped();
    }

    static String sourceToString(int i) {
        if (i == 0) {
            return "SOURCE_APP";
        }
        if (i == 1) {
            return "SOURCE_CLIPBOARD";
        }
        if (i == 2) {
            return "SOURCE_INPUT_METHOD";
        }
        if (i == 3) {
            return "SOURCE_DRAG_AND_DROP";
        }
        if (i == 4) {
            return "SOURCE_AUTOFILL";
        }
        if (i != 5) {
            return String.valueOf(i);
        }
        return "SOURCE_PROCESS_TEXT";
    }

    static String flagsToString(int i) {
        if ((i & 1) != 0) {
            return "FLAG_CONVERT_TO_PLAIN_TEXT";
        }
        return String.valueOf(i);
    }

    ContentInfoCompat(Compat compat) {
        this.mCompat = compat;
    }

    public static ContentInfoCompat toContentInfoCompat(ContentInfo contentInfo) {
        return new ContentInfoCompat(new Compat31Impl(contentInfo));
    }

    public ContentInfo toContentInfo() {
        ContentInfo wrapped = this.mCompat.getWrapped();
        Objects.requireNonNull(wrapped);
        return ContentInfoCompat$$ExternalSyntheticApiModelOutline0.m(wrapped);
    }

    public String toString() {
        return this.mCompat.toString();
    }

    public ClipData getClip() {
        return this.mCompat.getClip();
    }

    public int getSource() {
        return this.mCompat.getSource();
    }

    public int getFlags() {
        return this.mCompat.getFlags();
    }

    private static final class CompatImpl implements Compat {
        private final ClipData mClip;
        private final Bundle mExtras;
        private final int mFlags;
        private final Uri mLinkUri;
        private final int mSource;

        public ContentInfo getWrapped() {
            return null;
        }

        CompatImpl(BuilderCompatImpl builderCompatImpl) {
            this.mClip = (ClipData) Preconditions.checkNotNull(builderCompatImpl.mClip);
            this.mSource = Preconditions.checkArgumentInRange(builderCompatImpl.mSource, 0, 5, "source");
            this.mFlags = Preconditions.checkFlagsArgument(builderCompatImpl.mFlags, 1);
            this.mLinkUri = builderCompatImpl.mLinkUri;
            this.mExtras = builderCompatImpl.mExtras;
        }

        public ClipData getClip() {
            return this.mClip;
        }

        public int getSource() {
            return this.mSource;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("ContentInfoCompat{clip=");
            sb.append(this.mClip.getDescription());
            sb.append(", source=");
            sb.append(ContentInfoCompat.sourceToString(this.mSource));
            sb.append(", flags=");
            sb.append(ContentInfoCompat.flagsToString(this.mFlags));
            String str2 = "";
            if (this.mLinkUri == null) {
                str = str2;
            } else {
                str = ", hasLinkUri(" + this.mLinkUri.toString().length() + ")";
            }
            sb.append(str);
            if (this.mExtras != null) {
                str2 = ", hasExtras";
            }
            sb.append(str2);
            sb.append("}");
            return sb.toString();
        }
    }

    private static final class Compat31Impl implements Compat {
        private final ContentInfo mWrapped;

        Compat31Impl(ContentInfo contentInfo) {
            this.mWrapped = ContentInfoCompat$$ExternalSyntheticApiModelOutline0.m(Preconditions.checkNotNull(contentInfo));
        }

        public ContentInfo getWrapped() {
            return this.mWrapped;
        }

        public ClipData getClip() {
            return this.mWrapped.getClip();
        }

        public int getSource() {
            return this.mWrapped.getSource();
        }

        public int getFlags() {
            return this.mWrapped.getFlags();
        }

        public String toString() {
            return "ContentInfoCompat{" + this.mWrapped + "}";
        }
    }

    public static final class Builder {
        private final BuilderCompat mBuilderCompat;

        public Builder(ClipData clipData, int i) {
            if (Build.VERSION.SDK_INT >= 31) {
                this.mBuilderCompat = new BuilderCompat31Impl(clipData, i);
            } else {
                this.mBuilderCompat = new BuilderCompatImpl(clipData, i);
            }
        }

        public Builder setFlags(int i) {
            this.mBuilderCompat.setFlags(i);
            return this;
        }

        public Builder setLinkUri(Uri uri) {
            this.mBuilderCompat.setLinkUri(uri);
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mBuilderCompat.setExtras(bundle);
            return this;
        }

        public ContentInfoCompat build() {
            return this.mBuilderCompat.build();
        }
    }

    private static final class BuilderCompatImpl implements BuilderCompat {
        ClipData mClip;
        Bundle mExtras;
        int mFlags;
        Uri mLinkUri;
        int mSource;

        BuilderCompatImpl(ClipData clipData, int i) {
            this.mClip = clipData;
            this.mSource = i;
        }

        public void setFlags(int i) {
            this.mFlags = i;
        }

        public void setLinkUri(Uri uri) {
            this.mLinkUri = uri;
        }

        public void setExtras(Bundle bundle) {
            this.mExtras = bundle;
        }

        public ContentInfoCompat build() {
            return new ContentInfoCompat(new CompatImpl(this));
        }
    }

    private static final class BuilderCompat31Impl implements BuilderCompat {
        private final ContentInfo.Builder mPlatformBuilder;

        BuilderCompat31Impl(ClipData clipData, int i) {
            this.mPlatformBuilder = ContentInfoCompat$BuilderCompat31Impl$$ExternalSyntheticApiModelOutline2.m(clipData, i);
        }

        public void setFlags(int i) {
            ContentInfo.Builder unused = this.mPlatformBuilder.setFlags(i);
        }

        public void setLinkUri(Uri uri) {
            ContentInfo.Builder unused = this.mPlatformBuilder.setLinkUri(uri);
        }

        public void setExtras(Bundle bundle) {
            ContentInfo.Builder unused = this.mPlatformBuilder.setExtras(bundle);
        }

        public ContentInfoCompat build() {
            return new ContentInfoCompat(new Compat31Impl(this.mPlatformBuilder.build()));
        }
    }
}
