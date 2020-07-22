package com.erez.thymeleaf.vehiclemodelsshope.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="address")
public class Address implements Serializable {

    private static final long serialVersionUID = -5011548524561982389L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="addressId")
    private Integer id;


    @Column(name="city")
    @NotNull(message= "please  insert city name ")
    @Size(min=3, max=32, message="city name must be between 3 and 32 characters")
    private String city;


    @Column(name="streetEndNumber")
    @NotNull
    @NotBlank(message= "please  insert street name ")
    @Size(min=3, max=32, message="street name must be between 3 and 32 characters")
    private String street;


    @Column(name="state")
    @NotNull
    @NotBlank(message= "please  insert state name ")
    @Size(min=3, max=32, message="state name must be between 3 and 32 characters")
    private String state;

    @Column(name="zip")
    @NotNull
    @NotBlank(message= "please  insert zip number ")
    @Size(min=3, max=32, message="zip must be between 5 and 10 characters")
    private String zip;

    @Column(name ="country")
    @NotNull
    @NotBlank(message= "please  insert country name ")
    private String country;

    @Column(name ="territory")
    private String territory;

}
