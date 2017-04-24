package com.SoftwareFactoryCustomer.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	CUSTOMER("CUSTOMER"),
	DBA("DBA"),
	MANAGER("MANAGER"),
	STAFF("STAFF");

	String userProfileType;

	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType(){
		return userProfileType;
	}

}
