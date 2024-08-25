package com.brmayi.yuna.ai;

import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import dev.langchain4j.model.zhipu.ZhipuAiClient;
import dev.langchain4j.model.zhipu.ZhipuAiImageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZhipinConfig {
    public static final String API_KEY = "7382f276f1b272e58832f56dedadea0a.6b3ut9pFAbrCqq98";
    public static final ZhipuAiChatModel chatModel = ZhipuAiChatModel.builder()
            .apiKey(API_KEY)
            .maxRetries(0)
            .logRequests(false)
            .logResponses(false)
            .build();
    public static final ImageModel imageModel = ZhipuAiImageModel.builder()
            .apiKey(API_KEY)
            .build();

    @Bean
    public ZhipuAiClient zhipuAiClient() {
        return ZhipuAiClient.builder().apiKey(API_KEY)
                .build();
    }
}
