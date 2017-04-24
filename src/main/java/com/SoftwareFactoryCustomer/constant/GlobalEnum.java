package com.SoftwareFactoryCustomer.constant;


public enum  GlobalEnum {
    webRoot ("http://52.35.180.149");
    //webRoot ("http://localhost:8080");


    private String value;

    GlobalEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
