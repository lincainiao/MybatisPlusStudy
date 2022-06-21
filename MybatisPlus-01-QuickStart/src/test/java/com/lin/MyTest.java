package com.lin;

import com.lin.mapper.UserMapper;
import com.lin.pojo.User;
import jdk.management.resource.internal.inst.UnixAsynchronousSocketChannelImplRMHooks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test1(){
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
