package com.airbnb.lottie;

import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Typeface;
import com.airbnb.lottie.value.ScaleXY;

public interface LottieProperty {
    public static final Float BLUR_RADIUS;
    public static final Integer COLOR = 1;
    public static final ColorFilter COLOR_FILTER = new ColorFilter();
    public static final Float CORNER_RADIUS;
    public static final Integer DROP_SHADOW_COLOR = 5;
    public static final Float DROP_SHADOW_DIRECTION;
    public static final Float DROP_SHADOW_DISTANCE;
    public static final Float DROP_SHADOW_OPACITY;
    public static final Float DROP_SHADOW_RADIUS = Float.valueOf(18.0f);
    public static final PointF ELLIPSE_SIZE = new PointF();
    public static final Integer[] GRADIENT_COLOR = new Integer[0];
    public static final Bitmap IMAGE = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
    public static final Integer OPACITY = 4;
    public static final Path PATH = new Path();
    public static final Float POLYSTAR_INNER_RADIUS = Float.valueOf(8.0f);
    public static final Float POLYSTAR_INNER_ROUNDEDNESS = Float.valueOf(10.0f);
    public static final Float POLYSTAR_OUTER_RADIUS = Float.valueOf(9.0f);
    public static final Float POLYSTAR_OUTER_ROUNDEDNESS = Float.valueOf(11.0f);
    public static final Float POLYSTAR_POINTS = Float.valueOf(6.0f);
    public static final Float POLYSTAR_ROTATION = Float.valueOf(7.0f);
    public static final PointF POSITION = new PointF();
    public static final PointF RECTANGLE_SIZE = new PointF();
    public static final Float REPEATER_COPIES = Float.valueOf(4.0f);
    public static final Float REPEATER_OFFSET = Float.valueOf(5.0f);
    public static final Integer STROKE_COLOR = 2;
    public static final Float STROKE_WIDTH = Float.valueOf(2.0f);
    public static final CharSequence TEXT = "dynamic_text";
    public static final Float TEXT_SIZE = Float.valueOf(14.0f);
    public static final Float TEXT_TRACKING = Float.valueOf(3.0f);
    public static final Float TIME_REMAP = Float.valueOf(13.0f);
    public static final PointF TRANSFORM_ANCHOR_POINT = new PointF();
    public static final Float TRANSFORM_END_OPACITY = Float.valueOf(12.1f);
    public static final Integer TRANSFORM_OPACITY = 3;
    public static final PointF TRANSFORM_POSITION = new PointF();
    public static final Float TRANSFORM_POSITION_X;
    public static final Float TRANSFORM_POSITION_Y;
    public static final Float TRANSFORM_ROTATION = Float.valueOf(1.0f);
    public static final ScaleXY TRANSFORM_SCALE = new ScaleXY();
    public static final Float TRANSFORM_SKEW;
    public static final Float TRANSFORM_SKEW_ANGLE;
    public static final Float TRANSFORM_START_OPACITY = Float.valueOf(12.0f);
    public static final Typeface TYPEFACE = Typeface.DEFAULT;

    static {
        Float valueOf = Float.valueOf(15.0f);
        TRANSFORM_POSITION_X = valueOf;
        Float valueOf2 = Float.valueOf(16.0f);
        TRANSFORM_POSITION_Y = valueOf2;
        Float valueOf3 = Float.valueOf(17.0f);
        BLUR_RADIUS = valueOf3;
        Float valueOf4 = Float.valueOf(0.0f);
        CORNER_RADIUS = valueOf4;
        TRANSFORM_SKEW = valueOf4;
        TRANSFORM_SKEW_ANGLE = valueOf4;
        DROP_SHADOW_OPACITY = valueOf;
        DROP_SHADOW_DIRECTION = valueOf2;
        DROP_SHADOW_DISTANCE = valueOf3;
    }
}
