package io.branch.referral;

public enum Defines$IntentKeys {
    BranchData("branch_data"),
    ForceNewBranchSession("branch_force_new_session"),
    BranchLinkUsed("branch_used"),
    BranchURI("branch"),
    AutoDeepLinked("io.branch.sdk.auto_linked");
    
    private final String key;

    private Defines$IntentKeys(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }

    public String toString() {
        return this.key;
    }
}
