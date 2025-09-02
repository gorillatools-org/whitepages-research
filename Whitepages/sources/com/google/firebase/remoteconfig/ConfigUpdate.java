package com.google.firebase.remoteconfig;

import com.google.auto.value.AutoValue;
import java.util.Set;

@AutoValue
public abstract class ConfigUpdate {
    public abstract Set<String> getUpdatedKeys();

    public static ConfigUpdate create(Set<String> set) {
        return new AutoValue_ConfigUpdate(set);
    }
}
