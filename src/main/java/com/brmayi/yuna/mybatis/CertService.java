package com.brmayi.yuna.mybatis;

import com.brmayi.yuna.model.Pojo;
import com.google.common.collect.Lists;

import java.util.List;

public class CertService {

    @Gray(biz = "cert")
    public List<Pojo> queryCert() {
        return Lists.newArrayList();
    }
}
