package androidx.emoji2.text;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EmojiCompat {
    private static final Object CONFIG_LOCK = new Object();
    private static final Object INSTANCE_LOCK = new Object();
    private static volatile EmojiCompat sInstance;
    final int[] mEmojiAsDefaultStyleExceptions;
    private final int mEmojiSpanIndicatorColor;
    private final boolean mEmojiSpanIndicatorEnabled;
    /* access modifiers changed from: private */
    public final GlyphChecker mGlyphChecker;
    private final CompatInternal mHelper;
    private final Set mInitCallbacks;
    private final ReadWriteLock mInitLock = new ReentrantReadWriteLock();
    private volatile int mLoadState = 3;
    private final Handler mMainHandler;
    private final int mMetadataLoadStrategy;
    final MetadataRepoLoader mMetadataLoader;
    final boolean mReplaceAll;
    /* access modifiers changed from: private */
    public final SpanFactory mSpanFactory;
    final boolean mUseEmojiAsDefaultStyle;

    public interface GlyphChecker {
        boolean hasGlyph(CharSequence charSequence, int i, int i2, int i3);
    }

    public static abstract class InitCallback {
        public void onFailed(Throwable th) {
        }

        public void onInitialized() {
        }
    }

    public interface MetadataRepoLoader {
        void load(MetadataRepoLoaderCallback metadataRepoLoaderCallback);
    }

    public static abstract class MetadataRepoLoaderCallback {
        public abstract void onFailed(Throwable th);

        public abstract void onLoaded(MetadataRepo metadataRepo);
    }

    public interface SpanFactory {
        EmojiSpan createSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer);
    }

    private EmojiCompat(Config config) {
        this.mReplaceAll = config.mReplaceAll;
        this.mUseEmojiAsDefaultStyle = config.mUseEmojiAsDefaultStyle;
        this.mEmojiAsDefaultStyleExceptions = config.mEmojiAsDefaultStyleExceptions;
        this.mEmojiSpanIndicatorEnabled = config.mEmojiSpanIndicatorEnabled;
        this.mEmojiSpanIndicatorColor = config.mEmojiSpanIndicatorColor;
        this.mMetadataLoader = config.mMetadataLoader;
        this.mMetadataLoadStrategy = config.mMetadataLoadStrategy;
        this.mGlyphChecker = config.mGlyphChecker;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        ArraySet arraySet = new ArraySet();
        this.mInitCallbacks = arraySet;
        SpanFactory spanFactory = config.mSpanFactory;
        this.mSpanFactory = spanFactory == null ? new DefaultSpanFactory() : spanFactory;
        Set set = config.mInitCallbacks;
        if (set != null && !set.isEmpty()) {
            arraySet.addAll(config.mInitCallbacks);
        }
        this.mHelper = new CompatInternal19(this);
        loadMetadata();
    }

    public static EmojiCompat init(Config config) {
        EmojiCompat emojiCompat = sInstance;
        if (emojiCompat == null) {
            synchronized (INSTANCE_LOCK) {
                try {
                    emojiCompat = sInstance;
                    if (emojiCompat == null) {
                        emojiCompat = new EmojiCompat(config);
                        sInstance = emojiCompat;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return emojiCompat;
    }

    public static boolean isConfigured() {
        return sInstance != null;
    }

    public static EmojiCompat get() {
        EmojiCompat emojiCompat;
        synchronized (INSTANCE_LOCK) {
            emojiCompat = sInstance;
            Preconditions.checkState(emojiCompat != null, "EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
        }
        return emojiCompat;
    }

    public void load() {
        boolean z = true;
        if (this.mMetadataLoadStrategy != 1) {
            z = false;
        }
        Preconditions.checkState(z, "Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        if (!isInitialized()) {
            this.mInitLock.writeLock().lock();
            try {
                if (this.mLoadState != 0) {
                    this.mLoadState = 0;
                    this.mInitLock.writeLock().unlock();
                    this.mHelper.loadMetadata();
                }
            } finally {
                this.mInitLock.writeLock().unlock();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private void loadMetadata() {
        this.mInitLock.writeLock().lock();
        try {
            if (this.mMetadataLoadStrategy == 0) {
                this.mLoadState = 0;
            }
            this.mInitLock.writeLock().unlock();
            if (getLoadState() == 0) {
                this.mHelper.loadMetadata();
            }
        } catch (Throwable th) {
            this.mInitLock.writeLock().unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void onMetadataLoadSuccess() {
        ArrayList arrayList = new ArrayList();
        this.mInitLock.writeLock().lock();
        try {
            this.mLoadState = 1;
            arrayList.addAll(this.mInitCallbacks);
            this.mInitCallbacks.clear();
            this.mInitLock.writeLock().unlock();
            this.mMainHandler.post(new ListenerDispatcher((Collection) arrayList, this.mLoadState));
        } catch (Throwable th) {
            this.mInitLock.writeLock().unlock();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void onMetadataLoadFailed(Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.mInitLock.writeLock().lock();
        try {
            this.mLoadState = 2;
            arrayList.addAll(this.mInitCallbacks);
            this.mInitCallbacks.clear();
            this.mInitLock.writeLock().unlock();
            this.mMainHandler.post(new ListenerDispatcher(arrayList, this.mLoadState, th));
        } catch (Throwable th2) {
            this.mInitLock.writeLock().unlock();
            throw th2;
        }
    }

    public void registerInitCallback(InitCallback initCallback) {
        Preconditions.checkNotNull(initCallback, "initCallback cannot be null");
        this.mInitLock.writeLock().lock();
        try {
            if (this.mLoadState != 1) {
                if (this.mLoadState != 2) {
                    this.mInitCallbacks.add(initCallback);
                }
            }
            this.mMainHandler.post(new ListenerDispatcher(initCallback, this.mLoadState));
        } finally {
            this.mInitLock.writeLock().unlock();
        }
    }

    public void unregisterInitCallback(InitCallback initCallback) {
        Preconditions.checkNotNull(initCallback, "initCallback cannot be null");
        this.mInitLock.writeLock().lock();
        try {
            this.mInitCallbacks.remove(initCallback);
        } finally {
            this.mInitLock.writeLock().unlock();
        }
    }

    public int getLoadState() {
        this.mInitLock.readLock().lock();
        try {
            return this.mLoadState;
        } finally {
            this.mInitLock.readLock().unlock();
        }
    }

    private boolean isInitialized() {
        return getLoadState() == 1;
    }

    public boolean isEmojiSpanIndicatorEnabled() {
        return this.mEmojiSpanIndicatorEnabled;
    }

    public int getEmojiSpanIndicatorColor() {
        return this.mEmojiSpanIndicatorColor;
    }

    public static boolean handleOnKeyDown(Editable editable, int i, KeyEvent keyEvent) {
        return EmojiProcessor.handleOnKeyDown(editable, i, keyEvent);
    }

    public static boolean handleDeleteSurroundingText(InputConnection inputConnection, Editable editable, int i, int i2, boolean z) {
        return EmojiProcessor.handleDeleteSurroundingText(inputConnection, editable, i, i2, z);
    }

    public CharSequence process(CharSequence charSequence) {
        return process(charSequence, 0, charSequence == null ? 0 : charSequence.length());
    }

    public CharSequence process(CharSequence charSequence, int i, int i2) {
        return process(charSequence, i, i2, Integer.MAX_VALUE);
    }

    public CharSequence process(CharSequence charSequence, int i, int i2, int i3) {
        return process(charSequence, i, i2, i3, 0);
    }

    public CharSequence process(CharSequence charSequence, int i, int i2, int i3, int i4) {
        boolean z;
        Preconditions.checkState(isInitialized(), "Not initialized yet");
        Preconditions.checkArgumentNonnegative(i, "start cannot be negative");
        Preconditions.checkArgumentNonnegative(i2, "end cannot be negative");
        Preconditions.checkArgumentNonnegative(i3, "maxEmojiCount cannot be negative");
        boolean z2 = false;
        Preconditions.checkArgument(i <= i2, "start should be <= than end");
        if (charSequence == null) {
            return null;
        }
        Preconditions.checkArgument(i <= charSequence.length(), "start should be < than charSequence length");
        Preconditions.checkArgument(i2 <= charSequence.length(), "end should be < than charSequence length");
        if (charSequence.length() == 0 || i == i2) {
            return charSequence;
        }
        if (i4 != 1) {
            if (i4 != 2) {
                z2 = this.mReplaceAll;
            }
            z = z2;
        } else {
            z = true;
        }
        return this.mHelper.process(charSequence, i, i2, i3, z);
    }

    public void updateEditorInfo(EditorInfo editorInfo) {
        if (isInitialized() && editorInfo != null) {
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            this.mHelper.updateEditorInfoAttrs(editorInfo);
        }
    }

    public static class DefaultSpanFactory implements SpanFactory {
        public EmojiSpan createSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            return new TypefaceEmojiSpan(typefaceEmojiRasterizer);
        }
    }

    public static abstract class Config {
        int[] mEmojiAsDefaultStyleExceptions;
        int mEmojiSpanIndicatorColor = -16711936;
        boolean mEmojiSpanIndicatorEnabled;
        GlyphChecker mGlyphChecker = new DefaultGlyphChecker();
        Set mInitCallbacks;
        int mMetadataLoadStrategy = 0;
        final MetadataRepoLoader mMetadataLoader;
        boolean mReplaceAll;
        SpanFactory mSpanFactory;
        boolean mUseEmojiAsDefaultStyle;

        protected Config(MetadataRepoLoader metadataRepoLoader) {
            Preconditions.checkNotNull(metadataRepoLoader, "metadataLoader cannot be null.");
            this.mMetadataLoader = metadataRepoLoader;
        }

        public Config setMetadataLoadStrategy(int i) {
            this.mMetadataLoadStrategy = i;
            return this;
        }

        /* access modifiers changed from: protected */
        public final MetadataRepoLoader getMetadataRepoLoader() {
            return this.mMetadataLoader;
        }
    }

    private static class ListenerDispatcher implements Runnable {
        private final List mInitCallbacks;
        private final int mLoadState;
        private final Throwable mThrowable;

        ListenerDispatcher(InitCallback initCallback, int i) {
            this(Arrays.asList(new InitCallback[]{(InitCallback) Preconditions.checkNotNull(initCallback, "initCallback cannot be null")}), i, (Throwable) null);
        }

        ListenerDispatcher(Collection collection, int i) {
            this(collection, i, (Throwable) null);
        }

        ListenerDispatcher(Collection collection, int i, Throwable th) {
            Preconditions.checkNotNull(collection, "initCallbacks cannot be null");
            this.mInitCallbacks = new ArrayList(collection);
            this.mLoadState = i;
            this.mThrowable = th;
        }

        public void run() {
            int size = this.mInitCallbacks.size();
            int i = 0;
            if (this.mLoadState != 1) {
                while (i < size) {
                    ((InitCallback) this.mInitCallbacks.get(i)).onFailed(this.mThrowable);
                    i++;
                }
                return;
            }
            while (i < size) {
                ((InitCallback) this.mInitCallbacks.get(i)).onInitialized();
                i++;
            }
        }
    }

    private static class CompatInternal {
        final EmojiCompat mEmojiCompat;

        /* access modifiers changed from: package-private */
        public abstract void loadMetadata();

        /* access modifiers changed from: package-private */
        public abstract CharSequence process(CharSequence charSequence, int i, int i2, int i3, boolean z);

        /* access modifiers changed from: package-private */
        public abstract void updateEditorInfoAttrs(EditorInfo editorInfo);

        CompatInternal(EmojiCompat emojiCompat) {
            this.mEmojiCompat = emojiCompat;
        }
    }

    private static final class CompatInternal19 extends CompatInternal {
        private volatile MetadataRepo mMetadataRepo;
        private volatile EmojiProcessor mProcessor;

        CompatInternal19(EmojiCompat emojiCompat) {
            super(emojiCompat);
        }

        /* access modifiers changed from: package-private */
        public void loadMetadata() {
            try {
                this.mEmojiCompat.mMetadataLoader.load(new MetadataRepoLoaderCallback() {
                    public void onLoaded(MetadataRepo metadataRepo) {
                        CompatInternal19.this.onMetadataLoadSuccess(metadataRepo);
                    }

                    public void onFailed(Throwable th) {
                        CompatInternal19.this.mEmojiCompat.onMetadataLoadFailed(th);
                    }
                });
            } catch (Throwable th) {
                this.mEmojiCompat.onMetadataLoadFailed(th);
            }
        }

        /* access modifiers changed from: package-private */
        public void onMetadataLoadSuccess(MetadataRepo metadataRepo) {
            if (metadataRepo == null) {
                this.mEmojiCompat.onMetadataLoadFailed(new IllegalArgumentException("metadataRepo cannot be null"));
                return;
            }
            this.mMetadataRepo = metadataRepo;
            MetadataRepo metadataRepo2 = this.mMetadataRepo;
            SpanFactory access$000 = this.mEmojiCompat.mSpanFactory;
            GlyphChecker access$100 = this.mEmojiCompat.mGlyphChecker;
            EmojiCompat emojiCompat = this.mEmojiCompat;
            this.mProcessor = new EmojiProcessor(metadataRepo2, access$000, access$100, emojiCompat.mUseEmojiAsDefaultStyle, emojiCompat.mEmojiAsDefaultStyleExceptions, EmojiExclusions.getEmojiExclusions());
            this.mEmojiCompat.onMetadataLoadSuccess();
        }

        /* access modifiers changed from: package-private */
        public CharSequence process(CharSequence charSequence, int i, int i2, int i3, boolean z) {
            return this.mProcessor.process(charSequence, i, i2, i3, z);
        }

        /* access modifiers changed from: package-private */
        public void updateEditorInfoAttrs(EditorInfo editorInfo) {
            editorInfo.extras.putInt("android.support.text.emoji.emojiCompat_metadataVersion", this.mMetadataRepo.getMetadataVersion());
            editorInfo.extras.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", this.mEmojiCompat.mReplaceAll);
        }
    }
}
