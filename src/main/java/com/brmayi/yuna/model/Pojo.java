package com.brmayi.yuna.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Pojo {
    private String value;
    @NotBlank
    private String detail;
}
