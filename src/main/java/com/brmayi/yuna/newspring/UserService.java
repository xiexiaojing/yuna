package com.brmayi.yuna.newspring;

import com.brmayi.yuna.mapper.UserMapper;

@Component(value = "userService")
public class UserService implements InitializingBean{
    @Autowired
    private TestService testService;

    private String password;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        password = "从远程加密机获取秘钥";
        System.out.println(password);
    }

    public void test() {
        userMapper.selectList();
        System.out.println(testService.test());
    }
}