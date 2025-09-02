package androidx.datastore.core.okio;

import kotlin.coroutines.Continuation;
import okio.BufferedSink;
import okio.BufferedSource;

public interface OkioSerializer {
    Object getDefaultValue();

    Object readFrom(BufferedSource bufferedSource, Continuation continuation);

    Object writeTo(Object obj, BufferedSink bufferedSink, Continuation continuation);
}
