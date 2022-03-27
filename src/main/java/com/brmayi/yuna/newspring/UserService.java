package com.brmayi.yuna.newspring;

@Component(value = "userService")
public class UserService implements InitializingBean{
    @Autowired
    private TestService testService;

    private String password;

    @Override
    public void afterPropertiesSet() throws Exception {
        password = "从远程加密机获取秘钥";
        System.out.println(password);
    }

    public void test() {
        System.out.println(testService.test());
    }

}