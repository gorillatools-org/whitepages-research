package com.facebook.react.turbomodule.core.interfaces;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface TurboModuleWithJSIBindings {
    @DoNotStrip
    BindingsInstallerHolder getBindingsInstaller();
}
