package com.brmayi.yuna.ai.aiOps.infrastructure.entity;

import cn.hutool.json.JSONObject;
import lombok.Data;

@Data
public class DingTalkEntity {
    private String msgtype = "text";
    private JSONObject text;
    private JSONObject at;
}
