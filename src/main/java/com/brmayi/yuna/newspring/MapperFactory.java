package com.brmayi.yuna.newspring;

import com.brmayi.yuna.mybatis.YunaSession;

@Component
public class MapperFactory implements ObjectFactory {
    private Class clazz = null;

    public MapperFactory(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object getObject() {
        if (this.clazz.getName().endsWith("Mapper")) {
            //这是mybatis的代理类，具体可参考《mybatis的本质和原理》
            return YunaSession.dealSql(clazz);
        }
        return null;
    }
}