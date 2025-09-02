package com.swmansion.rnscreens;

import java.util.List;

public interface ChildDrawingOrderStrategy {
    void apply(List list);

    void disable();

    void enable();
}
