package com.google.android.gms.common.moduleinstall;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public interface ModuleInstallClient extends HasApiKey<Api.ApiOptions.NoOptions> {
    Task<ModuleAvailabilityResponse> areModulesAvailable(OptionalModuleApi... optionalModuleApiArr);

    Task<Void> deferredInstall(OptionalModuleApi... optionalModuleApiArr);

    Task<ModuleInstallIntentResponse> getInstallModulesIntent(OptionalModuleApi... optionalModuleApiArr);

    @ResultIgnorabilityUnspecified
    Task<ModuleInstallResponse> installModules(ModuleInstallRequest moduleInstallRequest);

    Task<Void> releaseModules(OptionalModuleApi... optionalModuleApiArr);

    @ResultIgnorabilityUnspecified
    Task<Boolean> unregisterListener(InstallStatusListener installStatusListener);
}
