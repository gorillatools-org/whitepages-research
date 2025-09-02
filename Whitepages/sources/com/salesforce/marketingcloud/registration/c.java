package com.salesforce.marketingcloud.registration;

import com.salesforce.marketingcloud.registration.RegistrationManager;
import java.util.Map;

public interface c {
    RegistrationManager.Editor a(String str, String str2, boolean z);

    RegistrationManager.Editor a(String str, Map<String, String> map, boolean z);

    RegistrationManager.Editor a(String str, boolean z);

    RegistrationManager.Editor a(Map<String, String> map, boolean z);
}
