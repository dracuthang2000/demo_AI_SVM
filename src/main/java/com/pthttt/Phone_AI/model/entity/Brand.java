package com.pthttt.Phone_AI.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BRAND")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "point")
    private Long point;

    @OneToMany(mappedBy = "brand")
    private Set<Phone> lstPhone = new HashSet<>();
}
