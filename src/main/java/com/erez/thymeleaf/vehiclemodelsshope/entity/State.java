package com.erez.thymeleaf.vehiclemodelsshope.entity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "state")
public class State implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId", nullable = false)
    private Country country;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
    private Set<City> cities = new HashSet<City>(0);







}
