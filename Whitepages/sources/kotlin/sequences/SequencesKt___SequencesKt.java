package kotlin.sequences;

import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

abstract class SequencesKt___SequencesKt extends SequencesKt___SequencesJvmKt {
    public static boolean contains(Sequence sequence, Object obj) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return indexOf(sequence, obj) >= 0;
    }

    public static Object firstOrNull(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator it = sequence.iterator();
        if (!it.hasNext()) {
            return null;
        }
        return it.next();
    }

    public static final int indexOf(Sequence sequence, Object obj) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        int i = 0;
        for (Object next : sequence) {
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (Intrinsics.areEqual(obj, next)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static Sequence drop(Sequence sequence, int i) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        if (i < 0) {
            throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
        } else if (i == 0) {
            return sequence;
        } else {
            if (sequence instanceof DropTakeSequence) {
                return ((DropTakeSequence) sequence).drop(i);
            }
            return new DropSequence(sequence, i);
        }
    }

    public static Sequence dropWhile(Sequence sequence, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        return new DropWhileSequence(sequence, function1);
    }

    public static Sequence filter(Sequence sequence, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        return new FilteringSequence(sequence, true, function1);
    }

    public static final Sequence filterNot(Sequence sequence, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        return new FilteringSequence(sequence, false, function1);
    }

    public static final Sequence filterNotNull(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Sequence filterNot = filterNot(sequence, SequencesKt___SequencesKt$filterNotNull$1.INSTANCE);
        Intrinsics.checkNotNull(filterNot, "null cannot be cast to non-null type kotlin.sequences.Sequence<T of kotlin.sequences.SequencesKt___SequencesKt.filterNotNull>");
        return filterNot;
    }

    public static Sequence takeWhile(Sequence sequence, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        return new TakeWhileSequence(sequence, function1);
    }

    public static Collection toCollection(Sequence sequence, Collection collection) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(collection, FirebaseAnalytics.Param.DESTINATION);
        for (Object add : sequence) {
            collection.add(add);
        }
        return collection;
    }

    public static List toList(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator it = sequence.iterator();
        if (!it.hasNext()) {
            return CollectionsKt.emptyList();
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return CollectionsKt.listOf(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static Sequence map(Sequence sequence, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(function1, ViewProps.TRANSFORM);
        return new TransformingSequence(sequence, function1);
    }

    public static Sequence mapNotNull(Sequence sequence, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(function1, ViewProps.TRANSFORM);
        return filterNotNull(new TransformingSequence(sequence, function1));
    }

    public static int count(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Iterator it = sequence.iterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
            if (i < 0) {
                CollectionsKt.throwCountOverflow();
            }
        }
        return i;
    }

    public static final Appendable joinTo(Sequence sequence, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(appendable, "buffer");
        Intrinsics.checkNotNullParameter(charSequence, "separator");
        Intrinsics.checkNotNullParameter(charSequence2, "prefix");
        Intrinsics.checkNotNullParameter(charSequence3, "postfix");
        Intrinsics.checkNotNullParameter(charSequence4, "truncated");
        appendable.append(charSequence2);
        int i2 = 0;
        for (Object next : sequence) {
            i2++;
            if (i2 > 1) {
                appendable.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            StringsKt.appendElement(appendable, next, function1);
        }
        if (i >= 0 && i2 > i) {
            appendable.append(charSequence4);
        }
        appendable.append(charSequence3);
        return appendable;
    }

    public static /* synthetic */ String joinToString$default(Sequence sequence, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = "";
        CharSequence charSequence6 = (i2 & 2) != 0 ? charSequence5 : charSequence2;
        if ((i2 & 4) == 0) {
            charSequence5 = charSequence3;
        }
        if ((i2 & 8) != 0) {
            i = -1;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        return joinToString(sequence, charSequence, charSequence6, charSequence5, i3, charSequence7, function1);
    }

    public static final String joinToString(Sequence sequence, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        Intrinsics.checkNotNullParameter(charSequence, "separator");
        Intrinsics.checkNotNullParameter(charSequence2, "prefix");
        Intrinsics.checkNotNullParameter(charSequence3, "postfix");
        Intrinsics.checkNotNullParameter(charSequence4, "truncated");
        String sb = ((StringBuilder) joinTo(sequence, new StringBuilder(), charSequence, charSequence2, charSequence3, i, charSequence4, function1)).toString();
        Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
        return sb;
    }

    public static Iterable asIterable(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return new SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(sequence);
    }
}
