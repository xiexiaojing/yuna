package com.brmayi.yuna.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Pojo {
    private String value;
    @NotBlank
    private String detail;
}
