package com.brmayi.yuna.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

import java.util.List;

public interface FaqAssitant {
    @SystemMessage("你是一个植物知识专家，说话言简意赅，一般不超过2句话。遇到链接将其包裹在 <img width='300px;'> 标签中，")
    @UserMessage("{{text}}")
    String call(@V("text") List<String> text);
}
