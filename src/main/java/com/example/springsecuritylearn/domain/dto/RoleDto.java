package com.example.springsecuritylearn.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto{
    private String id;
    private String roleName;
    private String roleDesc;
}
