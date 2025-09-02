package com.facebook.common.file;

import com.facebook.common.internal.Preconditions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileUtils {
    public static void mkdirs(File file) {
        if (file.exists()) {
            if (!file.isDirectory()) {
                if (!file.delete()) {
                    throw new CreateDirectoryException(file.getAbsolutePath(), new FileDeleteException(file.getAbsolutePath()));
                }
            } else {
                return;
            }
        }
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new CreateDirectoryException(file.getAbsolutePath());
        }
    }

    public static void rename(File file, File file2) {
        Throwable th;
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(file2);
        file2.delete();
        if (!file.renameTo(file2)) {
            if (file2.exists()) {
                th = new FileDeleteException(file2.getAbsolutePath());
            } else if (file.getParentFile().exists()) {
                th = !file.exists() ? new FileNotFoundException(file.getAbsolutePath()) : null;
            } else {
                th = new ParentDirNotFoundException(file.getAbsolutePath());
            }
            throw new RenameException("Unknown error renaming " + file.getAbsolutePath() + " to " + file2.getAbsolutePath(), th);
        }
    }

    public static class CreateDirectoryException extends IOException {
        public CreateDirectoryException(String str) {
            super(str);
        }

        public CreateDirectoryException(String str, Throwable th) {
            super(str);
            initCause(th);
        }
    }

    public static class ParentDirNotFoundException extends FileNotFoundException {
        public ParentDirNotFoundException(String str) {
            super(str);
        }
    }

    public static class FileDeleteException extends IOException {
        public FileDeleteException(String str) {
            super(str);
        }
    }

    public static class RenameException extends IOException {
        public RenameException(String str, Throwable th) {
            super(str);
            initCause(th);
        }
    }
}
