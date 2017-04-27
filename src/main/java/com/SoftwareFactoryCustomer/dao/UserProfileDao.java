package com.SoftwareFactoryCustomer.dao;


import com.SoftwareFactoryCustomer.model.UserProfile;

import java.util.List;

public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
