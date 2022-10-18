package com.pthttt.Phone_AI.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CPU")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_cpu")
    private String cpuName;
    @Column(name = "point")
    private Long point;
    @OneToMany(mappedBy = "brand")
    private Set<Phone> lstPhone = new HashSet<>();
}
