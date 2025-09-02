package androidx.datastore.preferences.protobuf;

import androidx.datastore.preferences.protobuf.Internal;
import java.util.List;

final class ListFieldSchemaLite implements ListFieldSchema {
    ListFieldSchemaLite() {
    }

    public List mutableListAt(Object obj, long j) {
        Internal.ProtobufList protobufList = getProtobufList(obj, j);
        if (protobufList.isModifiable()) {
            return protobufList;
        }
        int size = protobufList.size();
        Internal.ProtobufList mutableCopyWithCapacity = protobufList.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
        UnsafeUtil.putObject(obj, j, mutableCopyWithCapacity);
        return mutableCopyWithCapacity;
    }

    public void makeImmutableListAt(Object obj, long j) {
        getProtobufList(obj, j).makeImmutable();
    }

    public void mergeListsAt(Object obj, Object obj2, long j) {
        Internal.ProtobufList protobufList = getProtobufList(obj, j);
        Internal.ProtobufList protobufList2 = getProtobufList(obj2, j);
        int size = protobufList.size();
        int size2 = protobufList2.size();
        if (size > 0 && size2 > 0) {
            if (!protobufList.isModifiable()) {
                protobufList = protobufList.mutableCopyWithCapacity(size2 + size);
            }
            protobufList.addAll(protobufList2);
        }
        if (size > 0) {
            protobufList2 = protobufList;
        }
        UnsafeUtil.putObject(obj, j, protobufList2);
    }

    static Internal.ProtobufList getProtobufList(Object obj, long j) {
        return (Internal.ProtobufList) UnsafeUtil.getObject(obj, j);
    }
}
