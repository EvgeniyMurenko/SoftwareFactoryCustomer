package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.model.UserProfile;

import java.util.List;

public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
