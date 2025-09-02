package com.google.firebase.sessions.dagger;

public interface Lazy<T> {
    T get();
}
