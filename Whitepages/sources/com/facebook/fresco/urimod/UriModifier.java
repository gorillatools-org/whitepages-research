package com.facebook.fresco.urimod;

public final class UriModifier {
    public static final UriModifier INSTANCE = new UriModifier();

    /* renamed from: INSTANCE  reason: collision with other field name */
    public static UriModifierInterface f0INSTANCE = NopUriModifier.INSTANCE;

    private UriModifier() {
    }
}
