package com.application.userservice.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User Model class extending AbstractAuditable class for Audit fields
 */
@Entity
@Table(name="Users")
@Getter
@Setter
@IdClass(EntityId.class)
public class User extends AbstractAuditable<User, Long> implements Serializable
{
    private static final long serialVersionUID = 1075694382436239124L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;
    private String fullName;
    private String email;

    private static Long min = Long.valueOf(1), max = Long.MAX_VALUE;

    @OneToOne
    @JoinColumn(name = "userType_userTypeId", referencedColumnName = "userTypeId")
    private UserType userType;

    public User() {
        this.setId(getRandomValue());
        setAuditFields();
    }

    public User(String fullName, String email, UserType userType) {
        this.fullName = fullName;
        this.email = email;
        this.userType = userType;
        // setting this value since AbstractPersistable class needs it to insert
        this.setId(getRandomValue());
        setAuditFields();
    }

    Long getRandomValue() {
        return Double.valueOf(Math.random()*(max-min+1)+min).longValue();
    }

    void setAuditFields() {
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
    }

}
