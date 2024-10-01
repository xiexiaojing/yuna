package com.brmayi.yuna.ai.aiOps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AiOpsShowController {

    @GetMapping("/aiOps/show")
    public String faq() {
        return "AIOps";
    }
}
