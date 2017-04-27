package com.SoftwareFactoryCustomer.service;


import com.SoftwareFactoryCustomer.model.User;

import java.util.List;

public interface UserService {

	User findById(Long id);

	User findBySSO(String sso);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserBySSO(String sso);

	List<User> findAllUsers();

	boolean isUserSSOUnique(Long id, String sso);

	User createCustomerUser(String phone);

}