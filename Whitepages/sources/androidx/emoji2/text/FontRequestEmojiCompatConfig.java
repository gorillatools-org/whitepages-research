package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class FontRequestEmojiCompatConfig extends EmojiCompat.Config {
    private static final FontProviderHelper DEFAULT_FONTS_CONTRACT = new FontProviderHelper();

    public FontRequestEmojiCompatConfig(Context context, FontRequest fontRequest) {
        super(new FontRequestMetadataLoader(context, fontRequest, DEFAULT_FONTS_CONTRACT));
    }

    public FontRequestEmojiCompatConfig setLoadingExecutor(Executor executor) {
        ((FontRequestMetadataLoader) getMetadataRepoLoader()).setExecutor(executor);
        return this;
    }

    private static class FontRequestMetadataLoader implements EmojiCompat.MetadataRepoLoader {
        EmojiCompat.MetadataRepoLoaderCallback mCallback;
        private final Context mContext;
        private Executor mExecutor;
        private final FontProviderHelper mFontProviderHelper;
        private final Object mLock = new Object();
        private Handler mMainHandler;
        private Runnable mMainHandlerLoadCallback;
        private ThreadPoolExecutor mMyThreadPoolExecutor;
        private ContentObserver mObserver;
        private final FontRequest mRequest;

        FontRequestMetadataLoader(Context context, FontRequest fontRequest, FontProviderHelper fontProviderHelper) {
            Preconditions.checkNotNull(context, "Context cannot be null");
            Preconditions.checkNotNull(fontRequest, "FontRequest cannot be null");
            this.mContext = context.getApplicationContext();
            this.mRequest = fontRequest;
            this.mFontProviderHelper = fontProviderHelper;
        }

        public void setExecutor(Executor executor) {
            synchronized (this.mLock) {
                this.mExecutor = executor;
            }
        }

        public void load(EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback) {
            Preconditions.checkNotNull(metadataRepoLoaderCallback, "LoaderCallback cannot be null");
            synchronized (this.mLock) {
                this.mCallback = metadataRepoLoaderCallback;
            }
            loadInternal();
        }

        /* access modifiers changed from: package-private */
        public void loadInternal() {
            synchronized (this.mLock) {
                try {
                    if (this.mCallback != null) {
                        if (this.mExecutor == null) {
                            ThreadPoolExecutor createBackgroundPriorityExecutor = ConcurrencyHelpers.createBackgroundPriorityExecutor("emojiCompat");
                            this.mMyThreadPoolExecutor = createBackgroundPriorityExecutor;
                            this.mExecutor = createBackgroundPriorityExecutor;
                        }
                        this.mExecutor.execute(new FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda0(this));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        private FontsContractCompat.FontInfo retrieveFontInfo() {
            try {
                FontsContractCompat.FontFamilyResult fetchFonts = this.mFontProviderHelper.fetchFonts(this.mContext, this.mRequest);
                if (fetchFonts.getStatusCode() == 0) {
                    FontsContractCompat.FontInfo[] fonts = fetchFonts.getFonts();
                    if (fonts != null && fonts.length != 0) {
                        return fonts[0];
                    }
                    throw new RuntimeException("fetchFonts failed (empty result)");
                }
                throw new RuntimeException("fetchFonts failed (" + fetchFonts.getStatusCode() + ")");
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException("provider not found", e);
            }
        }

        private void cleanUp() {
            synchronized (this.mLock) {
                try {
                    this.mCallback = null;
                    ContentObserver contentObserver = this.mObserver;
                    if (contentObserver != null) {
                        this.mFontProviderHelper.unregisterObserver(this.mContext, contentObserver);
                        this.mObserver = null;
                    }
                    Handler handler = this.mMainHandler;
                    if (handler != null) {
                        handler.removeCallbacks(this.mMainHandlerLoadCallback);
                    }
                    this.mMainHandler = null;
                    ThreadPoolExecutor threadPoolExecutor = this.mMyThreadPoolExecutor;
                    if (threadPoolExecutor != null) {
                        threadPoolExecutor.shutdown();
                    }
                    this.mExecutor = null;
                    this.mMyThreadPoolExecutor = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            r0 = retrieveFontInfo();
            r1 = r0.getResultCode();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
            if (r1 != 2) goto L_0x0022;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
            r2 = r4.mLock;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0020, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0022, code lost:
            if (r1 != 0) goto L_0x0069;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            androidx.core.os.TraceCompat.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
            r1 = r4.mFontProviderHelper.buildTypeface(r4.mContext, r0);
            r0 = androidx.core.graphics.TypefaceCompatUtil.mmap(r4.mContext, (android.os.CancellationSignal) null, r0.getUri());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x003c, code lost:
            if (r0 == null) goto L_0x005d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x003e, code lost:
            if (r1 == null) goto L_0x005d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0040, code lost:
            r0 = androidx.emoji2.text.MetadataRepo.create(r1, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            androidx.core.os.TraceCompat.endSection();
            r1 = r4.mLock;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0049, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            r2 = r4.mCallback;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x004c, code lost:
            if (r2 == null) goto L_0x0054;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x004e, code lost:
            r2.onLoaded(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0052, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0054, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
            cleanUp();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x005b, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0064, code lost:
            throw new java.lang.RuntimeException("Unable to open file.");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
            androidx.core.os.TraceCompat.endSection();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0068, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0084, code lost:
            throw new java.lang.RuntimeException("fetchFonts result is not OK. (" + r1 + ")");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0087, code lost:
            monitor-enter(r4.mLock);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
            r2 = r4.mCallback;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x008a, code lost:
            if (r2 != null) goto L_0x008c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x008c, code lost:
            r2.onFailed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x0090, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x0093, code lost:
            cleanUp();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x0098, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void createMetadata() {
            /*
                r4 = this;
                java.lang.Object r0 = r4.mLock
                monitor-enter(r0)
                androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback r1 = r4.mCallback     // Catch:{ all -> 0x0009 }
                if (r1 != 0) goto L_0x000c
                monitor-exit(r0)     // Catch:{ all -> 0x0009 }
                return
            L_0x0009:
                r1 = move-exception
                goto L_0x0099
            L_0x000c:
                monitor-exit(r0)     // Catch:{ all -> 0x0009 }
                androidx.core.provider.FontsContractCompat$FontInfo r0 = r4.retrieveFontInfo()     // Catch:{ all -> 0x0020 }
                int r1 = r0.getResultCode()     // Catch:{ all -> 0x0020 }
                r2 = 2
                if (r1 != r2) goto L_0x0022
                java.lang.Object r2 = r4.mLock     // Catch:{ all -> 0x0020 }
                monitor-enter(r2)     // Catch:{ all -> 0x0020 }
                monitor-exit(r2)     // Catch:{ all -> 0x001d }
                goto L_0x0022
            L_0x001d:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x001d }
                throw r0     // Catch:{ all -> 0x0020 }
            L_0x0020:
                r0 = move-exception
                goto L_0x0085
            L_0x0022:
                if (r1 != 0) goto L_0x0069
                java.lang.String r1 = "EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface"
                androidx.core.os.TraceCompat.beginSection(r1)     // Catch:{ all -> 0x005b }
                androidx.emoji2.text.FontRequestEmojiCompatConfig$FontProviderHelper r1 = r4.mFontProviderHelper     // Catch:{ all -> 0x005b }
                android.content.Context r2 = r4.mContext     // Catch:{ all -> 0x005b }
                android.graphics.Typeface r1 = r1.buildTypeface(r2, r0)     // Catch:{ all -> 0x005b }
                android.content.Context r2 = r4.mContext     // Catch:{ all -> 0x005b }
                android.net.Uri r0 = r0.getUri()     // Catch:{ all -> 0x005b }
                r3 = 0
                java.nio.ByteBuffer r0 = androidx.core.graphics.TypefaceCompatUtil.mmap(r2, r3, r0)     // Catch:{ all -> 0x005b }
                if (r0 == 0) goto L_0x005d
                if (r1 == 0) goto L_0x005d
                androidx.emoji2.text.MetadataRepo r0 = androidx.emoji2.text.MetadataRepo.create(r1, r0)     // Catch:{ all -> 0x005b }
                androidx.core.os.TraceCompat.endSection()     // Catch:{ all -> 0x0020 }
                java.lang.Object r1 = r4.mLock     // Catch:{ all -> 0x0020 }
                monitor-enter(r1)     // Catch:{ all -> 0x0020 }
                androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback r2 = r4.mCallback     // Catch:{ all -> 0x0052 }
                if (r2 == 0) goto L_0x0054
                r2.onLoaded(r0)     // Catch:{ all -> 0x0052 }
                goto L_0x0054
            L_0x0052:
                r0 = move-exception
                goto L_0x0059
            L_0x0054:
                monitor-exit(r1)     // Catch:{ all -> 0x0052 }
                r4.cleanUp()     // Catch:{ all -> 0x0020 }
                goto L_0x0096
            L_0x0059:
                monitor-exit(r1)     // Catch:{ all -> 0x0052 }
                throw r0     // Catch:{ all -> 0x0020 }
            L_0x005b:
                r0 = move-exception
                goto L_0x0065
            L_0x005d:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x005b }
                java.lang.String r1 = "Unable to open file."
                r0.<init>(r1)     // Catch:{ all -> 0x005b }
                throw r0     // Catch:{ all -> 0x005b }
            L_0x0065:
                androidx.core.os.TraceCompat.endSection()     // Catch:{ all -> 0x0020 }
                throw r0     // Catch:{ all -> 0x0020 }
            L_0x0069:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0020 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
                r2.<init>()     // Catch:{ all -> 0x0020 }
                java.lang.String r3 = "fetchFonts result is not OK. ("
                r2.append(r3)     // Catch:{ all -> 0x0020 }
                r2.append(r1)     // Catch:{ all -> 0x0020 }
                java.lang.String r1 = ")"
                r2.append(r1)     // Catch:{ all -> 0x0020 }
                java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0020 }
                r0.<init>(r1)     // Catch:{ all -> 0x0020 }
                throw r0     // Catch:{ all -> 0x0020 }
            L_0x0085:
                java.lang.Object r1 = r4.mLock
                monitor-enter(r1)
                androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback r2 = r4.mCallback     // Catch:{ all -> 0x0090 }
                if (r2 == 0) goto L_0x0092
                r2.onFailed(r0)     // Catch:{ all -> 0x0090 }
                goto L_0x0092
            L_0x0090:
                r0 = move-exception
                goto L_0x0097
            L_0x0092:
                monitor-exit(r1)     // Catch:{ all -> 0x0090 }
                r4.cleanUp()
            L_0x0096:
                return
            L_0x0097:
                monitor-exit(r1)     // Catch:{ all -> 0x0090 }
                throw r0
            L_0x0099:
                monitor-exit(r0)     // Catch:{ all -> 0x0009 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.FontRequestEmojiCompatConfig.FontRequestMetadataLoader.createMetadata():void");
        }
    }

    public static class FontProviderHelper {
        public FontsContractCompat.FontFamilyResult fetchFonts(Context context, FontRequest fontRequest) {
            return FontsContractCompat.fetchFonts(context, (CancellationSignal) null, fontRequest);
        }

        public Typeface buildTypeface(Context context, FontsContractCompat.FontInfo fontInfo) {
            return FontsContractCompat.buildTypeface(context, (CancellationSignal) null, new FontsContractCompat.FontInfo[]{fontInfo});
        }

        public void unregisterObserver(Context context, ContentObserver contentObserver) {
            context.getContentResolver().unregisterContentObserver(contentObserver);
        }
    }
}
