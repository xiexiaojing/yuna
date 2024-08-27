package com.brmayi.yuna.ai.rag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rag")
public class RagController {
    @Resource
    private RagService ragService;

    @GetMapping("/storeWxPage")
    public String storeWxPage(String text) {
        return "共存储" + ragService.store(text) + "个文本段到向量数据库";
    }

    @GetMapping("/call")
    public String call(String question) {
        return ragService.call(question);
    }
}
