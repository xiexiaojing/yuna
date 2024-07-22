package com.brmayi.yuna.log;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

public class MaskingPatternLayoutEncoder extends PatternLayoutEncoder {

    /**
     * 使用自定义 MyLogbackPatternLayout 格式化输出
     */
    @Override
    public void start() {
        CustomPatternLayout patternLayout = new CustomPatternLayout();
        patternLayout.setContext(context);
        patternLayout.setPattern(this.getPattern());
        patternLayout.setOutputPatternAsHeader(outputPatternAsHeader);
        patternLayout.start();
        this.layout = patternLayout;
        started = true;
    }
}