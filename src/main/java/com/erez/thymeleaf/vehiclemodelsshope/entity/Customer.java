package com.erez.thymeleaf.vehiclemodelsshope.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.erez.thymeleaf.vehiclemodelsshope.entity.Order;
import lombok.*;

/*
customerNumber int PK
customerName varchar(50)
contactLastName varchar(50)
contactFirstName varchar(50)
phone varchar(50)
salesRepEmployeeNumber int
creditLimit decimal(10,2)
addressId int
 */

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="customer")
public class Customer {



    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="customerNumber")
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "sales_rep_employee_id")
    @SerializedName("id")
    @Expose
    private Integer id;


    @NotBlank(message= "place  insert company name ")
    @Size(min=3, max=32, message="First name must be between 3 and 32 characters")
    @Column(name="customerName")
    @SerializedName("customerName")
    @Expose
    private String customerName;

    @NotBlank(message= "pleace  insert last name ")
    @Size(min=3, max=32, message="First name must be between 3 and 32 characters")
    @Column(name="contactLastName")
    @SerializedName("contactLastName")
    @Expose
    private String contactLastName;

    @NotBlank(message= "pleace  insert first name ")
    @Size(min=3, max=32, message="First name must be between 3 and 32 characters")
    @Column(name="contact_first_Name")
    @SerializedName("contactFirstName")
    @Expose
    private String contactFirstName;

    @NotNull
    @NotBlank(message= "place  insert phone number ")
    //@Pattern(regexp = "\\d{10}", message="Please enter valid phone number")
    @Column(name="phone")
    private String phone;

    @NotBlank(message= "place  insert email ")
    @NotNull
    @Email(message= "place  insert valid email ")
    @Column(name="email")
    private String email;


    @Digits(integer=6, fraction=2 ,message= "place  insert valid number ")
    @Column(name ="creditLimit")
    private BigDecimal creditLimit;


    @ManyToOne( cascade= { CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST ,CascadeType.REFRESH} )
    @JoinColumn(name ="salesRepEmployeeNumber" )
    @NotNull
    @Valid
    private SalesRepresentative  salesRepresentative;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "addressId")
    @NotNull
    @Valid
    private Address address;


    @OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders ;

    public 	void addOrder(Order theOrder) {
        if (orders== null) {

            List<Order> orders = new ArrayList<Order>();
        }
        orders.add(theOrder);
        theOrder.setCustomer(this);
    }
}
