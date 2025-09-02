package com.salesforce.marketingcloud.sfmcsdk.modules;

import com.salesforce.marketingcloud.sfmcsdk.components.identity.ModuleIdentity;
import org.json.JSONObject;

public interface ModuleInterface {
    ModuleIdentity getModuleIdentity();

    JSONObject getState();
}
