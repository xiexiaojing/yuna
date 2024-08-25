package com.brmayi.yuna.ai;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;

import java.net.URI;

import static com.brmayi.yuna.ai.ZhipinConfig.imageModel;

public class AiTools {

    @Tool("生成图片、图像、图画、图、照片")
    public URI image(String name) {
        Response<Image> response = imageModel.generate(name);
        return response.content().url();
    }
}
