package com.deepak.prodReadyFeatures.prod_ready_features.dto;

import com.mysql.cj.log.Log;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
}
