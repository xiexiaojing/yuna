package com.brmayi.yuna.newspring;

@Component("testService")
public class TestService {
    @Autowired
    private UserService userService;

    public String test() {
        return "我是尤娜";
    }
}
