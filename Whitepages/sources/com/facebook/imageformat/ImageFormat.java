package com.facebook.imageformat;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ImageFormat {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final ImageFormat UNKNOWN = new ImageFormat("UNKNOWN", (String) null);
    private final String fileExtension;
    private final String name;

    public interface FormatChecker {
        ImageFormat determineFormat(byte[] bArr, int i);

        int getHeaderSize();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageFormat)) {
            return false;
        }
        ImageFormat imageFormat = (ImageFormat) obj;
        return Intrinsics.areEqual((Object) this.name, (Object) imageFormat.name) && Intrinsics.areEqual((Object) this.fileExtension, (Object) imageFormat.fileExtension);
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        String str = this.fileExtension;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public ImageFormat(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        this.name = str;
        this.fileExtension = str2;
    }

    public final String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
