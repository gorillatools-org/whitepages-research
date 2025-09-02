package androidx.fragment.app.strictmode;

public final /* synthetic */ class FragmentStrictMode$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Violation f$1;

    public /* synthetic */ FragmentStrictMode$$ExternalSyntheticLambda0(String str, Violation violation) {
        this.f$0 = str;
        this.f$1 = violation;
    }

    public final void run() {
        FragmentStrictMode.handlePolicyViolation$lambda$1(this.f$0, this.f$1);
    }
}
