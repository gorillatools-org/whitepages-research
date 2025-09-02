package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class DiskLruCache implements Closeable, Flushable {
    public static final long ANY_SEQUENCE_NUMBER = -1;
    public static final String CLEAN = "CLEAN";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DIRTY = "DIRTY";
    public static final String JOURNAL_FILE = "journal";
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    public static final String JOURNAL_FILE_TEMP = "journal.tmp";
    public static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
    public static final String MAGIC = "libcore.io.DiskLruCache";
    public static final String READ = "READ";
    public static final String REMOVE = "REMOVE";
    public static final String VERSION_1 = "1";
    private final int appVersion;
    /* access modifiers changed from: private */
    public boolean civilizedFileSystem;
    private final TaskQueue cleanupQueue;
    private final DiskLruCache$cleanupTask$1 cleanupTask;
    private boolean closed;
    private final File directory;
    private final FileSystem fileSystem;
    /* access modifiers changed from: private */
    public boolean hasJournalErrors;
    /* access modifiers changed from: private */
    public boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    /* access modifiers changed from: private */
    public BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long maxSize;
    /* access modifiers changed from: private */
    public boolean mostRecentRebuildFailed;
    /* access modifiers changed from: private */
    public boolean mostRecentTrimFailed;
    private long nextSequenceNumber;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size;
    private final int valueCount;

    public final Editor edit(String str) throws IOException {
        return edit$default(this, str, 0, 2, (Object) null);
    }

    public DiskLruCache(FileSystem fileSystem2, File file, int i, int i2, long j, TaskRunner taskRunner) {
        Intrinsics.checkNotNullParameter(fileSystem2, "fileSystem");
        Intrinsics.checkNotNullParameter(file, "directory");
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        this.fileSystem = fileSystem2;
        this.directory = file;
        this.appVersion = i;
        this.valueCount = i2;
        this.maxSize = j;
        boolean z = false;
        this.cleanupQueue = taskRunner.newQueue();
        this.cleanupTask = new DiskLruCache$cleanupTask$1(this, Util.okHttpName + " Cache");
        if (j > 0) {
            if (i2 > 0 ? true : z) {
                this.journalFile = new File(file, JOURNAL_FILE);
                this.journalFileTmp = new File(file, JOURNAL_FILE_TEMP);
                this.journalFileBackup = new File(file, JOURNAL_FILE_BACKUP);
                return;
            }
            throw new IllegalArgumentException("valueCount <= 0");
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public final FileSystem getFileSystem$okhttp() {
        return this.fileSystem;
    }

    public final File getDirectory() {
        return this.directory;
    }

    public final int getValueCount$okhttp() {
        return this.valueCount;
    }

    public final synchronized long getMaxSize() {
        return this.maxSize;
    }

    public final synchronized void setMaxSize(long j) {
        this.maxSize = j;
        if (this.initialized) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, (Object) null);
        }
    }

    public final LinkedHashMap<String, Entry> getLruEntries$okhttp() {
        return this.lruEntries;
    }

    public final boolean getClosed$okhttp() {
        return this.closed;
    }

    public final void setClosed$okhttp(boolean z) {
        this.closed = z;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:17|18|(1:20)(1:21)|22|23|24) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r9.redundantOpCount = r0 - r9.lruEntries.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        if (r1.exhausted() == false) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006c, code lost:
        rebuildJournal$okhttp();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0070, code lost:
        r9.journalWriter = newJournalWriter();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0076, code lost:
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0078, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ac, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ad, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b0, code lost:
        throw r2;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x005d */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x005d=Splitter:B:17:0x005d, B:25:0x007d=Splitter:B:25:0x007d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void readJournal() throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r0 = ", "
            okhttp3.internal.io.FileSystem r1 = r9.fileSystem
            java.io.File r2 = r9.journalFile
            okio.Source r1 = r1.source(r2)
            okio.BufferedSource r1 = okio.Okio.buffer((okio.Source) r1)
            java.lang.String r2 = r1.readUtf8LineStrict()     // Catch:{ all -> 0x005b }
            java.lang.String r3 = r1.readUtf8LineStrict()     // Catch:{ all -> 0x005b }
            java.lang.String r4 = r1.readUtf8LineStrict()     // Catch:{ all -> 0x005b }
            java.lang.String r5 = r1.readUtf8LineStrict()     // Catch:{ all -> 0x005b }
            java.lang.String r6 = r1.readUtf8LineStrict()     // Catch:{ all -> 0x005b }
            java.lang.String r7 = MAGIC     // Catch:{ all -> 0x005b }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r2)     // Catch:{ all -> 0x005b }
            if (r7 == 0) goto L_0x007d
            java.lang.String r7 = VERSION_1     // Catch:{ all -> 0x005b }
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r3)     // Catch:{ all -> 0x005b }
            if (r7 == 0) goto L_0x007d
            int r7 = r9.appVersion     // Catch:{ all -> 0x005b }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x005b }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r4)     // Catch:{ all -> 0x005b }
            if (r4 == 0) goto L_0x007d
            int r4 = r9.valueCount     // Catch:{ all -> 0x005b }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x005b }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x005b }
            if (r4 == 0) goto L_0x007d
            int r4 = r6.length()     // Catch:{ all -> 0x005b }
            if (r4 > 0) goto L_0x007d
            r0 = 0
        L_0x0051:
            java.lang.String r2 = r1.readUtf8LineStrict()     // Catch:{ EOFException -> 0x005d }
            r9.readJournalLine(r2)     // Catch:{ EOFException -> 0x005d }
            int r0 = r0 + 1
            goto L_0x0051
        L_0x005b:
            r0 = move-exception
            goto L_0x00ab
        L_0x005d:
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r2 = r9.lruEntries     // Catch:{ all -> 0x005b }
            int r2 = r2.size()     // Catch:{ all -> 0x005b }
            int r0 = r0 - r2
            r9.redundantOpCount = r0     // Catch:{ all -> 0x005b }
            boolean r0 = r1.exhausted()     // Catch:{ all -> 0x005b }
            if (r0 != 0) goto L_0x0070
            r9.rebuildJournal$okhttp()     // Catch:{ all -> 0x005b }
            goto L_0x0076
        L_0x0070:
            okio.BufferedSink r0 = r9.newJournalWriter()     // Catch:{ all -> 0x005b }
            r9.journalWriter = r0     // Catch:{ all -> 0x005b }
        L_0x0076:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005b }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r0)
            return
        L_0x007d:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x005b }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x005b }
            r7.<init>()     // Catch:{ all -> 0x005b }
            java.lang.String r8 = "unexpected journal header: ["
            r7.append(r8)     // Catch:{ all -> 0x005b }
            r7.append(r2)     // Catch:{ all -> 0x005b }
            r7.append(r0)     // Catch:{ all -> 0x005b }
            r7.append(r3)     // Catch:{ all -> 0x005b }
            r7.append(r0)     // Catch:{ all -> 0x005b }
            r7.append(r5)     // Catch:{ all -> 0x005b }
            r7.append(r0)     // Catch:{ all -> 0x005b }
            r7.append(r6)     // Catch:{ all -> 0x005b }
            r0 = 93
            r7.append(r0)     // Catch:{ all -> 0x005b }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x005b }
            r4.<init>(r0)     // Catch:{ all -> 0x005b }
            throw r4     // Catch:{ all -> 0x005b }
        L_0x00ab:
            throw r0     // Catch:{ all -> 0x00ac }
        L_0x00ac:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.readJournal():void");
    }

    private final BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer((Sink) new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile), new DiskLruCache$newJournalWriter$faultHidingSink$1(this)));
    }

    private final void readJournalLine(String str) throws IOException {
        String str2;
        String str3 = str;
        int indexOf$default = StringsKt.indexOf$default((CharSequence) str, ' ', 0, false, 6, (Object) null);
        if (indexOf$default != -1) {
            int i = indexOf$default + 1;
            int indexOf$default2 = StringsKt.indexOf$default((CharSequence) str, ' ', i, false, 4, (Object) null);
            if (indexOf$default2 == -1) {
                if (str3 != null) {
                    str2 = str3.substring(i);
                    Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.String).substring(startIndex)");
                    String str4 = REMOVE;
                    if (indexOf$default == str4.length() && StringsKt.startsWith$default(str3, str4, false, 2, (Object) null)) {
                        this.lruEntries.remove(str2);
                        return;
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            } else if (str3 != null) {
                str2 = str3.substring(i, indexOf$default2);
                Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            Entry entry = this.lruEntries.get(str2);
            if (entry == null) {
                entry = new Entry(this, str2);
                this.lruEntries.put(str2, entry);
            }
            if (indexOf$default2 != -1) {
                String str5 = CLEAN;
                if (indexOf$default == str5.length() && StringsKt.startsWith$default(str3, str5, false, 2, (Object) null)) {
                    int i2 = indexOf$default2 + 1;
                    if (str3 != null) {
                        String substring = str3.substring(i2);
                        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
                        List split$default = StringsKt.split$default((CharSequence) substring, new char[]{' '}, false, 0, 6, (Object) null);
                        entry.setReadable$okhttp(true);
                        entry.setCurrentEditor$okhttp((Editor) null);
                        entry.setLengths$okhttp(split$default);
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            }
            if (indexOf$default2 == -1) {
                String str6 = DIRTY;
                if (indexOf$default == str6.length() && StringsKt.startsWith$default(str3, str6, false, 2, (Object) null)) {
                    entry.setCurrentEditor$okhttp(new Editor(this, entry));
                    return;
                }
            }
            if (indexOf$default2 == -1) {
                String str7 = READ;
                if (indexOf$default == str7.length() && StringsKt.startsWith$default(str3, str7, false, 2, (Object) null)) {
                    return;
                }
            }
            throw new IOException("unexpected journal line: " + str3);
        }
        throw new IOException("unexpected journal line: " + str3);
    }

    private final void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "i.next()");
            Entry entry = next;
            int i = 0;
            if (entry.getCurrentEditor$okhttp() == null) {
                int i2 = this.valueCount;
                while (i < i2) {
                    this.size += entry.getLengths$okhttp()[i];
                    i++;
                }
            } else {
                entry.setCurrentEditor$okhttp((Editor) null);
                int i3 = this.valueCount;
                while (i < i3) {
                    this.fileSystem.delete(entry.getCleanFiles$okhttp().get(i));
                    this.fileSystem.delete(entry.getDirtyFiles$okhttp().get(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    public final synchronized void rebuildJournal$okhttp() throws IOException {
        try {
            BufferedSink bufferedSink = this.journalWriter;
            if (bufferedSink != null) {
                bufferedSink.close();
            }
            BufferedSink buffer = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
            try {
                buffer.writeUtf8(MAGIC).writeByte(10);
                buffer.writeUtf8(VERSION_1).writeByte(10);
                buffer.writeDecimalLong((long) this.appVersion).writeByte(10);
                buffer.writeDecimalLong((long) this.valueCount).writeByte(10);
                buffer.writeByte(10);
                for (Entry next : this.lruEntries.values()) {
                    if (next.getCurrentEditor$okhttp() != null) {
                        buffer.writeUtf8(DIRTY).writeByte(32);
                        buffer.writeUtf8(next.getKey$okhttp());
                        buffer.writeByte(10);
                    } else {
                        buffer.writeUtf8(CLEAN).writeByte(32);
                        buffer.writeUtf8(next.getKey$okhttp());
                        next.writeLengths$okhttp(buffer);
                        buffer.writeByte(10);
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(buffer, (Throwable) null);
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.rename(this.journalFile, this.journalFileBackup);
                }
                this.fileSystem.rename(this.journalFileTmp, this.journalFile);
                this.fileSystem.delete(this.journalFileBackup);
                this.journalWriter = newJournalWriter();
                this.hasJournalErrors = false;
                this.mostRecentRebuildFailed = false;
            } catch (Throwable th) {
                CloseableKt.closeFinally(buffer, th);
                throw th;
            }
        } finally {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005a, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized okhttp3.internal.cache.DiskLruCache.Snapshot get(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)     // Catch:{ all -> 0x0057 }
            r7.initialize()     // Catch:{ all -> 0x0057 }
            r7.checkNotClosed()     // Catch:{ all -> 0x0057 }
            r7.validateKey(r8)     // Catch:{ all -> 0x0057 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r7.lruEntries     // Catch:{ all -> 0x0057 }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x0057 }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0057 }
            r1 = 0
            if (r0 == 0) goto L_0x005d
            java.lang.String r2 = "lruEntries[key] ?: return null"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)     // Catch:{ all -> 0x0057 }
            okhttp3.internal.cache.DiskLruCache$Snapshot r0 = r0.snapshot$okhttp()     // Catch:{ all -> 0x0057 }
            if (r0 == 0) goto L_0x005b
            int r1 = r7.redundantOpCount     // Catch:{ all -> 0x0057 }
            int r1 = r1 + 1
            r7.redundantOpCount = r1     // Catch:{ all -> 0x0057 }
            okio.BufferedSink r1 = r7.journalWriter     // Catch:{ all -> 0x0057 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x0057 }
            java.lang.String r2 = READ     // Catch:{ all -> 0x0057 }
            okio.BufferedSink r1 = r1.writeUtf8(r2)     // Catch:{ all -> 0x0057 }
            r2 = 32
            okio.BufferedSink r1 = r1.writeByte(r2)     // Catch:{ all -> 0x0057 }
            okio.BufferedSink r8 = r1.writeUtf8(r8)     // Catch:{ all -> 0x0057 }
            r1 = 10
            r8.writeByte(r1)     // Catch:{ all -> 0x0057 }
            boolean r8 = r7.journalRebuildRequired()     // Catch:{ all -> 0x0057 }
            if (r8 == 0) goto L_0x0059
            okhttp3.internal.concurrent.TaskQueue r1 = r7.cleanupQueue     // Catch:{ all -> 0x0057 }
            okhttp3.internal.cache.DiskLruCache$cleanupTask$1 r2 = r7.cleanupTask     // Catch:{ all -> 0x0057 }
            r5 = 2
            r6 = 0
            r3 = 0
            okhttp3.internal.concurrent.TaskQueue.schedule$default(r1, r2, r3, r5, r6)     // Catch:{ all -> 0x0057 }
            goto L_0x0059
        L_0x0057:
            r8 = move-exception
            goto L_0x005f
        L_0x0059:
            monitor-exit(r7)
            return r0
        L_0x005b:
            monitor-exit(r7)
            return r1
        L_0x005d:
            monitor-exit(r7)
            return r1
        L_0x005f:
            monitor-exit(r7)     // Catch:{ all -> 0x0057 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.get(java.lang.String):okhttp3.internal.cache.DiskLruCache$Snapshot");
    }

    public static /* synthetic */ Editor edit$default(DiskLruCache diskLruCache, String str, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = ANY_SEQUENCE_NUMBER;
        }
        return diskLruCache.edit(str, j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002c, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized okhttp3.internal.cache.DiskLruCache.Editor edit(java.lang.String r10, long r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)     // Catch:{ all -> 0x0029 }
            r9.initialize()     // Catch:{ all -> 0x0029 }
            r9.checkNotClosed()     // Catch:{ all -> 0x0029 }
            r9.validateKey(r10)     // Catch:{ all -> 0x0029 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r9.lruEntries     // Catch:{ all -> 0x0029 }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x0029 }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0029 }
            long r1 = ANY_SEQUENCE_NUMBER     // Catch:{ all -> 0x0029 }
            int r1 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            r2 = 0
            if (r1 == 0) goto L_0x002d
            if (r0 == 0) goto L_0x002b
            long r3 = r0.getSequenceNumber$okhttp()     // Catch:{ all -> 0x0029 }
            int r11 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x002d
            goto L_0x002b
        L_0x0029:
            r10 = move-exception
            goto L_0x0092
        L_0x002b:
            monitor-exit(r9)
            return r2
        L_0x002d:
            if (r0 == 0) goto L_0x0034
            okhttp3.internal.cache.DiskLruCache$Editor r11 = r0.getCurrentEditor$okhttp()     // Catch:{ all -> 0x0029 }
            goto L_0x0035
        L_0x0034:
            r11 = r2
        L_0x0035:
            if (r11 == 0) goto L_0x0039
            monitor-exit(r9)
            return r2
        L_0x0039:
            if (r0 == 0) goto L_0x0043
            int r11 = r0.getLockingSourceCount$okhttp()     // Catch:{ all -> 0x0029 }
            if (r11 == 0) goto L_0x0043
            monitor-exit(r9)
            return r2
        L_0x0043:
            boolean r11 = r9.mostRecentTrimFailed     // Catch:{ all -> 0x0029 }
            if (r11 != 0) goto L_0x0085
            boolean r11 = r9.mostRecentRebuildFailed     // Catch:{ all -> 0x0029 }
            if (r11 == 0) goto L_0x004c
            goto L_0x0085
        L_0x004c:
            okio.BufferedSink r11 = r9.journalWriter     // Catch:{ all -> 0x0029 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)     // Catch:{ all -> 0x0029 }
            java.lang.String r12 = DIRTY     // Catch:{ all -> 0x0029 }
            okio.BufferedSink r12 = r11.writeUtf8(r12)     // Catch:{ all -> 0x0029 }
            r1 = 32
            okio.BufferedSink r12 = r12.writeByte(r1)     // Catch:{ all -> 0x0029 }
            okio.BufferedSink r12 = r12.writeUtf8(r10)     // Catch:{ all -> 0x0029 }
            r1 = 10
            r12.writeByte(r1)     // Catch:{ all -> 0x0029 }
            r11.flush()     // Catch:{ all -> 0x0029 }
            boolean r11 = r9.hasJournalErrors     // Catch:{ all -> 0x0029 }
            if (r11 == 0) goto L_0x006f
            monitor-exit(r9)
            return r2
        L_0x006f:
            if (r0 != 0) goto L_0x007b
            okhttp3.internal.cache.DiskLruCache$Entry r0 = new okhttp3.internal.cache.DiskLruCache$Entry     // Catch:{ all -> 0x0029 }
            r0.<init>(r9, r10)     // Catch:{ all -> 0x0029 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r11 = r9.lruEntries     // Catch:{ all -> 0x0029 }
            r11.put(r10, r0)     // Catch:{ all -> 0x0029 }
        L_0x007b:
            okhttp3.internal.cache.DiskLruCache$Editor r10 = new okhttp3.internal.cache.DiskLruCache$Editor     // Catch:{ all -> 0x0029 }
            r10.<init>(r9, r0)     // Catch:{ all -> 0x0029 }
            r0.setCurrentEditor$okhttp(r10)     // Catch:{ all -> 0x0029 }
            monitor-exit(r9)
            return r10
        L_0x0085:
            okhttp3.internal.concurrent.TaskQueue r3 = r9.cleanupQueue     // Catch:{ all -> 0x0029 }
            okhttp3.internal.cache.DiskLruCache$cleanupTask$1 r4 = r9.cleanupTask     // Catch:{ all -> 0x0029 }
            r7 = 2
            r8 = 0
            r5 = 0
            okhttp3.internal.concurrent.TaskQueue.schedule$default(r3, r4, r5, r7, r8)     // Catch:{ all -> 0x0029 }
            monitor-exit(r9)
            return r2
        L_0x0092:
            monitor-exit(r9)     // Catch:{ all -> 0x0029 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.edit(java.lang.String, long):okhttp3.internal.cache.DiskLruCache$Editor");
    }

    public final synchronized long size() throws IOException {
        initialize();
        return this.size;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0138, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void completeEdit$okhttp(okhttp3.internal.cache.DiskLruCache.Editor r9, boolean r10) throws java.io.IOException {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.String r0 = "editor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)     // Catch:{ all -> 0x0044 }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = r9.getEntry$okhttp()     // Catch:{ all -> 0x0044 }
            okhttp3.internal.cache.DiskLruCache$Editor r1 = r0.getCurrentEditor$okhttp()     // Catch:{ all -> 0x0044 }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r9)     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0139
            r1 = 0
            if (r10 == 0) goto L_0x0064
            boolean r2 = r0.getReadable$okhttp()     // Catch:{ all -> 0x0044 }
            if (r2 != 0) goto L_0x0064
            int r2 = r8.valueCount     // Catch:{ all -> 0x0044 }
            r3 = r1
        L_0x0020:
            if (r3 >= r2) goto L_0x0064
            boolean[] r4 = r9.getWritten$okhttp()     // Catch:{ all -> 0x0044 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0044 }
            boolean r4 = r4[r3]     // Catch:{ all -> 0x0044 }
            if (r4 == 0) goto L_0x004a
            okhttp3.internal.io.FileSystem r4 = r8.fileSystem     // Catch:{ all -> 0x0044 }
            java.util.List r5 = r0.getDirtyFiles$okhttp()     // Catch:{ all -> 0x0044 }
            java.lang.Object r5 = r5.get(r3)     // Catch:{ all -> 0x0044 }
            java.io.File r5 = (java.io.File) r5     // Catch:{ all -> 0x0044 }
            boolean r4 = r4.exists(r5)     // Catch:{ all -> 0x0044 }
            if (r4 != 0) goto L_0x0047
            r9.abort()     // Catch:{ all -> 0x0044 }
            monitor-exit(r8)
            return
        L_0x0044:
            r9 = move-exception
            goto L_0x0141
        L_0x0047:
            int r3 = r3 + 1
            goto L_0x0020
        L_0x004a:
            r9.abort()     // Catch:{ all -> 0x0044 }
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0044 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r10.<init>()     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r10.append(r0)     // Catch:{ all -> 0x0044 }
            r10.append(r3)     // Catch:{ all -> 0x0044 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0044 }
            r9.<init>(r10)     // Catch:{ all -> 0x0044 }
            throw r9     // Catch:{ all -> 0x0044 }
        L_0x0064:
            int r9 = r8.valueCount     // Catch:{ all -> 0x0044 }
        L_0x0066:
            if (r1 >= r9) goto L_0x00b2
            java.util.List r2 = r0.getDirtyFiles$okhttp()     // Catch:{ all -> 0x0044 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0044 }
            java.io.File r2 = (java.io.File) r2     // Catch:{ all -> 0x0044 }
            if (r10 == 0) goto L_0x00aa
            boolean r3 = r0.getZombie$okhttp()     // Catch:{ all -> 0x0044 }
            if (r3 != 0) goto L_0x00aa
            okhttp3.internal.io.FileSystem r3 = r8.fileSystem     // Catch:{ all -> 0x0044 }
            boolean r3 = r3.exists(r2)     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x00af
            java.util.List r3 = r0.getCleanFiles$okhttp()     // Catch:{ all -> 0x0044 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x0044 }
            java.io.File r3 = (java.io.File) r3     // Catch:{ all -> 0x0044 }
            okhttp3.internal.io.FileSystem r4 = r8.fileSystem     // Catch:{ all -> 0x0044 }
            r4.rename(r2, r3)     // Catch:{ all -> 0x0044 }
            long[] r2 = r0.getLengths$okhttp()     // Catch:{ all -> 0x0044 }
            r4 = r2[r1]     // Catch:{ all -> 0x0044 }
            okhttp3.internal.io.FileSystem r2 = r8.fileSystem     // Catch:{ all -> 0x0044 }
            long r2 = r2.size(r3)     // Catch:{ all -> 0x0044 }
            long[] r6 = r0.getLengths$okhttp()     // Catch:{ all -> 0x0044 }
            r6[r1] = r2     // Catch:{ all -> 0x0044 }
            long r6 = r8.size     // Catch:{ all -> 0x0044 }
            long r6 = r6 - r4
            long r6 = r6 + r2
            r8.size = r6     // Catch:{ all -> 0x0044 }
            goto L_0x00af
        L_0x00aa:
            okhttp3.internal.io.FileSystem r3 = r8.fileSystem     // Catch:{ all -> 0x0044 }
            r3.delete(r2)     // Catch:{ all -> 0x0044 }
        L_0x00af:
            int r1 = r1 + 1
            goto L_0x0066
        L_0x00b2:
            r9 = 0
            r0.setCurrentEditor$okhttp(r9)     // Catch:{ all -> 0x0044 }
            boolean r9 = r0.getZombie$okhttp()     // Catch:{ all -> 0x0044 }
            if (r9 == 0) goto L_0x00c1
            r8.removeEntry$okhttp(r0)     // Catch:{ all -> 0x0044 }
            monitor-exit(r8)
            return
        L_0x00c1:
            int r9 = r8.redundantOpCount     // Catch:{ all -> 0x0044 }
            r1 = 1
            int r9 = r9 + r1
            r8.redundantOpCount = r9     // Catch:{ all -> 0x0044 }
            okio.BufferedSink r9 = r8.journalWriter     // Catch:{ all -> 0x0044 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch:{ all -> 0x0044 }
            boolean r2 = r0.getReadable$okhttp()     // Catch:{ all -> 0x0044 }
            r3 = 10
            r4 = 32
            if (r2 != 0) goto L_0x00f6
            if (r10 == 0) goto L_0x00d9
            goto L_0x00f6
        L_0x00d9:
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r10 = r8.lruEntries     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = r0.getKey$okhttp()     // Catch:{ all -> 0x0044 }
            r10.remove(r1)     // Catch:{ all -> 0x0044 }
            java.lang.String r10 = REMOVE     // Catch:{ all -> 0x0044 }
            okio.BufferedSink r10 = r9.writeUtf8(r10)     // Catch:{ all -> 0x0044 }
            r10.writeByte(r4)     // Catch:{ all -> 0x0044 }
            java.lang.String r10 = r0.getKey$okhttp()     // Catch:{ all -> 0x0044 }
            r9.writeUtf8(r10)     // Catch:{ all -> 0x0044 }
            r9.writeByte(r3)     // Catch:{ all -> 0x0044 }
            goto L_0x011b
        L_0x00f6:
            r0.setReadable$okhttp(r1)     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = CLEAN     // Catch:{ all -> 0x0044 }
            okio.BufferedSink r1 = r9.writeUtf8(r1)     // Catch:{ all -> 0x0044 }
            r1.writeByte(r4)     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = r0.getKey$okhttp()     // Catch:{ all -> 0x0044 }
            r9.writeUtf8(r1)     // Catch:{ all -> 0x0044 }
            r0.writeLengths$okhttp(r9)     // Catch:{ all -> 0x0044 }
            r9.writeByte(r3)     // Catch:{ all -> 0x0044 }
            if (r10 == 0) goto L_0x011b
            long r1 = r8.nextSequenceNumber     // Catch:{ all -> 0x0044 }
            r3 = 1
            long r3 = r3 + r1
            r8.nextSequenceNumber = r3     // Catch:{ all -> 0x0044 }
            r0.setSequenceNumber$okhttp(r1)     // Catch:{ all -> 0x0044 }
        L_0x011b:
            r9.flush()     // Catch:{ all -> 0x0044 }
            long r9 = r8.size     // Catch:{ all -> 0x0044 }
            long r0 = r8.maxSize     // Catch:{ all -> 0x0044 }
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 > 0) goto L_0x012c
            boolean r9 = r8.journalRebuildRequired()     // Catch:{ all -> 0x0044 }
            if (r9 == 0) goto L_0x0137
        L_0x012c:
            okhttp3.internal.concurrent.TaskQueue r0 = r8.cleanupQueue     // Catch:{ all -> 0x0044 }
            okhttp3.internal.cache.DiskLruCache$cleanupTask$1 r1 = r8.cleanupTask     // Catch:{ all -> 0x0044 }
            r4 = 2
            r5 = 0
            r2 = 0
            okhttp3.internal.concurrent.TaskQueue.schedule$default(r0, r1, r2, r4, r5)     // Catch:{ all -> 0x0044 }
        L_0x0137:
            monitor-exit(r8)
            return
        L_0x0139:
            java.lang.String r9 = "Check failed."
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0044 }
            r10.<init>(r9)     // Catch:{ all -> 0x0044 }
            throw r10     // Catch:{ all -> 0x0044 }
        L_0x0141:
            monitor-exit(r8)     // Catch:{ all -> 0x0044 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.completeEdit$okhttp(okhttp3.internal.cache.DiskLruCache$Editor, boolean):void");
    }

    /* access modifiers changed from: private */
    public final boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean remove(java.lang.String r6) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)     // Catch:{ all -> 0x0030 }
            r5.initialize()     // Catch:{ all -> 0x0030 }
            r5.checkNotClosed()     // Catch:{ all -> 0x0030 }
            r5.validateKey(r6)     // Catch:{ all -> 0x0030 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x0030 }
            java.lang.Object r6 = r0.get(r6)     // Catch:{ all -> 0x0030 }
            okhttp3.internal.cache.DiskLruCache$Entry r6 = (okhttp3.internal.cache.DiskLruCache.Entry) r6     // Catch:{ all -> 0x0030 }
            r0 = 0
            if (r6 == 0) goto L_0x0034
            java.lang.String r1 = "lruEntries[key] ?: return false"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch:{ all -> 0x0030 }
            boolean r6 = r5.removeEntry$okhttp(r6)     // Catch:{ all -> 0x0030 }
            if (r6 == 0) goto L_0x0032
            long r1 = r5.size     // Catch:{ all -> 0x0030 }
            long r3 = r5.maxSize     // Catch:{ all -> 0x0030 }
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0032
            r5.mostRecentTrimFailed = r0     // Catch:{ all -> 0x0030 }
            goto L_0x0032
        L_0x0030:
            r6 = move-exception
            goto L_0x0036
        L_0x0032:
            monitor-exit(r5)
            return r6
        L_0x0034:
            monitor-exit(r5)
            return r0
        L_0x0036:
            monitor-exit(r5)     // Catch:{ all -> 0x0030 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.remove(java.lang.String):boolean");
    }

    public final boolean removeEntry$okhttp(Entry entry) throws IOException {
        BufferedSink bufferedSink;
        Intrinsics.checkNotNullParameter(entry, "entry");
        if (!this.civilizedFileSystem) {
            if (entry.getLockingSourceCount$okhttp() > 0 && (bufferedSink = this.journalWriter) != null) {
                bufferedSink.writeUtf8(DIRTY);
                bufferedSink.writeByte(32);
                bufferedSink.writeUtf8(entry.getKey$okhttp());
                bufferedSink.writeByte(10);
                bufferedSink.flush();
            }
            if (entry.getLockingSourceCount$okhttp() > 0 || entry.getCurrentEditor$okhttp() != null) {
                entry.setZombie$okhttp(true);
                return true;
            }
        }
        Editor currentEditor$okhttp = entry.getCurrentEditor$okhttp();
        if (currentEditor$okhttp != null) {
            currentEditor$okhttp.detach$okhttp();
        }
        int i = this.valueCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.fileSystem.delete(entry.getCleanFiles$okhttp().get(i2));
            this.size -= entry.getLengths$okhttp()[i2];
            entry.getLengths$okhttp()[i2] = 0;
        }
        this.redundantOpCount++;
        BufferedSink bufferedSink2 = this.journalWriter;
        if (bufferedSink2 != null) {
            bufferedSink2.writeUtf8(REMOVE);
            bufferedSink2.writeByte(32);
            bufferedSink2.writeUtf8(entry.getKey$okhttp());
            bufferedSink2.writeByte(10);
        }
        this.lruEntries.remove(entry.getKey$okhttp());
        if (journalRebuildRequired()) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, (Object) null);
        }
        return true;
    }

    public final synchronized void initialize() throws IOException {
        try {
            if (Util.assertionsEnabled) {
                if (!Thread.holdsLock(this)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Thread ");
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
                    sb.append(currentThread.getName());
                    sb.append(" MUST hold lock on ");
                    sb.append(this);
                    throw new AssertionError(sb.toString());
                }
            }
            if (!this.initialized) {
                if (this.fileSystem.exists(this.journalFileBackup)) {
                    if (this.fileSystem.exists(this.journalFile)) {
                        this.fileSystem.delete(this.journalFileBackup);
                    } else {
                        this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                    }
                }
                this.civilizedFileSystem = Util.isCivilized(this.fileSystem, this.journalFileBackup);
                if (this.fileSystem.exists(this.journalFile)) {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                    return;
                }
                rebuildJournal$okhttp();
                this.initialized = true;
            }
        } catch (IOException e) {
            Platform platform = Platform.Companion.get();
            platform.log("DiskLruCache " + this.directory + " is corrupt: " + e.getMessage() + ", removing", 5, e);
            delete();
            this.closed = false;
        } catch (Throwable th) {
            this.closed = false;
            throw th;
        }
    }

    private final synchronized void checkNotClosed() {
        if (this.closed) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.flush();
        }
    }

    public final synchronized boolean isClosed() {
        return this.closed;
    }

    public synchronized void close() throws IOException {
        Editor currentEditor$okhttp;
        try {
            if (this.initialized) {
                if (!this.closed) {
                    Collection<Entry> values = this.lruEntries.values();
                    Intrinsics.checkNotNullExpressionValue(values, "lruEntries.values");
                    Object[] array = values.toArray(new Entry[0]);
                    if (array != null) {
                        for (Entry entry : (Entry[]) array) {
                            if (!(entry.getCurrentEditor$okhttp() == null || (currentEditor$okhttp = entry.getCurrentEditor$okhttp()) == null)) {
                                currentEditor$okhttp.detach$okhttp();
                            }
                        }
                        trimToSize();
                        BufferedSink bufferedSink = this.journalWriter;
                        Intrinsics.checkNotNull(bufferedSink);
                        bufferedSink.close();
                        this.journalWriter = null;
                        this.closed = true;
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
            this.closed = true;
        } finally {
            while (true) {
            }
        }
    }

    public final void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            if (!removeOldestEntry()) {
                return;
            }
        }
        this.mostRecentTrimFailed = false;
    }

    private final boolean removeOldestEntry() {
        for (Entry next : this.lruEntries.values()) {
            if (!next.getZombie$okhttp()) {
                Intrinsics.checkNotNullExpressionValue(next, "toEvict");
                removeEntry$okhttp(next);
                return true;
            }
        }
        return false;
    }

    public final void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    public final synchronized void evictAll() throws IOException {
        try {
            initialize();
            Collection<Entry> values = this.lruEntries.values();
            Intrinsics.checkNotNullExpressionValue(values, "lruEntries.values");
            Object[] array = values.toArray(new Entry[0]);
            if (array != null) {
                for (Entry entry : (Entry[]) array) {
                    Intrinsics.checkNotNullExpressionValue(entry, "entry");
                    removeEntry$okhttp(entry);
                }
                this.mostRecentTrimFailed = false;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private final void validateKey(String str) {
        if (!LEGAL_KEY_PATTERN.matches(str)) {
            throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + '\"').toString());
        }
    }

    public final synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new DiskLruCache$snapshots$1(this);
    }

    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final List<Source> sources;
        final /* synthetic */ DiskLruCache this$0;

        public Snapshot(DiskLruCache diskLruCache, String str, long j, List<? extends Source> list, long[] jArr) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(list, "sources");
            Intrinsics.checkNotNullParameter(jArr, "lengths");
            this.this$0 = diskLruCache;
            this.key = str;
            this.sequenceNumber = j;
            this.sources = list;
            this.lengths = jArr;
        }

        public final String key() {
            return this.key;
        }

        public final Editor edit() throws IOException {
            return this.this$0.edit(this.key, this.sequenceNumber);
        }

        public final Source getSource(int i) {
            return this.sources.get(i);
        }

        public final long getLength(int i) {
            return this.lengths[i];
        }

        public void close() {
            for (Source closeQuietly : this.sources) {
                Util.closeQuietly((Closeable) closeQuietly);
            }
        }
    }

    public final class Editor {
        private boolean done;
        private final Entry entry;
        final /* synthetic */ DiskLruCache this$0;
        private final boolean[] written;

        public Editor(DiskLruCache diskLruCache, Entry entry2) {
            Intrinsics.checkNotNullParameter(entry2, "entry");
            this.this$0 = diskLruCache;
            this.entry = entry2;
            this.written = entry2.getReadable$okhttp() ? null : new boolean[diskLruCache.getValueCount$okhttp()];
        }

        public final Entry getEntry$okhttp() {
            return this.entry;
        }

        public final boolean[] getWritten$okhttp() {
            return this.written;
        }

        public final void detach$okhttp() {
            if (!Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                return;
            }
            if (this.this$0.civilizedFileSystem) {
                this.this$0.completeEdit$okhttp(this, false);
            } else {
                this.entry.setZombie$okhttp(true);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final okio.Source newSource(int r5) {
            /*
                r4 = this;
                okhttp3.internal.cache.DiskLruCache r0 = r4.this$0
                monitor-enter(r0)
                boolean r1 = r4.done     // Catch:{ all -> 0x003c }
                if (r1 != 0) goto L_0x0042
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r4.entry     // Catch:{ all -> 0x003c }
                boolean r1 = r1.getReadable$okhttp()     // Catch:{ all -> 0x003c }
                r2 = 0
                if (r1 == 0) goto L_0x0040
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r4.entry     // Catch:{ all -> 0x003c }
                okhttp3.internal.cache.DiskLruCache$Editor r1 = r1.getCurrentEditor$okhttp()     // Catch:{ all -> 0x003c }
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)     // Catch:{ all -> 0x003c }
                if (r1 == 0) goto L_0x0040
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r4.entry     // Catch:{ all -> 0x003c }
                boolean r1 = r1.getZombie$okhttp()     // Catch:{ all -> 0x003c }
                if (r1 == 0) goto L_0x0025
                goto L_0x0040
            L_0x0025:
                okhttp3.internal.cache.DiskLruCache r1 = r4.this$0     // Catch:{ FileNotFoundException -> 0x003e }
                okhttp3.internal.io.FileSystem r1 = r1.getFileSystem$okhttp()     // Catch:{ FileNotFoundException -> 0x003e }
                okhttp3.internal.cache.DiskLruCache$Entry r3 = r4.entry     // Catch:{ FileNotFoundException -> 0x003e }
                java.util.List r3 = r3.getCleanFiles$okhttp()     // Catch:{ FileNotFoundException -> 0x003e }
                java.lang.Object r5 = r3.get(r5)     // Catch:{ FileNotFoundException -> 0x003e }
                java.io.File r5 = (java.io.File) r5     // Catch:{ FileNotFoundException -> 0x003e }
                okio.Source r2 = r1.source(r5)     // Catch:{ FileNotFoundException -> 0x003e }
                goto L_0x003e
            L_0x003c:
                r5 = move-exception
                goto L_0x004a
            L_0x003e:
                monitor-exit(r0)
                return r2
            L_0x0040:
                monitor-exit(r0)
                return r2
            L_0x0042:
                java.lang.String r5 = "Check failed."
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x003c }
                r1.<init>(r5)     // Catch:{ all -> 0x003c }
                throw r1     // Catch:{ all -> 0x003c }
            L_0x004a:
                monitor-exit(r0)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Editor.newSource(int):okio.Source");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(4:22|23|24|25) */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            r5 = okio.Okio.blackhole();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0052, code lost:
            return r5;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x004d */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final okio.Sink newSink(int r5) {
            /*
                r4 = this;
                okhttp3.internal.cache.DiskLruCache r0 = r4.this$0
                monitor-enter(r0)
                boolean r1 = r4.done     // Catch:{ all -> 0x0019 }
                if (r1 != 0) goto L_0x0053
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r4.entry     // Catch:{ all -> 0x0019 }
                okhttp3.internal.cache.DiskLruCache$Editor r1 = r1.getCurrentEditor$okhttp()     // Catch:{ all -> 0x0019 }
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)     // Catch:{ all -> 0x0019 }
                if (r1 != 0) goto L_0x001b
                okio.Sink r5 = okio.Okio.blackhole()     // Catch:{ all -> 0x0019 }
                monitor-exit(r0)
                return r5
            L_0x0019:
                r5 = move-exception
                goto L_0x005b
            L_0x001b:
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r4.entry     // Catch:{ all -> 0x0019 }
                boolean r1 = r1.getReadable$okhttp()     // Catch:{ all -> 0x0019 }
                if (r1 != 0) goto L_0x002b
                boolean[] r1 = r4.written     // Catch:{ all -> 0x0019 }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x0019 }
                r2 = 1
                r1[r5] = r2     // Catch:{ all -> 0x0019 }
            L_0x002b:
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r4.entry     // Catch:{ all -> 0x0019 }
                java.util.List r1 = r1.getDirtyFiles$okhttp()     // Catch:{ all -> 0x0019 }
                java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0019 }
                java.io.File r1 = (java.io.File) r1     // Catch:{ all -> 0x0019 }
                okhttp3.internal.cache.DiskLruCache r2 = r4.this$0     // Catch:{ FileNotFoundException -> 0x004d }
                okhttp3.internal.io.FileSystem r2 = r2.getFileSystem$okhttp()     // Catch:{ FileNotFoundException -> 0x004d }
                okio.Sink r1 = r2.sink(r1)     // Catch:{ FileNotFoundException -> 0x004d }
                okhttp3.internal.cache.FaultHidingSink r2 = new okhttp3.internal.cache.FaultHidingSink     // Catch:{ all -> 0x0019 }
                okhttp3.internal.cache.DiskLruCache$Editor$newSink$$inlined$synchronized$lambda$1 r3 = new okhttp3.internal.cache.DiskLruCache$Editor$newSink$$inlined$synchronized$lambda$1     // Catch:{ all -> 0x0019 }
                r3.<init>(r4, r5)     // Catch:{ all -> 0x0019 }
                r2.<init>(r1, r3)     // Catch:{ all -> 0x0019 }
                monitor-exit(r0)
                return r2
            L_0x004d:
                okio.Sink r5 = okio.Okio.blackhole()     // Catch:{ all -> 0x0019 }
                monitor-exit(r0)
                return r5
            L_0x0053:
                java.lang.String r5 = "Check failed."
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0019 }
                r1.<init>(r5)     // Catch:{ all -> 0x0019 }
                throw r1     // Catch:{ all -> 0x0019 }
            L_0x005b:
                monitor-exit(r0)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Editor.newSink(int):okio.Sink");
        }

        public final void commit() throws IOException {
            synchronized (this.this$0) {
                try {
                    if (!this.done) {
                        if (Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                            this.this$0.completeEdit$okhttp(this, true);
                        }
                        this.done = true;
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new IllegalStateException("Check failed.");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final void abort() throws IOException {
            synchronized (this.this$0) {
                try {
                    if (!this.done) {
                        if (Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                            this.this$0.completeEdit$okhttp(this, false);
                        }
                        this.done = true;
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new IllegalStateException("Check failed.");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final class Entry {
        private final List<File> cleanFiles = new ArrayList();
        private Editor currentEditor;
        private final List<File> dirtyFiles = new ArrayList();
        private final String key;
        private final long[] lengths;
        private int lockingSourceCount;
        private boolean readable;
        private long sequenceNumber;
        final /* synthetic */ DiskLruCache this$0;
        private boolean zombie;

        public Entry(DiskLruCache diskLruCache, String str) {
            Intrinsics.checkNotNullParameter(str, "key");
            this.this$0 = diskLruCache;
            this.key = str;
            this.lengths = new long[diskLruCache.getValueCount$okhttp()];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            int valueCount$okhttp = diskLruCache.getValueCount$okhttp();
            for (int i = 0; i < valueCount$okhttp; i++) {
                sb.append(i);
                this.cleanFiles.add(new File(diskLruCache.getDirectory(), sb.toString()));
                sb.append(".tmp");
                this.dirtyFiles.add(new File(diskLruCache.getDirectory(), sb.toString()));
                sb.setLength(length);
            }
        }

        public final String getKey$okhttp() {
            return this.key;
        }

        public final long[] getLengths$okhttp() {
            return this.lengths;
        }

        public final List<File> getCleanFiles$okhttp() {
            return this.cleanFiles;
        }

        public final List<File> getDirtyFiles$okhttp() {
            return this.dirtyFiles;
        }

        public final boolean getReadable$okhttp() {
            return this.readable;
        }

        public final void setReadable$okhttp(boolean z) {
            this.readable = z;
        }

        public final boolean getZombie$okhttp() {
            return this.zombie;
        }

        public final void setZombie$okhttp(boolean z) {
            this.zombie = z;
        }

        public final Editor getCurrentEditor$okhttp() {
            return this.currentEditor;
        }

        public final void setCurrentEditor$okhttp(Editor editor) {
            this.currentEditor = editor;
        }

        public final int getLockingSourceCount$okhttp() {
            return this.lockingSourceCount;
        }

        public final void setLockingSourceCount$okhttp(int i) {
            this.lockingSourceCount = i;
        }

        public final long getSequenceNumber$okhttp() {
            return this.sequenceNumber;
        }

        public final void setSequenceNumber$okhttp(long j) {
            this.sequenceNumber = j;
        }

        public final void setLengths$okhttp(List<String> list) throws IOException {
            Intrinsics.checkNotNullParameter(list, "strings");
            if (list.size() == this.this$0.getValueCount$okhttp()) {
                try {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        this.lengths[i] = Long.parseLong(list.get(i));
                    }
                } catch (NumberFormatException unused) {
                    invalidLengths(list);
                    throw new KotlinNothingValueException();
                }
            } else {
                invalidLengths(list);
                throw new KotlinNothingValueException();
            }
        }

        public final void writeLengths$okhttp(BufferedSink bufferedSink) throws IOException {
            Intrinsics.checkNotNullParameter(bufferedSink, "writer");
            for (long writeDecimalLong : this.lengths) {
                bufferedSink.writeByte(32).writeDecimalLong(writeDecimalLong);
            }
        }

        private final Void invalidLengths(List<String> list) throws IOException {
            throw new IOException("unexpected journal line: " + list);
        }

        public final Snapshot snapshot$okhttp() {
            DiskLruCache diskLruCache = this.this$0;
            if (Util.assertionsEnabled && !Thread.holdsLock(diskLruCache)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Thread ");
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
                sb.append(currentThread.getName());
                sb.append(" MUST hold lock on ");
                sb.append(diskLruCache);
                throw new AssertionError(sb.toString());
            } else if (!this.readable) {
                return null;
            } else {
                if (!this.this$0.civilizedFileSystem && (this.currentEditor != null || this.zombie)) {
                    return null;
                }
                ArrayList<Source> arrayList = new ArrayList<>();
                long[] jArr = (long[]) this.lengths.clone();
                try {
                    int valueCount$okhttp = this.this$0.getValueCount$okhttp();
                    for (int i = 0; i < valueCount$okhttp; i++) {
                        arrayList.add(newSource(i));
                    }
                    return new Snapshot(this.this$0, this.key, this.sequenceNumber, arrayList, jArr);
                } catch (FileNotFoundException unused) {
                    for (Source closeQuietly : arrayList) {
                        Util.closeQuietly((Closeable) closeQuietly);
                    }
                    try {
                        this.this$0.removeEntry$okhttp(this);
                    } catch (IOException unused2) {
                    }
                    return null;
                }
            }
        }

        private final Source newSource(int i) {
            Source source = this.this$0.getFileSystem$okhttp().source(this.cleanFiles.get(i));
            if (this.this$0.civilizedFileSystem) {
                return source;
            }
            this.lockingSourceCount++;
            return new DiskLruCache$Entry$newSource$1(this, source, source);
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
