package com.brmayi.yuna.ai.aiOps.constant;

import lombok.Getter;

@Getter
public enum CheckStatusEnum {
    UNFINISHED(0),
    FINISHED(1);

    private Integer code;

    CheckStatusEnum(Integer code) {
        this.code = code;
    }
}
