package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.view.View;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.horcrux.svg.events.SvgLoadEvent;
import java.util.concurrent.atomic.AtomicBoolean;

class ImageView extends RenderableView {
    private String mAlign;
    private SVGLength mH;
    private int mImageHeight;
    private int mImageWidth;
    /* access modifiers changed from: private */
    public final AtomicBoolean mLoading = new AtomicBoolean(false);
    private int mMeetOrSlice;
    private SVGLength mW;
    private SVGLength mX;
    private SVGLength mY;
    /* access modifiers changed from: private */
    public String uriString;

    public ImageView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setX(Dynamic dynamic) {
        this.mX = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY(Dynamic dynamic) {
        this.mY = SVGLength.from(dynamic);
        invalidate();
    }

    public void setWidth(Dynamic dynamic) {
        this.mW = SVGLength.from(dynamic);
        invalidate();
    }

    public void setHeight(Dynamic dynamic) {
        this.mH = SVGLength.from(dynamic);
        invalidate();
    }

    public void setSrc(ReadableMap readableMap) {
        if (readableMap != null) {
            String string = readableMap.getString("uri");
            this.uriString = string;
            if (string != null && !string.isEmpty()) {
                if (!readableMap.hasKey("width") || !readableMap.hasKey("height")) {
                    this.mImageWidth = 0;
                    this.mImageHeight = 0;
                } else {
                    this.mImageWidth = readableMap.getInt("width");
                    this.mImageHeight = readableMap.getInt("height");
                }
                if (Uri.parse(this.uriString).getScheme() == null) {
                    ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(this.mContext, this.uriString);
                }
            }
        }
    }

    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f) {
        if (!this.mLoading.get()) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            ImageRequest fromUri = ImageRequest.fromUri(new ImageSource(this.mContext, this.uriString).getUri());
            if (imagePipeline.isInBitmapMemoryCache(fromUri)) {
                tryRenderFromBitmapCache(imagePipeline, fromUri, canvas, paint, f * this.mOpacity);
                return;
            }
            loadBitmap(imagePipeline, fromUri);
        }
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        this.mPath = path;
        path.addRect(getRect(), Path.Direction.CW);
        return this.mPath;
    }

    private void loadBitmap(ImagePipeline imagePipeline, ImageRequest imageRequest) {
        this.mLoading.set(true);
        imagePipeline.fetchDecodedImage(imageRequest, this.mContext).subscribe(new BaseBitmapDataSubscriber() {
            public void onNewResultImpl(Bitmap bitmap) {
                ImageView imageView = ImageView.this;
                EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(imageView.mContext, imageView.getId());
                int surfaceId = UIManagerHelper.getSurfaceId((View) ImageView.this);
                int id = ImageView.this.getId();
                ImageView imageView2 = ImageView.this;
                eventDispatcherForReactTag.dispatchEvent(new SvgLoadEvent(surfaceId, id, imageView2.mContext, imageView2.uriString, (float) bitmap.getWidth(), (float) bitmap.getHeight()));
                ImageView.this.mLoading.set(false);
                SvgView svgView = ImageView.this.getSvgView();
                if (svgView != null) {
                    svgView.invalidate();
                }
            }

            public void onFailureImpl(DataSource dataSource) {
                ImageView.this.mLoading.set(false);
                FLog.w(ReactConstants.TAG, dataSource.getFailureCause(), "RNSVG: fetchDecodedImage failed!", new Object[0]);
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }

    private RectF getRect() {
        double relativeOnWidth = relativeOnWidth(this.mX);
        double relativeOnHeight = relativeOnHeight(this.mY);
        double relativeOnWidth2 = relativeOnWidth(this.mW);
        double relativeOnHeight2 = relativeOnHeight(this.mH);
        if (relativeOnWidth2 == 0.0d) {
            relativeOnWidth2 = (double) (((float) this.mImageWidth) * this.mScale);
        }
        if (relativeOnHeight2 == 0.0d) {
            relativeOnHeight2 = (double) (((float) this.mImageHeight) * this.mScale);
        }
        return new RectF((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2));
    }

    private void doRender(Canvas canvas, Paint paint, Bitmap bitmap, float f) {
        if (this.mImageWidth == 0 || this.mImageHeight == 0) {
            this.mImageWidth = bitmap.getWidth();
            this.mImageHeight = bitmap.getHeight();
        }
        RectF rect = getRect();
        RectF rectF = new RectF(0.0f, 0.0f, (float) this.mImageWidth, (float) this.mImageHeight);
        ViewBox.getTransform(rectF, rect, this.mAlign, this.mMeetOrSlice).mapRect(rectF);
        canvas.clipPath(getPath(canvas, paint));
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
        Paint paint2 = new Paint();
        paint2.setAlpha((int) (f * 255.0f));
        canvas.drawBitmap(bitmap, (Rect) null, rectF, paint2);
        this.mCTM.mapRect(rectF);
        setClientRect(rectF);
    }

    private void tryRenderFromBitmapCache(ImagePipeline imagePipeline, ImageRequest imageRequest, Canvas canvas, Paint paint, float f) {
        CloseableReference closeableReference;
        DataSource fetchImageFromBitmapCache = imagePipeline.fetchImageFromBitmapCache(imageRequest, this.mContext);
        try {
            closeableReference = (CloseableReference) fetchImageFromBitmapCache.getResult();
            if (closeableReference == null) {
                fetchImageFromBitmapCache.close();
                return;
            }
            CloseableImage closeableImage = (CloseableImage) closeableReference.get();
            if (!(closeableImage instanceof CloseableBitmap)) {
                CloseableReference.closeSafely(closeableReference);
                fetchImageFromBitmapCache.close();
                return;
            }
            Bitmap underlyingBitmap = ((CloseableBitmap) closeableImage).getUnderlyingBitmap();
            if (underlyingBitmap == null) {
                CloseableReference.closeSafely(closeableReference);
                fetchImageFromBitmapCache.close();
                return;
            }
            doRender(canvas, paint, underlyingBitmap, f);
            CloseableReference.closeSafely(closeableReference);
            fetchImageFromBitmapCache.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } catch (Exception e2) {
            throw new IllegalStateException(e2);
        } catch (Throwable th) {
            fetchImageFromBitmapCache.close();
            throw th;
        }
    }
}
