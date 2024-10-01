package com.brmayi.yuna.mybatis;

import cn.hutool.core.lang.ClassScanner;
import com.brmayi.yuna.mybatis.mapper.BookMapper;
import com.brmayi.yuna.mybatis.mapper.UserMapper;
import com.brmayi.yuna.model.User;
import com.brmayi.yuna.newspring.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.eclipse.jetty.util.security.Credential;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyBatisStartUp {
    public static void main(String[] args) throws Exception{
//        Environment environment = new Environment("test", new ManagedTransactionFactory(), new PooledDataSource("com.mysql.jdbc.Driver","1","1","1"));
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(UserMapper.class);
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
//
        UserMapper userMapper = (UserMapper) YunaSession.dealSql(UserMapper.class);

        User user = new User();
        user.setAge(1);
        user.setName("1");
        System.out.println("整个切面完成最终获取到的结果为：" +userMapper.insertUser(user));
        System.out.println("整个切面完成最终获取到的结果为：" +userMapper.selectList());
        System.out.println("整个切面完成最终获取到的结果为：" +userMapper.selectUser());

        BookMapper bookMapper = (BookMapper) YunaSession.dealSql(BookMapper.class);

        System.out.println("整个切面完成最终获取到的结果为：" +bookMapper.insertUser(user));
        System.out.println("整个切面完成最终获取到的结果为：" +bookMapper.selectList());
        System.out.println("整个切面完成最终获取到的结果为：" +bookMapper.selectUser());
        System.out.println(Credential.MD5.digest("122"));
    }

    public static class YunaApplicationContext {
        private static Map<String, Object> singletonObjects = Maps.newHashMap();
        private final Map<String, Object> earlySingletonObjects = Maps.newHashMap();
        private final Map<String, ObjectFactory<?>> singletonFactories = Maps.newHashMap();
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
                    object = earlySingletonObjects.get(beanName);
                    if (object == null) {
                        //如果是接口，先实例化三级缓存
                        if (BeanDefinition.getClazz().isInterface()) {
                            addSingletonFactory(beanName, new MapperFactory(BeanDefinition.getClazz()));

                            ObjectFactory singletonFactory = singletonFactories.get(beanName);

                            object = singletonFactory.getObject();
                            this.earlySingletonObjects.put(beanName, object);
                            this.singletonFactories.remove(beanName);
                        } else {//如果是对象，直接实例化二级缓存
                            this.earlySingletonObjects.put(beanName, BeanDefinition.getClazz().newInstance());
                        }

                        object = createBean(BeanDefinition.getClazz(), beanName, object);//实例化
                        singletonObjects.put(beanName, object);
                    }
                }
                return object;
            } else {
                return createBean(BeanDefinition.getClazz(), beanName, null);
            }
        }

        protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
            synchronized (this.singletonObjects) {
                if (!this.singletonObjects.containsKey(beanName)) {
                    this.singletonFactories.put(beanName, singletonFactory);
                    this.earlySingletonObjects.remove(beanName);
                }
            }
        }

        private Object createBean(Class clazz, String beanName, Object tempObject) throws Exception {
            if (tempObject == null) {
                tempObject = clazz.newInstance();//实例化
            }

            Object object = tempObject;
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
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setClazz(c);
            Scope scope = (Scope) configClass.getAnnotation(c);
            beanDefinition.setScope(scope == null ? "singleton" : scope.value());

            Component component = (Component) c.getAnnotation(Component.class);
            beanDefinitionMap.put(component.value(), beanDefinition);
        }
    }
}