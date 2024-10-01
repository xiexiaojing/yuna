package com.brmayi.yuna.ai.aiOps.infrastructure.client;


import com.brmayi.yuna.ai.aiOps.infrastructure.entity.DingTalkEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "dingtalkCall", url = "https://oapi.dingtalk.com") // 替换为实际服务的 URL
public interface DingtalkCallClient {
    @PostMapping("/robot/send")
    String send(@RequestParam("access_token") String accessToken,
                @RequestParam("timestamp") long timestamp,
                @RequestParam("sign") String sign,
                @RequestBody DingTalkEntity dingTalkEntity);
}