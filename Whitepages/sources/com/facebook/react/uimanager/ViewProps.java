package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.Arrays;
import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;

public final class ViewProps {
    public static final String ACCESSIBILITY_ACTIONS = "accessibilityActions";
    public static final String ACCESSIBILITY_COLLECTION = "accessibilityCollection";
    public static final String ACCESSIBILITY_COLLECTION_ITEM = "accessibilityCollectionItem";
    public static final String ACCESSIBILITY_HINT = "accessibilityHint";
    public static final String ACCESSIBILITY_LABEL = "accessibilityLabel";
    public static final String ACCESSIBILITY_LABELLED_BY = "accessibilityLabelledBy";
    public static final String ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    public static final String ACCESSIBILITY_ROLE = "accessibilityRole";
    public static final String ACCESSIBILITY_STATE = "accessibilityState";
    public static final String ACCESSIBILITY_VALUE = "accessibilityValue";
    public static final String ADJUSTS_FONT_SIZE_TO_FIT = "adjustsFontSizeToFit";
    public static final String ALIGN_CONTENT = "alignContent";
    public static final String ALIGN_ITEMS = "alignItems";
    public static final String ALIGN_SELF = "alignSelf";
    public static final String ALLOW_FONT_SCALING = "allowFontScaling";
    public static final String ASPECT_RATIO = "aspectRatio";
    public static final String AUTO = "auto";
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final String BACKGROUND_IMAGE = "experimental_backgroundImage";
    public static final String BORDER_BLOCK_COLOR = "borderBlockColor";
    public static final String BORDER_BLOCK_END_COLOR = "borderBlockEndColor";
    public static final String BORDER_BLOCK_START_COLOR = "borderBlockStartColor";
    public static final String BORDER_BOTTOM_COLOR = "borderBottomColor";
    public static final String BORDER_BOTTOM_END_RADIUS = "borderBottomEndRadius";
    public static final String BORDER_BOTTOM_LEFT_RADIUS = "borderBottomLeftRadius";
    public static final String BORDER_BOTTOM_RIGHT_RADIUS = "borderBottomRightRadius";
    public static final String BORDER_BOTTOM_START_RADIUS = "borderBottomStartRadius";
    public static final String BORDER_BOTTOM_WIDTH = "borderBottomWidth";
    public static final String BORDER_COLOR = "borderColor";
    public static final String BORDER_END_COLOR = "borderEndColor";
    public static final String BORDER_END_END_RADIUS = "borderEndEndRadius";
    public static final String BORDER_END_START_RADIUS = "borderEndStartRadius";
    public static final String BORDER_END_WIDTH = "borderEndWidth";
    public static final String BORDER_LEFT_COLOR = "borderLeftColor";
    public static final String BORDER_LEFT_WIDTH = "borderLeftWidth";
    public static final String BORDER_RADIUS = "borderRadius";
    public static final String BORDER_RIGHT_COLOR = "borderRightColor";
    public static final String BORDER_RIGHT_WIDTH = "borderRightWidth";
    public static final int[] BORDER_SPACING_TYPES = {8, 4, 5, 1, 3, 0, 2};
    public static final String BORDER_START_COLOR = "borderStartColor";
    public static final String BORDER_START_END_RADIUS = "borderStartEndRadius";
    public static final String BORDER_START_START_RADIUS = "borderStartStartRadius";
    public static final String BORDER_START_WIDTH = "borderStartWidth";
    public static final String BORDER_TOP_COLOR = "borderTopColor";
    public static final String BORDER_TOP_END_RADIUS = "borderTopEndRadius";
    public static final String BORDER_TOP_LEFT_RADIUS = "borderTopLeftRadius";
    public static final String BORDER_TOP_RIGHT_RADIUS = "borderTopRightRadius";
    public static final String BORDER_TOP_START_RADIUS = "borderTopStartRadius";
    public static final String BORDER_TOP_WIDTH = "borderTopWidth";
    public static final String BORDER_WIDTH = "borderWidth";
    public static final String BOTTOM = "bottom";
    public static final String BOX_NONE = "box-none";
    public static final String BOX_SHADOW = "boxShadow";
    public static final String COLLAPSABLE = "collapsable";
    public static final String COLLAPSABLE_CHILDREN = "collapsableChildren";
    public static final String COLOR = "color";
    public static final String COLUMN_GAP = "columnGap";
    public static final String DISPLAY = "display";
    public static final String ELEVATION = "elevation";
    public static final String ELLIPSIZE_MODE = "ellipsizeMode";
    public static final String ENABLED = "enabled";
    public static final String END = "end";
    public static final String FILTER = "filter";
    public static final String FLEX = "flex";
    public static final String FLEX_BASIS = "flexBasis";
    public static final String FLEX_DIRECTION = "flexDirection";
    public static final String FLEX_GROW = "flexGrow";
    public static final String FLEX_SHRINK = "flexShrink";
    public static final String FLEX_WRAP = "flexWrap";
    public static final String FONT_FAMILY = "fontFamily";
    public static final String FONT_SIZE = "fontSize";
    public static final String FONT_STYLE = "fontStyle";
    public static final String FONT_VARIANT = "fontVariant";
    public static final String FONT_WEIGHT = "fontWeight";
    public static final String FOREGROUND_COLOR = "foregroundColor";
    public static final String GAP = "gap";
    public static final String HEIGHT = "height";
    public static final String HIDDEN = "hidden";
    public static final String IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    public static final String INCLUDE_FONT_PADDING = "includeFontPadding";
    public static final ViewProps INSTANCE = new ViewProps();
    public static final String JUSTIFY_CONTENT = "justifyContent";
    public static final String LAYOUT_DIRECTION = "layoutDirection";
    private static final HashSet<String> LAYOUT_ONLY_PROPS = new HashSet<>(Arrays.asList(new String[]{ALIGN_SELF, ALIGN_ITEMS, COLLAPSABLE, FLEX, FLEX_BASIS, FLEX_DIRECTION, FLEX_GROW, ROW_GAP, COLUMN_GAP, GAP, FLEX_SHRINK, FLEX_WRAP, JUSTIFY_CONTENT, ALIGN_CONTENT, "display", POSITION, RIGHT, TOP, BOTTOM, LEFT, START, END, "width", "height", MIN_WIDTH, MAX_WIDTH, MIN_HEIGHT, MAX_HEIGHT, MARGIN, MARGIN_VERTICAL, MARGIN_HORIZONTAL, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_TOP, MARGIN_BOTTOM, MARGIN_START, MARGIN_END, PADDING, PADDING_VERTICAL, PADDING_HORIZONTAL, PADDING_LEFT, PADDING_RIGHT, PADDING_TOP, PADDING_BOTTOM, PADDING_START, PADDING_END}));
    public static final String LEFT = "left";
    public static final String LETTER_SPACING = "letterSpacing";
    public static final String LINE_HEIGHT = "lineHeight";
    public static final String MARGIN = "margin";
    public static final String MARGIN_BOTTOM = "marginBottom";
    public static final String MARGIN_END = "marginEnd";
    public static final String MARGIN_HORIZONTAL = "marginHorizontal";
    public static final String MARGIN_LEFT = "marginLeft";
    public static final String MARGIN_RIGHT = "marginRight";
    public static final String MARGIN_START = "marginStart";
    public static final String MARGIN_TOP = "marginTop";
    public static final String MARGIN_VERTICAL = "marginVertical";
    public static final String MAX_FONT_SIZE_MULTIPLIER = "maxFontSizeMultiplier";
    public static final String MAX_HEIGHT = "maxHeight";
    public static final String MAX_WIDTH = "maxWidth";
    public static final String MINIMUM_FONT_SCALE = "minimumFontScale";
    public static final String MIN_HEIGHT = "minHeight";
    public static final String MIN_WIDTH = "minWidth";
    public static final String MIX_BLEND_MODE = "mixBlendMode";
    public static final String NATIVE_ID = "nativeID";
    public static final String NEEDS_OFFSCREEN_ALPHA_COMPOSITING = "needsOffscreenAlphaCompositing";
    public static final String NONE = "none";
    public static final String NUMBER_OF_LINES = "numberOfLines";
    public static final String ON = "on";
    public static final String ON_CLICK = "onClick";
    public static final String ON_CLICK_CAPTURE = "onClickCapture";
    public static final String ON_POINTER_ENTER = "onPointerEnter";
    public static final String ON_POINTER_ENTER_CAPTURE = "onPointerEnterCapture";
    public static final String ON_POINTER_LEAVE = "onPointerLeave";
    public static final String ON_POINTER_LEAVE_CAPTURE = "onPointerLeaveCapture";
    public static final String ON_POINTER_MOVE = "onPointerMove";
    public static final String ON_POINTER_MOVE_CAPTURE = "onPointerMoveCapture";
    public static final String ON_POINTER_OUT = "onPointerOut";
    public static final String ON_POINTER_OUT_CAPTURE = "onPointerOutCapture";
    public static final String ON_POINTER_OVER = "onPointerOver";
    public static final String ON_POINTER_OVER_CAPTURE = "onPointerOverCapture";
    public static final String OPACITY = "opacity";
    public static final String OUTLINE_COLOR = "outlineColor";
    public static final String OUTLINE_OFFSET = "outlineOffset";
    public static final String OUTLINE_STYLE = "outlineStyle";
    public static final String OUTLINE_WIDTH = "outlineWidth";
    public static final String OVERFLOW = "overflow";
    public static final String PADDING = "padding";
    public static final String PADDING_BOTTOM = "paddingBottom";
    public static final String PADDING_END = "paddingEnd";
    public static final String PADDING_HORIZONTAL = "paddingHorizontal";
    public static final String PADDING_LEFT = "paddingLeft";
    public static final int[] PADDING_MARGIN_SPACING_TYPES = {8, 7, 6, 4, 5, 1, 3, 0, 2};
    public static final String PADDING_RIGHT = "paddingRight";
    public static final String PADDING_START = "paddingStart";
    public static final String PADDING_TOP = "paddingTop";
    public static final String PADDING_VERTICAL = "paddingVertical";
    public static final String POINTER_EVENTS = "pointerEvents";
    public static final String POSITION = "position";
    public static final String RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    public static final String RESIZE_METHOD = "resizeMethod";
    public static final String RESIZE_MODE = "resizeMode";
    public static final String RIGHT = "right";
    public static final String ROLE = "role";
    public static final String ROTATION = "rotation";
    public static final String ROW_GAP = "rowGap";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String SCROLL = "scroll";
    public static final String SHADOW_COLOR = "shadowColor";
    public static final String START = "start";
    public static final String TEST_ID = "testID";
    public static final String TEXT_ALIGN = "textAlign";
    public static final String TEXT_ALIGN_VERTICAL = "textAlignVertical";
    public static final String TEXT_BREAK_STRATEGY = "textBreakStrategy";
    public static final String TEXT_DECORATION_LINE = "textDecorationLine";
    public static final String TOP = "top";
    public static final String TRANSFORM = "transform";
    public static final String TRANSFORM_ORIGIN = "transformOrigin";
    public static final String TRANSLATE_X = "translateX";
    public static final String TRANSLATE_Y = "translateY";
    public static final String VIEW_CLASS_NAME = "RCTView";
    public static final String VISIBLE = "visible";
    public static final String WIDTH = "width";
    public static final String Z_INDEX = "zIndex";

    private ViewProps() {
    }

    public static final boolean isLayoutOnly(ReadableMap readableMap, String str) {
        ReadableType type;
        Intrinsics.checkNotNullParameter(readableMap, "map");
        Intrinsics.checkNotNullParameter(str, "prop");
        if (LAYOUT_ONLY_PROPS.contains(str)) {
            return true;
        }
        if (Intrinsics.areEqual((Object) POINTER_EVENTS, (Object) str)) {
            String string = readableMap.getString(str);
            if (Intrinsics.areEqual((Object) "auto", (Object) string) || Intrinsics.areEqual((Object) BOX_NONE, (Object) string)) {
                return true;
            }
            return false;
        }
        switch (str.hashCode()) {
            case -1989576717:
                if (str.equals(BORDER_RIGHT_COLOR) && readableMap.getType(BORDER_RIGHT_COLOR) == ReadableType.Number && readableMap.getInt(BORDER_RIGHT_COLOR) == 0) {
                    return true;
                }
            case -1971292586:
                if (str.equals(BORDER_RIGHT_WIDTH) && (readableMap.isNull(BORDER_RIGHT_WIDTH) || readableMap.getDouble(BORDER_RIGHT_WIDTH) == 0.0d)) {
                    return true;
                }
            case -1470826662:
                if (str.equals(BORDER_TOP_COLOR) && readableMap.getType(BORDER_TOP_COLOR) == ReadableType.Number && readableMap.getInt(BORDER_TOP_COLOR) == 0) {
                    return true;
                }
            case -1452542531:
                if (str.equals(BORDER_TOP_WIDTH) && (readableMap.isNull(BORDER_TOP_WIDTH) || readableMap.getDouble(BORDER_TOP_WIDTH) == 0.0d)) {
                    return true;
                }
            case -1308858324:
                if (str.equals(BORDER_BOTTOM_COLOR) && readableMap.getType(BORDER_BOTTOM_COLOR) == ReadableType.Number && readableMap.getInt(BORDER_BOTTOM_COLOR) == 0) {
                    return true;
                }
            case -1290574193:
                if (str.equals(BORDER_BOTTOM_WIDTH) && (readableMap.isNull(BORDER_BOTTOM_WIDTH) || readableMap.getDouble(BORDER_BOTTOM_WIDTH) == 0.0d)) {
                    return true;
                }
            case -1267206133:
                if (str.equals(OPACITY) && (readableMap.isNull(OPACITY) || readableMap.getDouble(OPACITY) == 1.0d)) {
                    return true;
                }
            case -242276144:
                if (str.equals(BORDER_LEFT_COLOR) && readableMap.getType(BORDER_LEFT_COLOR) == ReadableType.Number && readableMap.getInt(BORDER_LEFT_COLOR) == 0) {
                    return true;
                }
            case -223992013:
                if (str.equals(BORDER_LEFT_WIDTH) && (readableMap.isNull(BORDER_LEFT_WIDTH) || readableMap.getDouble(BORDER_LEFT_WIDTH) == 0.0d)) {
                    return true;
                }
            case 306963138:
                if (str.equals(BORDER_BLOCK_START_COLOR) && readableMap.getType(BORDER_BLOCK_START_COLOR) == ReadableType.Number && readableMap.getInt(BORDER_BLOCK_START_COLOR) == 0) {
                    return true;
                }
            case 529642498:
                if (str.equals(OVERFLOW) && (readableMap.isNull(OVERFLOW) || Intrinsics.areEqual((Object) VISIBLE, (Object) readableMap.getString(OVERFLOW)))) {
                    return true;
                }
            case 684610594:
                if (str.equals(BORDER_BLOCK_COLOR) && readableMap.getType(BORDER_BLOCK_COLOR) == ReadableType.Number && readableMap.getInt(BORDER_BLOCK_COLOR) == 0) {
                    return true;
                }
            case 741115130:
                if (str.equals(BORDER_WIDTH) && (readableMap.isNull(BORDER_WIDTH) || readableMap.getDouble(BORDER_WIDTH) == 0.0d)) {
                    return true;
                }
            case 762983977:
                if (str.equals(BORDER_BLOCK_END_COLOR) && readableMap.getType(BORDER_BLOCK_END_COLOR) == ReadableType.Number && readableMap.getInt(BORDER_BLOCK_END_COLOR) == 0) {
                    return true;
                }
            case 1349188574:
                if (str.equals(BORDER_RADIUS)) {
                    if (readableMap.hasKey(BACKGROUND_COLOR) && (((type = readableMap.getType(BACKGROUND_COLOR)) == ReadableType.Number && readableMap.getInt(BACKGROUND_COLOR) != 0) || type != ReadableType.Null)) {
                        return false;
                    }
                    if (!readableMap.hasKey(BORDER_WIDTH) || readableMap.isNull(BORDER_WIDTH) || readableMap.getDouble(BORDER_WIDTH) == 0.0d) {
                        return true;
                    }
                    return false;
                }
                break;
        }
        return false;
    }
}
