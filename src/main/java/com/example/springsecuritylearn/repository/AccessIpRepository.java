package com.example.springsecuritylearn.repository;

import com.example.springsecuritylearn.domain.entity.AccessIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {
    AccessIp findByIpAddress(String IpAddress);
}