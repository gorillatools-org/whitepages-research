package com.salesforce.marketingcloud.registration;

import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.g;
import java.util.Map;
import java.util.Set;

public interface RegistrationManager {
    public static final String a = g.a("RegistrationManager");
    public static final String b = "Android";
    public static final int c = 128;

    @MCKeep
    public interface Editor {
        Editor addTag(String str);

        Editor addTags(Iterable<String> iterable);

        Editor addTags(String... strArr);

        Editor clearAttribute(String str);

        Editor clearAttributes(Iterable<String> iterable);

        Editor clearAttributes(String... strArr);

        Editor clearTags();

        boolean commit();

        Editor removeTag(String str);

        Editor removeTags(Iterable<String> iterable);

        Editor removeTags(String... strArr);

        Editor setAttribute(String str, String str2);

        Editor setContactKey(String str);

        Editor setSignedString(String str);
    }

    @MCKeep
    public interface RegistrationEventListener {
        void onRegistrationReceived(Registration registration);
    }

    @MCKeep
    Editor edit();

    @MCKeep
    Map<String, String> getAttributes();

    @MCKeep
    String getContactKey();

    @MCKeep
    String getDeviceId();

    @MCKeep
    String getSignedString();

    @MCKeep
    String getSystemToken();

    @MCKeep
    Set<String> getTags();

    @MCKeep
    void registerForRegistrationEvents(RegistrationEventListener registrationEventListener);

    @MCKeep
    void unregisterForRegistrationEvents(RegistrationEventListener registrationEventListener);
}
