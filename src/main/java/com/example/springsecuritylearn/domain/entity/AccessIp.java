package com.example.springsecuritylearn.domain.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ACCESS_IP")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class AccessIp implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "IP_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "IP_ADDRESS", nullable = false)
    private String ipAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccessIp accessIp = (AccessIp) o;
        return id != null && Objects.equals(id, accessIp.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
