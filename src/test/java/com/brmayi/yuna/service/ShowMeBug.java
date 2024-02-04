package com.brmayi.yuna.service;

// 可以引⼊的库和版本相关请参考 “环境说明”
// 请勿更改 `ShowMeBug` 类名以防执⾏失败

import java.util.function.IntBinaryOperator;

// 你需要修改以下代码
public enum ShowMeBug {
    /**
     * 加
     */
    ADD("+", null),
    /**
     * 减
     */
    SUB("-", null),
    /**
     * 乘
     */
    MUL("*", null),
    /**
     * 除
     */
    DIV("/", null);

    private final String symbol;
    private final IntBinaryOperator op;

    ShowMeBug(String symbol, IntBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    public int apply(int a, int b) {
        switch (symbol) {
            case "+" : return a+b;
            case "-" : return a-b;
            case "*" : return a*b;
        }
        return a / b;
    }
}
