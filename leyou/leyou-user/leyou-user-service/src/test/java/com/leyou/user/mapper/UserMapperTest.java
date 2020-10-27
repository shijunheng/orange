package com.leyou.user.mapper;

import com.leyou.user.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQuery(){
        List<User> users = userMapper.selectAll();
        System.out.println("users"+users);
    }
}
