package com.facebook.imagepipeline.image;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class BaseCloseableImage implements CloseableImage {
    private static final Set mImageExtrasList = new HashSet(Arrays.asList(new String[]{"encoded_size", "encoded_width", "encoded_height", "uri_source", "image_format", "bitmap_config", "is_rounded", "non_fatal_decode_error", "original_url", "modified_url", "image_color_space"}));
    private ImageInfo mCacheImageInfo;
    private Map mExtras = new HashMap();

    public boolean isStateful() {
        return false;
    }

    public QualityInfo getQualityInfo() {
        return ImmutableQualityInfo.FULL_QUALITY;
    }

    public Map getExtras() {
        return this.mExtras;
    }

    public void putExtras(Map map) {
        if (map != null) {
            for (String str : mImageExtrasList) {
                Object obj = map.get(str);
                if (obj != null) {
                    this.mExtras.put(str, obj);
                }
            }
        }
    }

    public void putExtra(String str, Object obj) {
        if (mImageExtrasList.contains(str)) {
            this.mExtras.put(str, obj);
        }
    }

    public ImageInfo getImageInfo() {
        if (this.mCacheImageInfo == null) {
            this.mCacheImageInfo = new ImageInfoImpl(getWidth(), getHeight(), getSizeInBytes(), getQualityInfo(), getExtras());
        }
        return this.mCacheImageInfo;
    }
}
