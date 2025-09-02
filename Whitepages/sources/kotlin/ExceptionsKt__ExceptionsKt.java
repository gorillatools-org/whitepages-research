package kotlin;

import java.io.PrintWriter;
import java.io.StringWriter;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;

abstract class ExceptionsKt__ExceptionsKt {
    public static String stackTraceToString(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        String stringWriter2 = stringWriter.toString();
        Intrinsics.checkNotNullExpressionValue(stringWriter2, "toString(...)");
        return stringWriter2;
    }

    public static void addSuppressed(Throwable th, Throwable th2) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        Intrinsics.checkNotNullParameter(th2, "exception");
        if (th != th2) {
            PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(th, th2);
        }
    }
}
