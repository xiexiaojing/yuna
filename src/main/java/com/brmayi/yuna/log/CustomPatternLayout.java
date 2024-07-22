package com.brmayi.yuna.log;


import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class CustomPatternLayout extends PatternLayout {
    Pattern pattern = Pattern.compile("(moldde:\\d+)|(moldde:\\d+)|(moldde:\\d+)|(mole:\\d+)|(mobile:\\d+)|(bankCode:\\d+)|email:\\S+@");

    /**
     * 格式化日志信息
     *
     * @param event ILoggingEvent
     * @return 日志信息
     */
    @Override
    public String doLayout(ILoggingEvent event) {
        // 占位符填充
        String msg = super.doLayout(event);
        // 脱敏处理
        return this.buildSensitiveMsg(msg);
    }

    /**
     * 根据配置对日志进行脱敏
     *
     * @param msg 消息
     * @return 脱敏后的日志信息
     */
    public String buildSensitiveMsg(String msg) {
        return pattern.matcher(msg).replaceAll("****");
    }
}
