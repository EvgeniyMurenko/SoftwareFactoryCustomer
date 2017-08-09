package com.SoftwareFactoryCustomer.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


import com.SoftwareFactoryCustomer.dao.UserDao;
import com.SoftwareFactoryCustomer.model.User;
import com.SoftwareFactoryCustomer.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(Long id) {
        return dao.findById(id);
    }

    public User findBySSO(String sso) {
        User user = dao.findBySSO(sso);
        return user;
    }

    public void saveUser(User user) {
      /*  user.setPassword(passwordEncoder.encode(user.getPassword()));*/
        dao.save(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null) {
            entity.setSsoId(user.getSsoId());
            if (!user.getPassword().equals(entity.getPassword())) {
       /*         entity.setPassword(passwordEncoder.encode(user.getPassword()));*/
                entity.setPassword(user.getPassword());
            }
            entity.setAvatarImage(user.getAvatarImage());
            entity.setUserProfiles(user.getUserProfiles());
        }
    }


    public void deleteUserBySSO(String sso) {
        dao.deleteBySSO(sso);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public boolean isUserSSOUnique(Long id, String sso) {
        User user = findBySSO(sso);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

    public User createCustomerUser(String phone) {

        User user = new User();
        user.setSsoId(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        user.setAvatarImage("");

        UserProfile userProfile = new UserProfile();
        userProfile.setId(1);
        userProfile.setType("CUSTOMER");

        Set<UserProfile> userProfiles = new HashSet<>();
        userProfiles.add(userProfile);

        user.setUserProfiles(userProfiles);

        dao.save(user);

        String ssoId = getCustomerId(user.getId().toString());

        user.setPassword(phone);
        user.setSsoId(ssoId);



        dao.save(user);

        return user;
    }


    private String getCustomerId(String id) {
        if (id.length() <= 4) {
            String zero = "";
            for (int i = 0; i < 4 - id.length(); i++) {
                zero = zero + "0";
                System.out.println("1");
            }
            return zero + id;
        } else {
            return id;
        }

    }
}




