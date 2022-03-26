package com.brmayi.yuna.newspring;

import cn.hutool.core.lang.ClassScanner;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class YunaApplicationContext {
    private Class configClass;
    private static Map<String, Object> singletonMap = Maps.newHashMap();
    private static Map<String, BeanDefination> beanDefinationMap = Maps.newHashMap();

    public YunaApplicationContext(Class configClass) {
        this.configClass = configClass;
        //解析配置类
        ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String path = annotation.value();
        Set<Class<?>> classes = ClassScanner.scanPackage(path);//hutool工具扫描包

        classes.stream()
                .filter(c -> c.isAnnotationPresent(Component.class))
                .forEach(c -> {
                    BeanDefination beanDefination = new BeanDefination();
                    beanDefination.setClazz(c);
                    Scope scope = (Scope) configClass.getAnnotation(c);
                    beanDefination.setScope(scope == null ? "singleton" : scope.value());

                    Component component = c.getAnnotation(Component.class);
                    beanDefinationMap.put(component.value(), beanDefination);
                });
    }

    public Object getBean(String beanName) throws Exception {
        BeanDefination beanDefination = beanDefinationMap.get(beanName);
        if (beanDefination.getScope().equals("singleton")) {
            Object object = singletonMap.get(beanName);
            if (object == null) {
                object = createBean(beanDefination.getClazz());//实例化
                singletonMap.put(beanName, object);
            }
            return object;
        } else {
            return createBean(beanDefination.getClazz());
        }
    }

    private Object createBean(Class clazz) throws Exception {
        Object object = clazz.newInstance();//实例化
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(Autowired.class))
                .forEach(f -> {
                    try {
                        //用反射将实例注入到field变量中
                        f.setAccessible(true);
                        f.set(object, getBean(f.getName()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return object;
    }
}