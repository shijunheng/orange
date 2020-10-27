package com.leyou.user.mapper;


import com.leyou.user.pojo.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<User> {

    int selectCount(User record);

    int insertSelective(User user);

    User selectOne(User record);

    List<User> selectAll();
}