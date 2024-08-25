package com.brmayi.yuna.ai;

import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.zhipu.ZhipuAiImageModel;
import org.springframework.web.bind.annotation.RequestParam;

import static com.brmayi.yuna.ai.ZhipinConfig.API_KEY;

public class ZhipuBaseRun {
    public static String testImage(@RequestParam String name) {
        ImageModel imageModel = ZhipuAiImageModel.builder()
                .apiKey(API_KEY)
                .build();
        Response<Image> response = imageModel.generate(name);
        return response.content().url().toString();
    }

    public static void main(String[] args) {
        System.out.println(testImage("小猫"));
    }
}
