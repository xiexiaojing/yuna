package com.brmayi.yuna.ai.aiOps.infrastructure.config;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

import java.time.LocalDateTime;
import java.util.List;

public interface AiOpsAssitant {
    @SystemMessage("你是研发好帮手。你在中国北京。遇到以下表相关的你要调用执行更新SQL工具" +
            "CREATE TABLE `brmayi_schedule`  (\n" +
            "  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日程ID',\n" +
            "  `time` datetime NOT NULL,\n" +
            "  `event` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,\n" +
            "  `phones` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,\n" +
            "  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,\n" +
            "  `event_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件类型：关联brmayi_event_content表的type字段',\n" +
            "  PRIMARY KEY (`id`) USING BTREE\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;" +
            "CREATE TABLE `brmayi_event_content`  (\n" +
            "  `task_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',\n" +
            "  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '例如：上线准备、上线前通知',\n" +
            "  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '例如：基础上线准备',\n" +
            "  `context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '例如：SQL变更',\n" +
            "  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '例如：具体的SQL语句',\n" +
            "  `check_status` tinyint NOT NULL DEFAULT 0 COMMENT '值包含0:未完成,1:已完成',\n" +
            "  PRIMARY KEY (`task_id`) USING BTREE\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;")
    @UserMessage("{{text}}")
    String call( @V("text") List<String> text);

    @UserMessage("提取{{it}}")
    LocalDateTime getTime(List<String> text);
}
