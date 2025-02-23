package com.examportal.server.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private Set<User_Role> roleUsers;

    public Role(Long id, String name, Set<User_Role> roleUsers) {
        super();
        this.id = id;
        this.name = name;
        this.roleUsers = roleUsers;
    }

    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User_Role> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(Set<User_Role> roleUsers) {
        this.roleUsers = roleUsers;
    }

}
