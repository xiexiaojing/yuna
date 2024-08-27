package com.brmayi.yuna.ai.rag;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "webCrawler", url = "https://mp.weixin.qq.com") // 替换为实际服务的 URL
public interface WebCrawler {
    // 微信公众号文章，例如：https://mp.weixin.qq.com/s/82Y8rIBgArM4byTLfXEJWg
    @GetMapping("/s/{pageId}")
    String getPage(@PathVariable("pageId") String pageId);
}