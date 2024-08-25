package com.brmayi.yuna.ai;

import dev.langchain4j.agent.tool.Tool;

public class SimpleCalculator {

    @Tool("加起来几个字？")
    public String add(double a, double b) {
        return "啦啦啦，计算结果出来啦。是："+(a + b);
    }

    @Tool("减起来几个字？")
    public String subtract(double a, double b) {
        return "啦啦啦，计算结果出来啦。是："+(a - b);
    }

    @Tool("乘起来几个字？")
    public String multiply(double a, double b) {
        return "啦啦啦，计算结果出来啦。是："+(a * b);
    }

    @Tool("除起来几个字？")
    public String divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return "啦啦啦，计算结果出来啦。是："+(a / b);
    }
}
