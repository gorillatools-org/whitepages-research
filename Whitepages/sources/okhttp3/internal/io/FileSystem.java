package okhttp3.internal.io;

import com.google.firebase.messaging.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Okio;
import okio.Sink;
import okio.Source;

public interface FileSystem {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final FileSystem SYSTEM = new Companion.SystemFileSystem();

    Sink appendingSink(File file) throws FileNotFoundException;

    void delete(File file) throws IOException;

    void deleteContents(File file) throws IOException;

    boolean exists(File file);

    void rename(File file, File file2) throws IOException;

    Sink sink(File file) throws FileNotFoundException;

    long size(File file);

    Source source(File file) throws FileNotFoundException;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static final class SystemFileSystem implements FileSystem {
            public Source source(File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                return Okio.source(file);
            }

            public Sink sink(File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    return Okio__JvmOkioKt.sink$default(file, false, 1, (Object) null);
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return Okio__JvmOkioKt.sink$default(file, false, 1, (Object) null);
                }
            }

            public Sink appendingSink(File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    return Okio.appendingSink(file);
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return Okio.appendingSink(file);
                }
            }

            public void delete(File file) throws IOException {
                Intrinsics.checkNotNullParameter(file, "file");
                if (!file.delete() && file.exists()) {
                    throw new IOException("failed to delete " + file);
                }
            }

            public boolean exists(File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.exists();
            }

            public long size(File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.length();
            }

            public void rename(File file, File file2) throws IOException {
                Intrinsics.checkNotNullParameter(file, Constants.MessagePayloadKeys.FROM);
                Intrinsics.checkNotNullParameter(file2, "to");
                delete(file2);
                if (!file.renameTo(file2)) {
                    throw new IOException("failed to rename " + file + " to " + file2);
                }
            }

            public void deleteContents(File file) throws IOException {
                Intrinsics.checkNotNullParameter(file, "directory");
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    int length = listFiles.length;
                    int i = 0;
                    while (i < length) {
                        File file2 = listFiles[i];
                        Intrinsics.checkNotNullExpressionValue(file2, "file");
                        if (file2.isDirectory()) {
                            deleteContents(file2);
                        }
                        if (file2.delete()) {
                            i++;
                        } else {
                            throw new IOException("failed to delete " + file2);
                        }
                    }
                    return;
                }
                throw new IOException("not a readable directory: " + file);
            }

            public String toString() {
                return "FileSystem.SYSTEM";
            }
        }
    }
}
