package com.example.springsecuritylearn.security.init;

import com.example.springsecuritylearn.service.RoleHierarchyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;

@Component
public class SecurityInitializer implements ApplicationRunner {
    private RoleHierarchyService roleHierarchyService;
    private RoleHierarchyImpl roleHierarchy;

    @Autowired
    private void setSecurityInitializer(RoleHierarchyService roleHierarchyService, RoleHierarchyImpl roleHierarchy) {
        this.roleHierarchyService = roleHierarchyService;
        this.roleHierarchy = roleHierarchy;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String allHierarchy = roleHierarchyService.findAllHierarchy();
        roleHierarchy.setHierarchy(allHierarchy);
    }
}
