package com.brmayi.yuna.ai;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.zhipu.ZhipuAiClient;
import dev.langchain4j.model.zhipu.chat.*;
import dev.langchain4j.model.zhipu.embedding.EmbeddingRequest;
import dev.langchain4j.model.zhipu.embedding.EmbeddingResponse;
import dev.langchain4j.model.zhipu.image.ImageRequest;
import dev.langchain4j.model.zhipu.image.ImageResponse;
import dev.langchain4j.service.AiServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.brmayi.yuna.ai.ZhipinConfig.chatModel;

@RestController
@RequestMapping("/ai")
@Slf4j
public class AiController {
    @Resource
    private ZhipuAiClient zhipuAiClient;

    @GetMapping("/call")
    public String baseMessage(String text) {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(UserMessage.from(text)).build();
        ChatCompletionResponse completionResponse = zhipuAiClient.chatCompletion(request);
        List<ChatCompletionChoice> chatCompletionChoices = completionResponse.getChoices();
        return chatCompletionChoices.get(0).getMessage().getContent();
    }

    @GetMapping("/prompt")
    public String prompt(String name, String text) {
        // 创建一个提示词模板
        PromptTemplate template = PromptTemplate.from("你不叫人工智能助手，你的名字是{{name}}");
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", name);

        // 使用模板和参数生成提示词
        Prompt prompt = template.apply(map);

        // 输出生成的提示词
        String systemText = prompt.text();

        Message[] messages = new Message[2];
        messages[0] = SystemMessage.from(systemText);
        messages[1] = UserMessage.from(text);

        // 设置
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(messages).build();

        ChatCompletionResponse completionResponse = zhipuAiClient.chatCompletion(request);

        List<ChatCompletionChoice> chatCompletionChoices = completionResponse.getChoices();
        return chatCompletionChoices.get(0).getMessage().getContent();
    }

    @GetMapping("/image")
    public String image(String text) {
        ImageRequest request = ImageRequest.builder()
                .model("cogview-3")
                .prompt(text).build();
        ImageResponse imageResponse = zhipuAiClient.imagesGeneration(request);
        return "<img src='" + imageResponse.getData().get(0).getUrl() + "'/>";
    }


    @GetMapping("/embed")
    public int embed(String text) {
        EmbeddingRequest request = EmbeddingRequest.builder()
                .input(text).build();
        EmbeddingResponse response = zhipuAiClient.embedAll(request);
        return response.getData().get(0).getEmbedding().size();
    }

    private static List<String> chatHistories = Lists.newArrayList();

    @GetMapping("/plantFaq")
    public String plantFaq(String text) {
        FaqAssitant faqAssitant = AiServices.builder(FaqAssitant.class)
                .chatLanguageModel(chatModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(50))
                .tools(new AiTools())
                .build();
        chatHistories.add(text);
        String answer = faqAssitant.call(chatHistories);
        chatHistories.add(answer);
        return answer;
    }
}
