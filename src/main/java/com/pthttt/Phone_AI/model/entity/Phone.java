package com.pthttt.Phone_AI.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PHONE")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "ram")
    private Integer ram;
    @Column(name = "widescreen")
    private Float widescreen;
    @Column(name = "resolution1")
    private Integer resolution1;
    @Column(name = "resolution2")
    private Integer resolution2;
    @Column(name = "price")
    private Double price;
    @Column(name = "label")
    private String label;
    @Column(name = "image")
    private String image;
    @Column(name = "ROM")
    private Integer rom;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "id_brand")
    private Brand brand;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "id_cpu")
    private CPU cpu;
}
