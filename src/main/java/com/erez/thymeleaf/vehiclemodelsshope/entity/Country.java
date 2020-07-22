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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "country")
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Country implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "sortname", nullable = false, length = 10)
    private String sortname;

    @Column(name = "region", nullable = false, length = 250)
    private String region;

    @Column(name = "phonecode", nullable = false, length = 10)
    private int phonecode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private Set<State> states = new HashSet<State>(0);

}
