package com.SoftwareFactoryCustomer.dao;


import com.SoftwareFactoryCustomer.model.User;

import java.util.List;

public interface UserDao {

    User findById(Long id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();

}
