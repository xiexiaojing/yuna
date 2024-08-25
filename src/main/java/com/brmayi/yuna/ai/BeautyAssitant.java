package com.brmayi.yuna.ai;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

import java.time.LocalDateTime;
import java.util.Map;

public interface BeautyAssitant {
    @UserMessage("中国古代四大美女以及与沉鱼落雁闭月羞花之间的对应关系")
    Map<String, String> all();

    @SystemMessage("你只返回中国古代四大美女的title，比如：name=西施，返回=沉鱼")
    @UserMessage("{{name}}")
    String get(@V("name")String name);


    @UserMessage("提取{{it}}")
    LocalDateTime now(String text);

    @UserMessage("中国古代四大美女的名字加起来几个字？")
    String cal();
}
