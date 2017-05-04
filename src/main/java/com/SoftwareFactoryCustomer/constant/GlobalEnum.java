package com.SoftwareFactoryCustomer.constant;


public enum  GlobalEnum {
    webRoot ("http://www.sofac.kr");
    /*webRoot ("http://54.76.53.40");*/


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
