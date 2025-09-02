package androidx.room;

import android.content.Context;
import android.util.Log;
import androidx.room.util.DBUtil;
import androidx.room.util.FileUtil;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.util.ProcessLock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.Callable;
import kotlin.jvm.internal.Intrinsics;

public final class SQLiteCopyOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    private final Context context;
    private final String copyFromAssetPath;
    private final File copyFromFile;
    private final Callable copyFromInputStream;
    private DatabaseConfiguration databaseConfiguration;
    private final int databaseVersion;
    private final SupportSQLiteOpenHelper delegate;
    private boolean verified;

    public SQLiteCopyOpenHelper(Context context2, String str, File file, Callable callable, int i, SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper, "delegate");
        this.context = context2;
        this.copyFromAssetPath = str;
        this.copyFromFile = file;
        this.copyFromInputStream = callable;
        this.databaseVersion = i;
        this.delegate = supportSQLiteOpenHelper;
    }

    public SupportSQLiteOpenHelper getDelegate() {
        return this.delegate;
    }

    public String getDatabaseName() {
        return getDelegate().getDatabaseName();
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        getDelegate().setWriteAheadLoggingEnabled(z);
    }

    public SupportSQLiteDatabase getWritableDatabase() {
        if (!this.verified) {
            verifyDatabaseFile(true);
            this.verified = true;
        }
        return getDelegate().getWritableDatabase();
    }

    public synchronized void close() {
        getDelegate().close();
        this.verified = false;
    }

    public final void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration2) {
        Intrinsics.checkNotNullParameter(databaseConfiguration2, "databaseConfiguration");
        this.databaseConfiguration = databaseConfiguration2;
    }

    private final void verifyDatabaseFile(boolean z) {
        String databaseName = getDatabaseName();
        if (databaseName != null) {
            File databasePath = this.context.getDatabasePath(databaseName);
            DatabaseConfiguration databaseConfiguration2 = this.databaseConfiguration;
            DatabaseConfiguration databaseConfiguration3 = null;
            if (databaseConfiguration2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("databaseConfiguration");
                databaseConfiguration2 = null;
            }
            boolean z2 = databaseConfiguration2.multiInstanceInvalidation;
            File filesDir = this.context.getFilesDir();
            Intrinsics.checkNotNullExpressionValue(filesDir, "context.filesDir");
            ProcessLock processLock = new ProcessLock(databaseName, filesDir, z2);
            try {
                ProcessLock.lock$default(processLock, false, 1, (Object) null);
                if (!databasePath.exists()) {
                    Intrinsics.checkNotNullExpressionValue(databasePath, "databaseFile");
                    copyDatabaseFile(databasePath, z);
                    processLock.unlock();
                    return;
                }
                try {
                    Intrinsics.checkNotNullExpressionValue(databasePath, "databaseFile");
                    int readVersion = DBUtil.readVersion(databasePath);
                    if (readVersion == this.databaseVersion) {
                        processLock.unlock();
                        return;
                    }
                    DatabaseConfiguration databaseConfiguration4 = this.databaseConfiguration;
                    if (databaseConfiguration4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("databaseConfiguration");
                    } else {
                        databaseConfiguration3 = databaseConfiguration4;
                    }
                    if (databaseConfiguration3.isMigrationRequired(readVersion, this.databaseVersion)) {
                        processLock.unlock();
                        return;
                    }
                    if (this.context.deleteDatabase(databaseName)) {
                        try {
                            copyDatabaseFile(databasePath, z);
                        } catch (IOException e) {
                            Log.w("ROOM", "Unable to copy database file.", e);
                        }
                    } else {
                        Log.w("ROOM", "Failed to delete database file (" + databaseName + ") for a copy destructive migration.");
                    }
                    processLock.unlock();
                } catch (IOException e2) {
                    Log.w("ROOM", "Unable to read database version.", e2);
                    processLock.unlock();
                }
            } catch (IOException e3) {
                throw new RuntimeException("Unable to copy database file.", e3);
            } catch (Throwable th) {
                processLock.unlock();
                throw th;
            }
        } else {
            throw new IllegalStateException("Required value was null.");
        }
    }

    private final void copyDatabaseFile(File file, boolean z) {
        ReadableByteChannel readableByteChannel;
        if (this.copyFromAssetPath != null) {
            readableByteChannel = Channels.newChannel(this.context.getAssets().open(this.copyFromAssetPath));
            Intrinsics.checkNotNullExpressionValue(readableByteChannel, "newChannel(context.assets.open(copyFromAssetPath))");
        } else if (this.copyFromFile != null) {
            readableByteChannel = new FileInputStream(this.copyFromFile).getChannel();
            Intrinsics.checkNotNullExpressionValue(readableByteChannel, "FileInputStream(copyFromFile).channel");
        } else {
            Callable callable = this.copyFromInputStream;
            if (callable != null) {
                try {
                    readableByteChannel = Channels.newChannel((InputStream) callable.call());
                    Intrinsics.checkNotNullExpressionValue(readableByteChannel, "newChannel(inputStream)");
                } catch (Exception e) {
                    throw new IOException("inputStreamCallable exception on call", e);
                }
            } else {
                throw new IllegalStateException("copyFromAssetPath, copyFromFile and copyFromInputStream are all null!");
            }
        }
        File createTempFile = File.createTempFile("room-copy-helper", ".tmp", this.context.getCacheDir());
        createTempFile.deleteOnExit();
        FileChannel channel = new FileOutputStream(createTempFile).getChannel();
        Intrinsics.checkNotNullExpressionValue(channel, "output");
        FileUtil.copy(readableByteChannel, channel);
        File parentFile = file.getParentFile();
        if (parentFile == null || parentFile.exists() || parentFile.mkdirs()) {
            Intrinsics.checkNotNullExpressionValue(createTempFile, "intermediateFile");
            dispatchOnOpenPrepackagedDatabase(createTempFile, z);
            if (!createTempFile.renameTo(file)) {
                throw new IOException("Failed to move intermediate file (" + createTempFile.getAbsolutePath() + ") to destination (" + file.getAbsolutePath() + ").");
            }
            return;
        }
        throw new IOException("Failed to create directories for " + file.getAbsolutePath());
    }

    private final void dispatchOnOpenPrepackagedDatabase(File file, boolean z) {
        DatabaseConfiguration databaseConfiguration2 = this.databaseConfiguration;
        if (databaseConfiguration2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseConfiguration");
            databaseConfiguration2 = null;
        }
        databaseConfiguration2.getClass();
    }
}
