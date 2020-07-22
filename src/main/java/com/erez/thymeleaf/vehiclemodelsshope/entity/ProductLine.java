package com.erez.thymeleaf.vehiclemodelsshope.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="productline")
class ProductLine {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="productLine")
    private String productLine;

    @Column(name="textDescription")
     private String textDescription;

    @Column(name="htmlDescription")
    private String htmlDescription;

    @Column(name="image")
    private String image;
  


}
