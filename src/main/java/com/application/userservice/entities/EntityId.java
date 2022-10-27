package com.application.userservice.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class EntityId implements Serializable
{
    private static final long serialVersionUID = 5745297203625178254L;

    private Long userId;
}
