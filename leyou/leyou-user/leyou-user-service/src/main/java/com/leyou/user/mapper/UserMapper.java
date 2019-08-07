package com.leyou.user.mapper;


import com.leyou.user.pojo.User;
import org.xmlunit.util.Mapper;

public interface UserMapper extends Mapper<User,Long> {

    int selectCount(User record);
}