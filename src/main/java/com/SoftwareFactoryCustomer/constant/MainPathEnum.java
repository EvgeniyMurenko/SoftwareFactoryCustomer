package com.SoftwareFactoryCustomer.constant;

/**
 * Created by adm on 2/21/2017.
 */
public enum MainPathEnum {
    mainPath("opt/tomcat/webapps/softwarefactory");

    private String value;

    MainPathEnum(final String value) {
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
