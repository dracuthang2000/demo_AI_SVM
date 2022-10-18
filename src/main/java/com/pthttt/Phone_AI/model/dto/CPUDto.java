package com.pthttt.Phone_AI.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pthttt.Phone_AI.model.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CPUDto {
    private Long id;
    @JsonProperty("cpu_name")
    private String cpuName;
    private Long point;
}