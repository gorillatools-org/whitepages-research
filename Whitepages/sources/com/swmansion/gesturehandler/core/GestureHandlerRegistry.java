package com.swmansion.gesturehandler.core;

import android.view.View;
import java.util.ArrayList;

public interface GestureHandlerRegistry {
    ArrayList getHandlersForView(View view);
}
