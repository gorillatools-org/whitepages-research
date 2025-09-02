package com.facebook.appevents;

public enum ParameterClassification {
    CustomData("custom_data"),
    OperationalData("operational_data"),
    CustomAndOperationalData("custom_and_operational_data");
    
    private final String value;

    private ParameterClassification(String str) {
        this.value = str;
    }
}
