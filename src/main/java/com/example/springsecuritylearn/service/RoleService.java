package com.example.springsecuritylearn.service;

import com.example.springsecuritylearn.domain.entity.Role;

import java.util.List;

public interface RoleService {
    Role getRole(long id);
    List<Role> getRoles();

    void createRole(Role role);

    void deleteRole(long id);
}
