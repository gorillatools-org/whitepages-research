package kotlin.collections;

import java.util.Map;
import kotlin.jvm.internal.markers.KMappedMarker;

interface MapWithDefault extends Map, KMappedMarker {
    Object getOrImplicitDefault(Object obj);
}
