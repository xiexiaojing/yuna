package com.brmayi.yuna.newspring;

import cn.hutool.core.lang.ClassScanner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class YunaApplicationContext {
    private static Map<String, Object> singletonObjects = Maps.newHashMap();
    private static Map<String, BeanDefinition> beanDefinitionMap = Maps.newHashMap();
    private static List<BeanPostProcessor> postProcessorList = Lists.newArrayList();

    public YunaApplicationContext(Class configClass) {
        //解析配置类
        ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String path = annotation.value();
        Set<Class<?>> classes = ClassScanner.scanPackage(path);//hutool工具扫描包

        classes.stream()
                .filter(c -> c.isAnnotationPresent(Component.class))
                .forEach(c -> {
                    loadBeanDefinition(c, configClass);//加载Bean定义

                    //解析后置处理器
                    if (BeanPostProcessor.class.isAssignableFrom(c)) {
                        try {
                            BeanPostProcessor beanPostProcessor = (BeanPostProcessor) c.newInstance();
                            postProcessorList.add(beanPostProcessor);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public Object getBean(String beanName) throws Exception {
        BeanDefinition BeanDefinition = beanDefinitionMap.get(beanName);
        if (BeanDefinition.getScope().equals("singleton")) {
            Object object = singletonObjects.get(beanName);
            if (object == null) {
                object = createBean(BeanDefinition.getClazz(), beanName);//实例化
                singletonObjects.put(beanName, object);
            }
            return object;
        } else {
            return createBean(BeanDefinition.getClazz(), beanName);
        }
    }

    private Object createBean(Class clazz, String beanName) throws Exception {
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

        postProcessorList.stream().forEach(p -> p.postProcessBeforeInitialization(object, beanName));

        if (object instanceof InitializingBean) {
            ((InitializingBean) object).afterPropertiesSet();
        }

        postProcessorList.stream().forEach(p -> p.postProcessAfterInitialization(object, beanName));
        return object;
    }

    private void loadBeanDefinition(Class c, Class configClass) {
        BeanDefinition BeanDefinition = new BeanDefinition();
        BeanDefinition.setClazz(c);
        Scope scope = (Scope) configClass.getAnnotation(c);
        BeanDefinition.setScope(scope == null ? "singleton" : scope.value());

        Component component = (Component) c.getAnnotation(Component.class);
        beanDefinitionMap.put(component.value(), BeanDefinition);
    }
}