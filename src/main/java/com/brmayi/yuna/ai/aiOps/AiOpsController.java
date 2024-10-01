package com.brmayi.yuna.ai.aiOps;

import com.brmayi.yuna.ai.aiOps.infrastructure.config.AiOpsAssitant;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/aiOps")
@Slf4j
public class AiOpsController {
    @Resource
    private AiOpsAssitant aiOpsAssitant;
    public static String userText = "";
    @GetMapping("/call")
    public String call(String text) {
        List<String> chats = Lists.newArrayList();
        chats.add("当前北京时间为" + LocalDateTime.now());
        chats.add(text);
        chats.add(userText);
        String answer = aiOpsAssitant.call(chats);
        userText = "";
        return answer;
    }
}
