package com.brmayi.yuna.mybatis;

import com.brmayi.yuna.model.Pojo;

import java.util.ArrayList;
import java.util.List;

public class CertService {

    @Gray(biz = "cert")
    public List<Pojo> queryCert() {
        return new ArrayList<>();
    }
}
