package com.brmayi.yuna.ai.aiOps.infrastructure.config;

import com.brmayi.yuna.ai.aiOps.service.AiOpsTools;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

import static com.brmayi.yuna.ai.ZhipinConfig.chatModel;

@Configuration
@Order(999)
public class AiOpsConfig {
    @Resource
    private AiOpsTools aiOpsTools;

    @Bean
    public AiOpsAssitant aiOpsAssitant() {
        return AiServices.builder(AiOpsAssitant.class)
                .chatLanguageModel(chatModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(500))
                .tools(aiOpsTools)
                .build();
    }
}
