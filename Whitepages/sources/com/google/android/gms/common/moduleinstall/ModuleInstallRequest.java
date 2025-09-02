package com.google.android.gms.common.moduleinstall;

import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public final class ModuleInstallRequest {
    private final List zaa;
    private final InstallStatusListener zab;
    private final Executor zac;

    public static class Builder {
        private final List zaa = new ArrayList();
        private InstallStatusListener zab;
        private Executor zac;

        public Builder addApi(OptionalModuleApi optionalModuleApi) {
            this.zaa.add(optionalModuleApi);
            return this;
        }

        public ModuleInstallRequest build() {
            return new ModuleInstallRequest(this.zaa, this.zab, this.zac, true, (zac) null);
        }

        public Builder setListener(InstallStatusListener installStatusListener) {
            return setListener(installStatusListener, (Executor) null);
        }

        public Builder setListener(InstallStatusListener installStatusListener, Executor executor) {
            this.zab = installStatusListener;
            this.zac = executor;
            return this;
        }
    }

    /* synthetic */ ModuleInstallRequest(List list, InstallStatusListener installStatusListener, Executor executor, boolean z, zac zac2) {
        Preconditions.checkNotNull(list, "APIs must not be null.");
        Preconditions.checkArgument(!list.isEmpty(), "APIs must not be empty.");
        if (executor != null) {
            Preconditions.checkNotNull(installStatusListener, "Listener must not be null when listener executor is set.");
        }
        this.zaa = list;
        this.zab = installStatusListener;
        this.zac = executor;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<OptionalModuleApi> getApis() {
        return this.zaa;
    }

    public InstallStatusListener getListener() {
        return this.zab;
    }

    public Executor getListenerExecutor() {
        return this.zac;
    }
}
