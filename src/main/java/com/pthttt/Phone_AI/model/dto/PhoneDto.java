package com.pthttt.Phone_AI.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pthttt.Phone_AI.model.entity.Brand;
import com.pthttt.Phone_AI.model.entity.CPU;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private Integer ram;
    @NotBlank
    private Float widescreen;
    @NotBlank
    private Integer resolution1;
    @NotBlank
    private Integer resolution2;
    @NotBlank
    private Double price;
    @NotBlank
    private BrandDto brand;
    @NotBlank
    private CPUDto cpu;
    private Integer rom;
    private String label;
    @JsonProperty("binary_image")
    private String binaryImage;
    @NotBlank
    private String image;
    private String data;
}
