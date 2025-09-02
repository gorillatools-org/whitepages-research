package com.facebook.soloader.recovery;

import com.facebook.soloader.SoSource;

public interface RecoveryStrategy {
    boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr);
}
