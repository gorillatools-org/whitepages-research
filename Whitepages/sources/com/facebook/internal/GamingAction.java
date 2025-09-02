package com.facebook.internal;

public enum GamingAction {
    ContextChoose("context_choose"),
    JoinTournament("join_tournament");
    
    private final String rawValue;

    private GamingAction(String str) {
        this.rawValue = str;
    }

    public final String getRawValue() {
        return this.rawValue;
    }
}
