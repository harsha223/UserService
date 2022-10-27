package com.application.userservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="UserTypes")
@Getter
@Setter
public class UserType
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userTypeId")
    private Long userTypeId;
    private String type;

    public UserType() {

    }

    public UserType (String type) {
        this.type = type;
    }

}
