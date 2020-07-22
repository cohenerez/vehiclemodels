package com.erez.thymeleaf.vehiclemodelsshope.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/*
officeCode int PK
phone varchar(50)
territory varchar(20)
addressId int
 */
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="office")
@JsonIgnoreProperties(value= {"salesRepresentatives"})
public class Office  implements Serializable {


    private static final long serialVersionUID = -5011548524561982389L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="officeCode")
    private int id;


    @Column(name="phone")
    private String phone;

    @OneToMany(mappedBy="office" ,fetch = FetchType.EAGER
       ,cascade= { CascadeType.DETACH, CascadeType.MERGE,
                  CascadeType.PERSIST , CascadeType.REFRESH
                 })
    @JsonIgnore
    private List<SalesRepresentative> salesRepresentatives ;

   @OneToOne(cascade= CascadeType.ALL)
   @JoinColumn(name = "addressId")
   private Address address;

    public 	void addSalesRepresentative(SalesRepresentative tempSalesRepresentative) {
        if (salesRepresentatives == null) {
            List<SalesRepresentative> salesRepresentatives = new ArrayList<SalesRepresentative>();
        }
        salesRepresentatives.add(tempSalesRepresentative);
        tempSalesRepresentative.setOffice(this);
    }





}
