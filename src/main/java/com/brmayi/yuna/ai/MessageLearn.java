package com.brmayi.yuna.ai;

import com.google.common.collect.Lists;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.zhipu.ZhipuAiStreamingChatModel;

import java.util.Date;
import java.util.List;

import static com.brmayi.yuna.ai.ZhipinConstants.API_KEY;

public class MessageLearn {

    public static void systemMessage() {
        ZhipuAiChatModel chatModel = ZhipuAiChatModel.builder()
                .apiKey(API_KEY)
                .maxRetries(0)
                .logRequests(true)
                .logResponses(true)
                .build();

        List<ChatMessage> histories = Lists.newArrayList();
        histories.add(SystemMessage.systemMessage("你下面的回答都要以表格形式给出"));
        histories.add(UserMessage.userMessage("中国古代四大美女以及与沉鱼落雁闭月羞花之间的对应关系"));

        // when
        Response<AiMessage> secondResponse = chatModel.generate(histories);
        System.out.println(secondResponse.content().text());
    }

    public static void histories() {
        ZhipuAiStreamingChatModel chatModel = ZhipuAiStreamingChatModel.builder()
                .apiKey(API_KEY)
                .logRequests(false)
                .logResponses(false)
                .build();

        List<ChatMessage> histories = Lists.newArrayList();
        histories.add(SystemMessage.systemMessage("请保持回答的格式统一"));
        histories.add(UserMessage.userMessage("西施的昵称是？"));
        histories.add(AiMessage.aiMessage("这个老老老奶奶的昵称是{沉鱼}"));
        histories.add(UserMessage.userMessage("王昭君的昵称是？"));
        histories.add(AiMessage.aiMessage("这个老老老奶奶的昵称是{落雁}"));
        histories.add(UserMessage.userMessage("杨玉环的昵称是？"));
        // when
       chatModel.generate(histories, new StreamingResponseHandler<AiMessage>(){
            @Override
            public void onNext(String s) {
                System.out.print(s);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    public static void aiServices() {
        ZhipuAiChatModel chatModel = ZhipuAiChatModel.builder()
                .apiKey(API_KEY)
                .maxRetries(0)
                .logRequests(false)
                .logResponses(false)
                .build();

        BeautyAssitant beautyAssitant = AiServices.create(BeautyAssitant.class, chatModel);
        System.out.println("结果1==============");
        System.out.println(beautyAssitant.all());
        System.out.println("结果2==============");
        System.out.println(beautyAssitant.get("貂蝉"));
        System.out.println("结果3==============");
        System.out.println(beautyAssitant.now("最好的开始学习时间是"+new Date()));
    }

    public static void aiServicesTool() {
        ZhipuAiChatModel chatModel = ZhipuAiChatModel.builder()
                .apiKey(API_KEY)
                .maxRetries(0)
                .logRequests(false)
                .logResponses(false)
                .build();

        BeautyAssitant beautyAssitant = AiServices.builder(BeautyAssitant.class)
                        .chatLanguageModel(chatModel)
                        .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                        .tools(new SimpleCalculator())
                        .build();
        System.out.println(beautyAssitant.cal());
    }

    public static void main(String[] args) {
        aiServicesTool();
    }
}
