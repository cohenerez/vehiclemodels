package com.erez.thymeleaf.vehiclemodelsshope.entity;

import lombok.*;
import java.io.Serializable;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StateDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
}