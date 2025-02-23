package com.examportal.server.Repositories;

import com.examportal.server.Entity.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<User_Role, Long> {

}
