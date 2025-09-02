package com.airbnb.lottie;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

public class LottieAnimationView extends AppCompatImageView {
    /* access modifiers changed from: private */
    public static final LottieListener DEFAULT_FAILURE_LISTENER = new LottieAnimationView$$ExternalSyntheticLambda1();
    private static final String TAG = "LottieAnimationView";
    private String animationName;
    private int animationResId;
    private boolean autoPlay = false;
    private boolean cacheComposition = true;
    private LottieTask compositionTask;
    /* access modifiers changed from: private */
    public LottieListener failureListener;
    /* access modifiers changed from: private */
    public int fallbackResource = 0;
    private boolean ignoreUnschedule = false;
    private final LottieListener loadedListener = new WeakSuccessListener(this);
    private final LottieDrawable lottieDrawable = new LottieDrawable();
    private final Set lottieOnCompositionLoadedListeners = new HashSet();
    private final Set userActionsTaken = new HashSet();
    private final LottieListener wrappedFailureListener = new WeakFailureListener(this);

    /* renamed from: com.airbnb.lottie.LottieAnimationView$1  reason: invalid class name */
    abstract class AnonymousClass1 extends LottieValueCallback {
    }

    private enum UserActionTaken {
        SET_ANIMATION,
        SET_PROGRESS,
        SET_REPEAT_MODE,
        SET_REPEAT_COUNT,
        SET_IMAGE_ASSETS,
        PLAY_OPTION
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(Throwable th) {
        if (Utils.isNetworkException(th)) {
            Logger.warning("Unable to load composition.", th);
            return;
        }
        throw new IllegalStateException("Unable to parse composition", th);
    }

    private static class WeakSuccessListener implements LottieListener {
        private final WeakReference targetReference;

        public WeakSuccessListener(LottieAnimationView lottieAnimationView) {
            this.targetReference = new WeakReference(lottieAnimationView);
        }

        public void onResult(LottieComposition lottieComposition) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) this.targetReference.get();
            if (lottieAnimationView != null) {
                lottieAnimationView.setComposition(lottieComposition);
            }
        }
    }

    private static class WeakFailureListener implements LottieListener {
        private final WeakReference targetReference;

        public WeakFailureListener(LottieAnimationView lottieAnimationView) {
            this.targetReference = new WeakReference(lottieAnimationView);
        }

        public void onResult(Throwable th) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) this.targetReference.get();
            if (lottieAnimationView != null) {
                if (lottieAnimationView.fallbackResource != 0) {
                    lottieAnimationView.setImageResource(lottieAnimationView.fallbackResource);
                }
                (lottieAnimationView.failureListener == null ? LottieAnimationView.DEFAULT_FAILURE_LISTENER : lottieAnimationView.failureListener).onResult(th);
            }
        }
    }

    public LottieAnimationView(Context context) {
        super(context);
        init((AttributeSet) null, R$attr.lottieAnimationViewStyle);
    }

    private void init(AttributeSet attributeSet, int i) {
        String string;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.LottieAnimationView, i, 0);
        this.cacheComposition = obtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_cacheComposition, true);
        boolean hasValue = obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_rawRes);
        boolean hasValue2 = obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_fileName);
        boolean hasValue3 = obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_url);
        if (!hasValue || !hasValue2) {
            if (hasValue) {
                int resourceId = obtainStyledAttributes.getResourceId(R$styleable.LottieAnimationView_lottie_rawRes, 0);
                if (resourceId != 0) {
                    setAnimation(resourceId);
                }
            } else if (hasValue2) {
                String string2 = obtainStyledAttributes.getString(R$styleable.LottieAnimationView_lottie_fileName);
                if (string2 != null) {
                    setAnimation(string2);
                }
            } else if (hasValue3 && (string = obtainStyledAttributes.getString(R$styleable.LottieAnimationView_lottie_url)) != null) {
                setAnimationFromUrl(string);
            }
            setFallbackResource(obtainStyledAttributes.getResourceId(R$styleable.LottieAnimationView_lottie_fallbackRes, 0));
            if (obtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_autoPlay, false)) {
                this.autoPlay = true;
            }
            if (obtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_loop, false)) {
                this.lottieDrawable.setRepeatCount(-1);
            }
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_repeatMode)) {
                setRepeatMode(obtainStyledAttributes.getInt(R$styleable.LottieAnimationView_lottie_repeatMode, 1));
            }
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_repeatCount)) {
                setRepeatCount(obtainStyledAttributes.getInt(R$styleable.LottieAnimationView_lottie_repeatCount, -1));
            }
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_speed)) {
                setSpeed(obtainStyledAttributes.getFloat(R$styleable.LottieAnimationView_lottie_speed, 1.0f));
            }
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_clipToCompositionBounds)) {
                setClipToCompositionBounds(obtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_clipToCompositionBounds, true));
            }
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_clipTextToBoundingBox)) {
                setClipTextToBoundingBox(obtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_clipTextToBoundingBox, false));
            }
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_defaultFontFileExtension)) {
                setDefaultFontFileExtension(obtainStyledAttributes.getString(R$styleable.LottieAnimationView_lottie_defaultFontFileExtension));
            }
            setImageAssetsFolder(obtainStyledAttributes.getString(R$styleable.LottieAnimationView_lottie_imageAssetsFolder));
            setProgressInternal(obtainStyledAttributes.getFloat(R$styleable.LottieAnimationView_lottie_progress, 0.0f), obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_progress));
            enableMergePathsForKitKatAndAbove(obtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_colorFilter)) {
                SimpleColorFilter simpleColorFilter = new SimpleColorFilter(AppCompatResources.getColorStateList(getContext(), obtainStyledAttributes.getResourceId(R$styleable.LottieAnimationView_lottie_colorFilter, -1)).getDefaultColor());
                addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER, new LottieValueCallback(simpleColorFilter));
            }
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_renderMode)) {
                int i2 = R$styleable.LottieAnimationView_lottie_renderMode;
                RenderMode renderMode = RenderMode.AUTOMATIC;
                int i3 = obtainStyledAttributes.getInt(i2, renderMode.ordinal());
                if (i3 >= RenderMode.values().length) {
                    i3 = renderMode.ordinal();
                }
                setRenderMode(RenderMode.values()[i3]);
            }
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_asyncUpdates)) {
                int i4 = R$styleable.LottieAnimationView_lottie_asyncUpdates;
                AsyncUpdates asyncUpdates = AsyncUpdates.AUTOMATIC;
                int i5 = obtainStyledAttributes.getInt(i4, asyncUpdates.ordinal());
                if (i5 >= RenderMode.values().length) {
                    i5 = asyncUpdates.ordinal();
                }
                setAsyncUpdates(AsyncUpdates.values()[i5]);
            }
            setIgnoreDisabledSystemAnimations(obtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_ignoreDisabledSystemAnimations, false));
            if (obtainStyledAttributes.hasValue(R$styleable.LottieAnimationView_lottie_useCompositionFrameRate)) {
                setUseCompositionFrameRate(obtainStyledAttributes.getBoolean(R$styleable.LottieAnimationView_lottie_useCompositionFrameRate, false));
            }
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
    }

    public void setImageResource(int i) {
        this.animationResId = 0;
        this.animationName = null;
        cancelLoaderTask();
        super.setImageResource(i);
    }

    public void setImageDrawable(Drawable drawable) {
        this.animationResId = 0;
        this.animationName = null;
        cancelLoaderTask();
        super.setImageDrawable(drawable);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.animationResId = 0;
        this.animationName = null;
        cancelLoaderTask();
        super.setImageBitmap(bitmap);
    }

    public void unscheduleDrawable(Drawable drawable) {
        LottieDrawable lottieDrawable2;
        if (!this.ignoreUnschedule && drawable == (lottieDrawable2 = this.lottieDrawable) && lottieDrawable2.isAnimating()) {
            pauseAnimation();
        } else if (!this.ignoreUnschedule && (drawable instanceof LottieDrawable)) {
            LottieDrawable lottieDrawable3 = (LottieDrawable) drawable;
            if (lottieDrawable3.isAnimating()) {
                lottieDrawable3.pauseAnimation();
            }
        }
        super.unscheduleDrawable(drawable);
    }

    public void invalidate() {
        super.invalidate();
        Drawable drawable = getDrawable();
        if ((drawable instanceof LottieDrawable) && ((LottieDrawable) drawable).getRenderMode() == RenderMode.SOFTWARE) {
            this.lottieDrawable.invalidateSelf();
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        Drawable drawable2 = getDrawable();
        LottieDrawable lottieDrawable2 = this.lottieDrawable;
        if (drawable2 == lottieDrawable2) {
            super.invalidateDrawable(lottieDrawable2);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.animationName = this.animationName;
        savedState.animationResId = this.animationResId;
        savedState.progress = this.lottieDrawable.getProgress();
        savedState.isAnimating = this.lottieDrawable.isAnimatingOrWillAnimateOnVisible();
        savedState.imageAssetsFolder = this.lottieDrawable.getImageAssetsFolder();
        savedState.repeatMode = this.lottieDrawable.getRepeatMode();
        savedState.repeatCount = this.lottieDrawable.getRepeatCount();
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        int i;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.animationName = savedState.animationName;
        Set set = this.userActionsTaken;
        UserActionTaken userActionTaken = UserActionTaken.SET_ANIMATION;
        if (!set.contains(userActionTaken) && !TextUtils.isEmpty(this.animationName)) {
            setAnimation(this.animationName);
        }
        this.animationResId = savedState.animationResId;
        if (!this.userActionsTaken.contains(userActionTaken) && (i = this.animationResId) != 0) {
            setAnimation(i);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_PROGRESS)) {
            setProgressInternal(savedState.progress, false);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.PLAY_OPTION) && savedState.isAnimating) {
            playAnimation();
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_IMAGE_ASSETS)) {
            setImageAssetsFolder(savedState.imageAssetsFolder);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_MODE)) {
            setRepeatMode(savedState.repeatMode);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_COUNT)) {
            setRepeatCount(savedState.repeatCount);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode() && this.autoPlay) {
            this.lottieDrawable.playAnimation();
        }
    }

    @Deprecated
    public void setIgnoreDisabledSystemAnimations(boolean z) {
        this.lottieDrawable.setIgnoreDisabledSystemAnimations(z);
    }

    public void setUseCompositionFrameRate(boolean z) {
        this.lottieDrawable.setUseCompositionFrameRate(z);
    }

    public void enableMergePathsForKitKatAndAbove(boolean z) {
        this.lottieDrawable.enableFeatureFlag(LottieFeatureFlag.MergePathsApi19, z);
    }

    public void setClipToCompositionBounds(boolean z) {
        this.lottieDrawable.setClipToCompositionBounds(z);
    }

    public boolean getClipToCompositionBounds() {
        return this.lottieDrawable.getClipToCompositionBounds();
    }

    public void setCacheComposition(boolean z) {
        this.cacheComposition = z;
    }

    public void setOutlineMasksAndMattes(boolean z) {
        this.lottieDrawable.setOutlineMasksAndMattes(z);
    }

    public void setAnimation(int i) {
        this.animationResId = i;
        this.animationName = null;
        setCompositionTask(fromRawRes(i));
    }

    private LottieTask fromRawRes(int i) {
        if (isInEditMode()) {
            return new LottieTask(new LottieAnimationView$$ExternalSyntheticLambda2(this, i), true);
        }
        return this.cacheComposition ? LottieCompositionFactory.fromRawRes(getContext(), i) : LottieCompositionFactory.fromRawRes(getContext(), i, (String) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ LottieResult lambda$fromRawRes$1(int i) {
        return this.cacheComposition ? LottieCompositionFactory.fromRawResSync(getContext(), i) : LottieCompositionFactory.fromRawResSync(getContext(), i, (String) null);
    }

    public void setAnimation(String str) {
        this.animationName = str;
        this.animationResId = 0;
        setCompositionTask(fromAssets(str));
    }

    private LottieTask fromAssets(String str) {
        if (isInEditMode()) {
            return new LottieTask(new LottieAnimationView$$ExternalSyntheticLambda0(this, str), true);
        }
        return this.cacheComposition ? LottieCompositionFactory.fromAsset(getContext(), str) : LottieCompositionFactory.fromAsset(getContext(), str, (String) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ LottieResult lambda$fromAssets$2(String str) {
        return this.cacheComposition ? LottieCompositionFactory.fromAssetSync(getContext(), str) : LottieCompositionFactory.fromAssetSync(getContext(), str, (String) null);
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        setAnimationFromJson(str, (String) null);
    }

    public void setAnimationFromJson(String str, String str2) {
        setAnimation((InputStream) new ByteArrayInputStream(str.getBytes()), str2);
    }

    public void setAnimation(InputStream inputStream, String str) {
        setCompositionTask(LottieCompositionFactory.fromJsonInputStream(inputStream, str));
    }

    public void setAnimation(ZipInputStream zipInputStream, String str) {
        setCompositionTask(LottieCompositionFactory.fromZipStream(zipInputStream, str));
    }

    public void setAnimationFromUrl(String str) {
        setCompositionTask(this.cacheComposition ? LottieCompositionFactory.fromUrl(getContext(), str) : LottieCompositionFactory.fromUrl(getContext(), str, (String) null));
    }

    public void setAnimationFromUrl(String str, String str2) {
        setCompositionTask(LottieCompositionFactory.fromUrl(getContext(), str, str2));
    }

    public void setFailureListener(LottieListener lottieListener) {
        this.failureListener = lottieListener;
    }

    public void setFallbackResource(int i) {
        this.fallbackResource = i;
    }

    private void setCompositionTask(LottieTask lottieTask) {
        LottieResult result = lottieTask.getResult();
        LottieDrawable lottieDrawable2 = this.lottieDrawable;
        if (result == null || lottieDrawable2 != getDrawable() || lottieDrawable2.getComposition() != result.getValue()) {
            this.userActionsTaken.add(UserActionTaken.SET_ANIMATION);
            clearComposition();
            cancelLoaderTask();
            this.compositionTask = lottieTask.addListener(this.loadedListener).addFailureListener(this.wrappedFailureListener);
        }
    }

    private void cancelLoaderTask() {
        LottieTask lottieTask = this.compositionTask;
        if (lottieTask != null) {
            lottieTask.removeListener(this.loadedListener);
            this.compositionTask.removeFailureListener(this.wrappedFailureListener);
        }
    }

    public void setComposition(LottieComposition lottieComposition) {
        if (L.DBG) {
            String str = TAG;
            Log.v(str, "Set Composition \n" + lottieComposition);
        }
        this.lottieDrawable.setCallback(this);
        this.ignoreUnschedule = true;
        boolean composition = this.lottieDrawable.setComposition(lottieComposition);
        if (this.autoPlay) {
            this.lottieDrawable.playAnimation();
        }
        this.ignoreUnschedule = false;
        if (getDrawable() != this.lottieDrawable || composition) {
            if (!composition) {
                setLottieDrawable();
            }
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            for (LottieOnCompositionLoadedListener onCompositionLoaded : this.lottieOnCompositionLoadedListeners) {
                onCompositionLoaded.onCompositionLoaded(lottieComposition);
            }
        }
    }

    public LottieComposition getComposition() {
        Drawable drawable = getDrawable();
        LottieDrawable lottieDrawable2 = this.lottieDrawable;
        if (drawable == lottieDrawable2) {
            return lottieDrawable2.getComposition();
        }
        return null;
    }

    public void playAnimation() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.playAnimation();
    }

    public void resumeAnimation() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.resumeAnimation();
    }

    public void setMinFrame(int i) {
        this.lottieDrawable.setMinFrame(i);
    }

    public float getMinFrame() {
        return this.lottieDrawable.getMinFrame();
    }

    public void setMinProgress(float f) {
        this.lottieDrawable.setMinProgress(f);
    }

    public void setMaxFrame(int i) {
        this.lottieDrawable.setMaxFrame(i);
    }

    public float getMaxFrame() {
        return this.lottieDrawable.getMaxFrame();
    }

    public void setMaxProgress(float f) {
        this.lottieDrawable.setMaxProgress(f);
    }

    public void setMinFrame(String str) {
        this.lottieDrawable.setMinFrame(str);
    }

    public void setMaxFrame(String str) {
        this.lottieDrawable.setMaxFrame(str);
    }

    public void setMinAndMaxFrame(String str) {
        this.lottieDrawable.setMinAndMaxFrame(str);
    }

    public void setMinAndMaxFrame(int i, int i2) {
        this.lottieDrawable.setMinAndMaxFrame(i, i2);
    }

    public void reverseAnimationSpeed() {
        this.lottieDrawable.reverseAnimationSpeed();
    }

    public void setSpeed(float f) {
        this.lottieDrawable.setSpeed(f);
    }

    public float getSpeed() {
        return this.lottieDrawable.getSpeed();
    }

    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.lottieDrawable.addAnimatorListener(animatorListener);
    }

    public void setRepeatMode(int i) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_MODE);
        this.lottieDrawable.setRepeatMode(i);
    }

    public int getRepeatMode() {
        return this.lottieDrawable.getRepeatMode();
    }

    public void setRepeatCount(int i) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_COUNT);
        this.lottieDrawable.setRepeatCount(i);
    }

    public int getRepeatCount() {
        return this.lottieDrawable.getRepeatCount();
    }

    public boolean isAnimating() {
        return this.lottieDrawable.isAnimating();
    }

    public void setImageAssetsFolder(String str) {
        this.lottieDrawable.setImagesAssetsFolder(str);
    }

    public String getImageAssetsFolder() {
        return this.lottieDrawable.getImageAssetsFolder();
    }

    public void setMaintainOriginalImageBounds(boolean z) {
        this.lottieDrawable.setMaintainOriginalImageBounds(z);
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.lottieDrawable.getMaintainOriginalImageBounds();
    }

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate) {
        this.lottieDrawable.setImageAssetDelegate(imageAssetDelegate);
    }

    public void setDefaultFontFileExtension(String str) {
        this.lottieDrawable.setDefaultFontFileExtension(str);
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate) {
        this.lottieDrawable.setFontAssetDelegate(fontAssetDelegate);
    }

    public void setFontMap(Map<String, Typeface> map) {
        this.lottieDrawable.setFontMap(map);
    }

    public void setTextDelegate(TextDelegate textDelegate) {
        this.lottieDrawable.setTextDelegate(textDelegate);
    }

    public void addValueCallback(KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback) {
        this.lottieDrawable.addValueCallback(keyPath, obj, lottieValueCallback);
    }

    public void cancelAnimation() {
        this.autoPlay = false;
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.cancelAnimation();
    }

    public void pauseAnimation() {
        this.autoPlay = false;
        this.lottieDrawable.pauseAnimation();
    }

    public void setFrame(int i) {
        this.lottieDrawable.setFrame(i);
    }

    public int getFrame() {
        return this.lottieDrawable.getFrame();
    }

    public void setProgress(float f) {
        setProgressInternal(f, true);
    }

    private void setProgressInternal(float f, boolean z) {
        if (z) {
            this.userActionsTaken.add(UserActionTaken.SET_PROGRESS);
        }
        this.lottieDrawable.setProgress(f);
    }

    public float getProgress() {
        return this.lottieDrawable.getProgress();
    }

    public long getDuration() {
        LottieComposition composition = getComposition();
        if (composition != null) {
            return (long) composition.getDuration();
        }
        return 0;
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        this.lottieDrawable.setPerformanceTrackingEnabled(z);
    }

    public PerformanceTracker getPerformanceTracker() {
        return this.lottieDrawable.getPerformanceTracker();
    }

    private void clearComposition() {
        this.lottieDrawable.clearComposition();
    }

    public void setSafeMode(boolean z) {
        this.lottieDrawable.setSafeMode(z);
    }

    public void setRenderMode(RenderMode renderMode) {
        this.lottieDrawable.setRenderMode(renderMode);
    }

    public RenderMode getRenderMode() {
        return this.lottieDrawable.getRenderMode();
    }

    public AsyncUpdates getAsyncUpdates() {
        return this.lottieDrawable.getAsyncUpdates();
    }

    public boolean getAsyncUpdatesEnabled() {
        return this.lottieDrawable.getAsyncUpdatesEnabled();
    }

    public void setAsyncUpdates(AsyncUpdates asyncUpdates) {
        this.lottieDrawable.setAsyncUpdates(asyncUpdates);
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.lottieDrawable.setApplyingOpacityToLayersEnabled(z);
    }

    public boolean getClipTextToBoundingBox() {
        return this.lottieDrawable.getClipTextToBoundingBox();
    }

    public void setClipTextToBoundingBox(boolean z) {
        this.lottieDrawable.setClipTextToBoundingBox(z);
    }

    public boolean addLottieOnCompositionLoadedListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        LottieComposition composition = getComposition();
        if (composition != null) {
            lottieOnCompositionLoadedListener.onCompositionLoaded(composition);
        }
        return this.lottieOnCompositionLoadedListeners.add(lottieOnCompositionLoadedListener);
    }

    private void setLottieDrawable() {
        boolean isAnimating = isAnimating();
        setImageDrawable((Drawable) null);
        setImageDrawable(this.lottieDrawable);
        if (isAnimating) {
            this.lottieDrawable.resumeAnimation();
        }
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (AnonymousClass1) null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        String animationName;
        int animationResId;
        String imageAssetsFolder;
        boolean isAnimating;
        float progress;
        int repeatCount;
        int repeatMode;

        /* synthetic */ SavedState(Parcel parcel, AnonymousClass1 r2) {
            this(parcel);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.animationName = parcel.readString();
            this.progress = parcel.readFloat();
            this.isAnimating = parcel.readInt() != 1 ? false : true;
            this.imageAssetsFolder = parcel.readString();
            this.repeatMode = parcel.readInt();
            this.repeatCount = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.animationName);
            parcel.writeFloat(this.progress);
            parcel.writeInt(this.isAnimating ? 1 : 0);
            parcel.writeString(this.imageAssetsFolder);
            parcel.writeInt(this.repeatMode);
            parcel.writeInt(this.repeatCount);
        }
    }
}
