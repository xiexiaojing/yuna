package com.brmayi.yuna.newspring;

@Component(value = "userService")
public class UserService {
    @Autowired
    private TestService testService;

    public void test() {
        System.out.println(testService.test());
    }
}