package com.brmayi.yuna.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Pojo {
    private int id;
    private String value;
    @NotBlank
    private String detail;
}
