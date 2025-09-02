package androidx.datastore.preferences.protobuf;

import java.util.List;

interface ListFieldSchema {
    void makeImmutableListAt(Object obj, long j);

    void mergeListsAt(Object obj, Object obj2, long j);

    List mutableListAt(Object obj, long j);
}
