package com.SoftwareFactoryCustomer.constant;


public enum  ProjectEnum {
    projectNameEstimate("Estimate" , "#$ESTIMATE"),
    projectNameNormal("Normal" , "#$GENERAL");


    private String value;
    private String dbValue;

    ProjectEnum(final String value , String dbValue) {
        this.value = value;
        this.dbValue = dbValue;
    }

    public String getValue() {
        return value;
    }

    public String getDbValue(){
        return dbValue;
    }

    @Override
    public String toString() {
        return this.getValue();
    }


}
