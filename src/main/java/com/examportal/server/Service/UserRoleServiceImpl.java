package com.examportal.server.Service;

import com.examportal.server.Entity.User_Role;
import com.examportal.server.Repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRespository;

    @Override
    public void save(User_Role user_role) {
        try {
            userRoleRespository.save(user_role);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
