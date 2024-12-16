package com.deepak.prodReadyFeatures.prod_ready_features.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    private Long id;

    private String name;

    private String email;


    private Integer age;


    private String role; //ADMIN, USER

    private Double salary;

    private LocalDate dateOfJoining;

    private Boolean isActive;
}

