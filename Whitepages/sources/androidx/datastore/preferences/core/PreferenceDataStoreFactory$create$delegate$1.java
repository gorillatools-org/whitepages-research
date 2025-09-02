package androidx.datastore.preferences.core;

import java.io.File;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okio.Path;

final class PreferenceDataStoreFactory$create$delegate$1 extends Lambda implements Function0 {
    final /* synthetic */ Function0 $produceFile;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferenceDataStoreFactory$create$delegate$1(Function0 function0) {
        super(0);
        this.$produceFile = function0;
    }

    public final Path invoke() {
        File file = (File) this.$produceFile.invoke();
        if (Intrinsics.areEqual((Object) FilesKt.getExtension(file), (Object) "preferences_pb")) {
            Path.Companion companion = Path.Companion;
            File absoluteFile = file.getAbsoluteFile();
            Intrinsics.checkNotNullExpressionValue(absoluteFile, "file.absoluteFile");
            return Path.Companion.get$default(companion, absoluteFile, false, 1, (Object) null);
        }
        throw new IllegalStateException(("File extension for file: " + file + " does not match required extension for Preferences file: preferences_pb").toString());
    }
}
