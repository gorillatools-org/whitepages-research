package com.facebook.binaryresource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FileBinaryResource implements BinaryResource {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final File file;

    public /* synthetic */ FileBinaryResource(File file2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file2);
    }

    public static final FileBinaryResource create(File file2) {
        return Companion.create(file2);
    }

    public static final FileBinaryResource createOrNull(File file2) {
        return Companion.createOrNull(file2);
    }

    private FileBinaryResource(File file2) {
        this.file = file2;
    }

    public final File getFile() {
        return this.file;
    }

    public InputStream openStream() {
        return new FileInputStream(this.file);
    }

    public long size() {
        return this.file.length();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FileBinaryResource)) {
            return false;
        }
        return Intrinsics.areEqual((Object) this.file, (Object) ((FileBinaryResource) obj).file);
    }

    public int hashCode() {
        return this.file.hashCode();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FileBinaryResource createOrNull(File file) {
            if (file != null) {
                return new FileBinaryResource(file, (DefaultConstructorMarker) null);
            }
            return null;
        }

        public final FileBinaryResource create(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return new FileBinaryResource(file, (DefaultConstructorMarker) null);
        }
    }
}
