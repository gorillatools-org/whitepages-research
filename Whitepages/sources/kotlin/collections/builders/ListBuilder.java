package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableList;

public final class ListBuilder extends AbstractMutableList implements List, RandomAccess, Serializable, KMutableList {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final ListBuilder Empty;
    /* access modifiers changed from: private */
    public Object[] backing;
    /* access modifiers changed from: private */
    public boolean isReadOnly;
    /* access modifiers changed from: private */
    public int length;

    public ListBuilder(int i) {
        this.backing = ListBuilderKt.arrayOfUninitializedElements(i);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ListBuilder(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        ListBuilder listBuilder = new ListBuilder(0);
        listBuilder.isReadOnly = true;
        Empty = listBuilder;
    }

    public final List build() {
        checkIsMutable();
        this.isReadOnly = true;
        return this.length > 0 ? this : Empty;
    }

    private final Object writeReplace() {
        if (this.isReadOnly) {
            return new SerializedCollection(this, 0);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    public int getSize() {
        return this.length;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public Object get(int i) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        return this.backing[i];
    }

    public Object set(int i, Object obj) {
        checkIsMutable();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        Object[] objArr = this.backing;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        return obj2;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < this.length; i++) {
            if (Intrinsics.areEqual(this.backing[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        for (int i = this.length - 1; i >= 0; i--) {
            if (Intrinsics.areEqual(this.backing[i], obj)) {
                return i;
            }
        }
        return -1;
    }

    public Iterator iterator() {
        return listIterator(0);
    }

    public ListIterator listIterator() {
        return listIterator(0);
    }

    public ListIterator listIterator(int i) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        return new Itr(this, i);
    }

    public boolean add(Object obj) {
        checkIsMutable();
        addAtInternal(this.length, obj);
        return true;
    }

    public void add(int i, Object obj) {
        checkIsMutable();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        addAtInternal(i, obj);
    }

    public boolean addAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        int size = collection.size();
        addAllInternal(this.length, collection, size);
        return size > 0;
    }

    public boolean addAll(int i, Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
        int size = collection.size();
        addAllInternal(i, collection, size);
        return size > 0;
    }

    public void clear() {
        checkIsMutable();
        removeRangeInternal(0, this.length);
    }

    public Object removeAt(int i) {
        checkIsMutable();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
        return removeAtInternal(i);
    }

    public boolean remove(Object obj) {
        checkIsMutable();
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            remove(indexOf);
        }
        return indexOf >= 0;
    }

    public boolean removeAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        return retainOrRemoveAllInternal(0, this.length, collection, false) > 0;
    }

    public boolean retainAll(Collection collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        checkIsMutable();
        return retainOrRemoveAllInternal(0, this.length, collection, true) > 0;
    }

    public List subList(int i, int i2) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, this.length);
        return new BuilderSubList(this.backing, i, i2 - i, (BuilderSubList) null, this);
    }

    public Object[] toArray(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "array");
        int length2 = objArr.length;
        int i = this.length;
        if (length2 < i) {
            Object[] copyOfRange = Arrays.copyOfRange(this.backing, 0, i, objArr.getClass());
            Intrinsics.checkNotNullExpressionValue(copyOfRange, "copyOfRange(...)");
            return copyOfRange;
        }
        ArraysKt.copyInto(this.backing, objArr, 0, 0, i);
        return CollectionsKt.terminateCollectionToArray(this.length, objArr);
    }

    public Object[] toArray() {
        return ArraysKt.copyOfRange(this.backing, 0, this.length);
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof List) && contentEquals((List) obj));
    }

    public int hashCode() {
        return ListBuilderKt.subarrayContentHashCode(this.backing, 0, this.length);
    }

    public String toString() {
        return ListBuilderKt.subarrayContentToString(this.backing, 0, this.length, this);
    }

    private final void registerModification() {
        this.modCount++;
    }

    private final void checkIsMutable() {
        if (this.isReadOnly) {
            throw new UnsupportedOperationException();
        }
    }

    private final void ensureExtraCapacity(int i) {
        ensureCapacityInternal(this.length + i);
    }

    private final void ensureCapacityInternal(int i) {
        if (i >= 0) {
            Object[] objArr = this.backing;
            if (i > objArr.length) {
                this.backing = ListBuilderKt.copyOfUninitializedElements(this.backing, AbstractList.Companion.newCapacity$kotlin_stdlib(objArr.length, i));
                return;
            }
            return;
        }
        throw new OutOfMemoryError();
    }

    private final boolean contentEquals(List list) {
        return ListBuilderKt.subarrayContentEquals(this.backing, 0, this.length, list);
    }

    private final void insertAtInternal(int i, int i2) {
        ensureExtraCapacity(i2);
        Object[] objArr = this.backing;
        ArraysKt.copyInto(objArr, objArr, i + i2, i, this.length);
        this.length += i2;
    }

    /* access modifiers changed from: private */
    public final void addAtInternal(int i, Object obj) {
        registerModification();
        insertAtInternal(i, 1);
        this.backing[i] = obj;
    }

    /* access modifiers changed from: private */
    public final void addAllInternal(int i, Collection collection, int i2) {
        registerModification();
        insertAtInternal(i, i2);
        Iterator it = collection.iterator();
        for (int i3 = 0; i3 < i2; i3++) {
            this.backing[i + i3] = it.next();
        }
    }

    /* access modifiers changed from: private */
    public final Object removeAtInternal(int i) {
        registerModification();
        Object[] objArr = this.backing;
        Object obj = objArr[i];
        ArraysKt.copyInto(objArr, objArr, i, i + 1, this.length);
        ListBuilderKt.resetAt(this.backing, this.length - 1);
        this.length--;
        return obj;
    }

    /* access modifiers changed from: private */
    public final void removeRangeInternal(int i, int i2) {
        if (i2 > 0) {
            registerModification();
        }
        Object[] objArr = this.backing;
        ArraysKt.copyInto(objArr, objArr, i, i + i2, this.length);
        Object[] objArr2 = this.backing;
        int i3 = this.length;
        ListBuilderKt.resetRange(objArr2, i3 - i2, i3);
        this.length -= i2;
    }

    /* access modifiers changed from: private */
    public final int retainOrRemoveAllInternal(int i, int i2, Collection collection, boolean z) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i + i3;
            if (collection.contains(this.backing[i5]) == z) {
                Object[] objArr = this.backing;
                i3++;
                objArr[i4 + i] = objArr[i5];
                i4++;
            } else {
                i3++;
            }
        }
        int i6 = i2 - i4;
        Object[] objArr2 = this.backing;
        ArraysKt.copyInto(objArr2, objArr2, i + i4, i2 + i, this.length);
        Object[] objArr3 = this.backing;
        int i7 = this.length;
        ListBuilderKt.resetRange(objArr3, i7 - i6, i7);
        if (i6 > 0) {
            registerModification();
        }
        this.length -= i6;
        return i6;
    }

    private static final class Itr implements ListIterator, KMappedMarker {
        private int expectedModCount;
        private int index;
        private int lastIndex = -1;
        private final ListBuilder list;

        public Itr(ListBuilder listBuilder, int i) {
            Intrinsics.checkNotNullParameter(listBuilder, "list");
            this.list = listBuilder;
            this.index = i;
            this.expectedModCount = listBuilder.modCount;
        }

        public boolean hasPrevious() {
            return this.index > 0;
        }

        public boolean hasNext() {
            return this.index < this.list.length;
        }

        public int previousIndex() {
            return this.index - 1;
        }

        public int nextIndex() {
            return this.index;
        }

        public Object previous() {
            checkForComodification();
            int i = this.index;
            if (i > 0) {
                int i2 = i - 1;
                this.index = i2;
                this.lastIndex = i2;
                return this.list.backing[this.lastIndex];
            }
            throw new NoSuchElementException();
        }

        public Object next() {
            checkForComodification();
            if (this.index < this.list.length) {
                int i = this.index;
                this.index = i + 1;
                this.lastIndex = i;
                return this.list.backing[this.lastIndex];
            }
            throw new NoSuchElementException();
        }

        public void set(Object obj) {
            checkForComodification();
            int i = this.lastIndex;
            if (i != -1) {
                this.list.set(i, obj);
                return;
            }
            throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
        }

        public void add(Object obj) {
            checkForComodification();
            ListBuilder listBuilder = this.list;
            int i = this.index;
            this.index = i + 1;
            listBuilder.add(i, obj);
            this.lastIndex = -1;
            this.expectedModCount = this.list.modCount;
        }

        public void remove() {
            checkForComodification();
            int i = this.lastIndex;
            if (i != -1) {
                this.list.remove(i);
                this.index = this.lastIndex;
                this.lastIndex = -1;
                this.expectedModCount = this.list.modCount;
                return;
            }
            throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
        }

        private final void checkForComodification() {
            if (this.list.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    public static final class BuilderSubList extends AbstractMutableList implements List, RandomAccess, Serializable, KMutableList {
        /* access modifiers changed from: private */
        public Object[] backing;
        /* access modifiers changed from: private */
        public int length;
        /* access modifiers changed from: private */
        public final int offset;
        private final BuilderSubList parent;
        /* access modifiers changed from: private */
        public final ListBuilder root;

        public BuilderSubList(Object[] objArr, int i, int i2, BuilderSubList builderSubList, ListBuilder listBuilder) {
            Intrinsics.checkNotNullParameter(objArr, "backing");
            Intrinsics.checkNotNullParameter(listBuilder, "root");
            this.backing = objArr;
            this.offset = i;
            this.length = i2;
            this.parent = builderSubList;
            this.root = listBuilder;
            this.modCount = listBuilder.modCount;
        }

        private final Object writeReplace() {
            if (isReadOnly()) {
                return new SerializedCollection(this, 0);
            }
            throw new NotSerializableException("The list cannot be serialized while it is being built.");
        }

        public int getSize() {
            checkForComodification();
            return this.length;
        }

        public boolean isEmpty() {
            checkForComodification();
            return this.length == 0;
        }

        public Object get(int i) {
            checkForComodification();
            AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
            return this.backing[this.offset + i];
        }

        public Object set(int i, Object obj) {
            checkIsMutable();
            checkForComodification();
            AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
            Object[] objArr = this.backing;
            int i2 = this.offset;
            Object obj2 = objArr[i2 + i];
            objArr[i2 + i] = obj;
            return obj2;
        }

        public int indexOf(Object obj) {
            checkForComodification();
            for (int i = 0; i < this.length; i++) {
                if (Intrinsics.areEqual(this.backing[this.offset + i], obj)) {
                    return i;
                }
            }
            return -1;
        }

        public int lastIndexOf(Object obj) {
            checkForComodification();
            for (int i = this.length - 1; i >= 0; i--) {
                if (Intrinsics.areEqual(this.backing[this.offset + i], obj)) {
                    return i;
                }
            }
            return -1;
        }

        public Iterator iterator() {
            return listIterator(0);
        }

        public ListIterator listIterator() {
            return listIterator(0);
        }

        public ListIterator listIterator(int i) {
            checkForComodification();
            AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
            return new Itr(this, i);
        }

        public boolean add(Object obj) {
            checkIsMutable();
            checkForComodification();
            addAtInternal(this.offset + this.length, obj);
            return true;
        }

        public void add(int i, Object obj) {
            checkIsMutable();
            checkForComodification();
            AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
            addAtInternal(this.offset + i, obj);
        }

        public boolean addAll(Collection collection) {
            Intrinsics.checkNotNullParameter(collection, "elements");
            checkIsMutable();
            checkForComodification();
            int size = collection.size();
            addAllInternal(this.offset + this.length, collection, size);
            return size > 0;
        }

        public boolean addAll(int i, Collection collection) {
            Intrinsics.checkNotNullParameter(collection, "elements");
            checkIsMutable();
            checkForComodification();
            AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, this.length);
            int size = collection.size();
            addAllInternal(this.offset + i, collection, size);
            return size > 0;
        }

        public void clear() {
            checkIsMutable();
            checkForComodification();
            removeRangeInternal(this.offset, this.length);
        }

        public Object removeAt(int i) {
            checkIsMutable();
            checkForComodification();
            AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, this.length);
            return removeAtInternal(this.offset + i);
        }

        public boolean remove(Object obj) {
            checkIsMutable();
            checkForComodification();
            int indexOf = indexOf(obj);
            if (indexOf >= 0) {
                remove(indexOf);
            }
            return indexOf >= 0;
        }

        public boolean removeAll(Collection collection) {
            Intrinsics.checkNotNullParameter(collection, "elements");
            checkIsMutable();
            checkForComodification();
            return retainOrRemoveAllInternal(this.offset, this.length, collection, false) > 0;
        }

        public boolean retainAll(Collection collection) {
            Intrinsics.checkNotNullParameter(collection, "elements");
            checkIsMutable();
            checkForComodification();
            return retainOrRemoveAllInternal(this.offset, this.length, collection, true) > 0;
        }

        public List subList(int i, int i2) {
            AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(i, i2, this.length);
            return new BuilderSubList(this.backing, this.offset + i, i2 - i, this, this.root);
        }

        public Object[] toArray(Object[] objArr) {
            Intrinsics.checkNotNullParameter(objArr, "array");
            checkForComodification();
            int length2 = objArr.length;
            int i = this.length;
            if (length2 < i) {
                Object[] objArr2 = this.backing;
                int i2 = this.offset;
                Object[] copyOfRange = Arrays.copyOfRange(objArr2, i2, i + i2, objArr.getClass());
                Intrinsics.checkNotNullExpressionValue(copyOfRange, "copyOfRange(...)");
                return copyOfRange;
            }
            Object[] objArr3 = this.backing;
            int i3 = this.offset;
            ArraysKt.copyInto(objArr3, objArr, 0, i3, i + i3);
            return CollectionsKt.terminateCollectionToArray(this.length, objArr);
        }

        public Object[] toArray() {
            checkForComodification();
            Object[] objArr = this.backing;
            int i = this.offset;
            return ArraysKt.copyOfRange(objArr, i, this.length + i);
        }

        public boolean equals(Object obj) {
            checkForComodification();
            return obj == this || ((obj instanceof List) && contentEquals((List) obj));
        }

        public int hashCode() {
            checkForComodification();
            return ListBuilderKt.subarrayContentHashCode(this.backing, this.offset, this.length);
        }

        public String toString() {
            checkForComodification();
            return ListBuilderKt.subarrayContentToString(this.backing, this.offset, this.length, this);
        }

        private final void registerModification() {
            this.modCount++;
        }

        private final void checkForComodification() {
            if (this.root.modCount != this.modCount) {
                throw new ConcurrentModificationException();
            }
        }

        private final void checkIsMutable() {
            if (isReadOnly()) {
                throw new UnsupportedOperationException();
            }
        }

        private final boolean isReadOnly() {
            return this.root.isReadOnly;
        }

        private final boolean contentEquals(List list) {
            return ListBuilderKt.subarrayContentEquals(this.backing, this.offset, this.length, list);
        }

        private final void addAtInternal(int i, Object obj) {
            registerModification();
            BuilderSubList builderSubList = this.parent;
            if (builderSubList != null) {
                builderSubList.addAtInternal(i, obj);
            } else {
                this.root.addAtInternal(i, obj);
            }
            this.backing = this.root.backing;
            this.length++;
        }

        private final void addAllInternal(int i, Collection collection, int i2) {
            registerModification();
            BuilderSubList builderSubList = this.parent;
            if (builderSubList != null) {
                builderSubList.addAllInternal(i, collection, i2);
            } else {
                this.root.addAllInternal(i, collection, i2);
            }
            this.backing = this.root.backing;
            this.length += i2;
        }

        private final Object removeAtInternal(int i) {
            Object obj;
            registerModification();
            BuilderSubList builderSubList = this.parent;
            if (builderSubList != null) {
                obj = builderSubList.removeAtInternal(i);
            } else {
                obj = this.root.removeAtInternal(i);
            }
            this.length--;
            return obj;
        }

        private final void removeRangeInternal(int i, int i2) {
            if (i2 > 0) {
                registerModification();
            }
            BuilderSubList builderSubList = this.parent;
            if (builderSubList != null) {
                builderSubList.removeRangeInternal(i, i2);
            } else {
                this.root.removeRangeInternal(i, i2);
            }
            this.length -= i2;
        }

        private final int retainOrRemoveAllInternal(int i, int i2, Collection collection, boolean z) {
            int i3;
            BuilderSubList builderSubList = this.parent;
            if (builderSubList != null) {
                i3 = builderSubList.retainOrRemoveAllInternal(i, i2, collection, z);
            } else {
                i3 = this.root.retainOrRemoveAllInternal(i, i2, collection, z);
            }
            if (i3 > 0) {
                registerModification();
            }
            this.length -= i3;
            return i3;
        }

        private static final class Itr implements ListIterator, KMappedMarker {
            private int expectedModCount;
            private int index;
            private int lastIndex = -1;
            private final BuilderSubList list;

            public Itr(BuilderSubList builderSubList, int i) {
                Intrinsics.checkNotNullParameter(builderSubList, "list");
                this.list = builderSubList;
                this.index = i;
                this.expectedModCount = builderSubList.modCount;
            }

            public boolean hasPrevious() {
                return this.index > 0;
            }

            public boolean hasNext() {
                return this.index < this.list.length;
            }

            public int previousIndex() {
                return this.index - 1;
            }

            public int nextIndex() {
                return this.index;
            }

            public Object previous() {
                checkForComodification();
                int i = this.index;
                if (i > 0) {
                    int i2 = i - 1;
                    this.index = i2;
                    this.lastIndex = i2;
                    return this.list.backing[this.list.offset + this.lastIndex];
                }
                throw new NoSuchElementException();
            }

            public Object next() {
                checkForComodification();
                if (this.index < this.list.length) {
                    int i = this.index;
                    this.index = i + 1;
                    this.lastIndex = i;
                    return this.list.backing[this.list.offset + this.lastIndex];
                }
                throw new NoSuchElementException();
            }

            public void set(Object obj) {
                checkForComodification();
                int i = this.lastIndex;
                if (i != -1) {
                    this.list.set(i, obj);
                    return;
                }
                throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
            }

            public void add(Object obj) {
                checkForComodification();
                BuilderSubList builderSubList = this.list;
                int i = this.index;
                this.index = i + 1;
                builderSubList.add(i, obj);
                this.lastIndex = -1;
                this.expectedModCount = this.list.modCount;
            }

            public void remove() {
                checkForComodification();
                int i = this.lastIndex;
                if (i != -1) {
                    this.list.remove(i);
                    this.index = this.lastIndex;
                    this.lastIndex = -1;
                    this.expectedModCount = this.list.modCount;
                    return;
                }
                throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
            }

            private final void checkForComodification() {
                if (this.list.root.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }
}
