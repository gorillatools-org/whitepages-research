package com.google.android.datatransport.runtime;

import java.util.Set;

public interface EncodedDestination extends Destination {
    Set getSupportedEncodings();
}
