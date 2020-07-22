package com.erez.thymeleaf.vehiclemodelsshope.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="productCode")
    private String productCode;

    @Column(name="productName")
    private String productName;

    @Column(name="productScale")
    private  String productScale;

    @Column(name="productVendor")
    private  String productVendor;

    @Column(name="productDescription")
    private  String productDescription;

    @Column(name="quantityInStock")
    private  int quantityInStock;

    @Column(name="buyPrice")
    private float buyPrice;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "productLine")
    private ProductLine productLine;


}
