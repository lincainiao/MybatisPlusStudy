package com.lin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lin.mapper.UserMapper;
import com.lin.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.UsesSunMisc;

import java.net.SocketTimeoutException;
import java.util.List;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void test(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 1.查询name不为空，而且邮箱不为空的用户，年龄>=20
        // wrapper.isNotNull("name")
        //         .isNotNull("email")
        //         .ge("age",24);  //ge全称为Greater than：大于

        // 2.名字为zhizhi的用户
        // wrapper.eq("name","zhizhi");
        // System.out.println(userMapper.selectOne(wrapper));   // 查询一个数据

        // 3.between...and
        // wrapper.between("age",20,24);
        // for (User user : userMapper.selectList(wrapper)) {
        //     System.out.println(user);
        // }
        // System.out.println("total is "+userMapper.selectCount(wrapper));    //查询结果的数量

        // 4.模糊查询
        // like：%zhi%；likeLeft：%zhi；likeRight：zhi%
        // wrapper.notLike("name","zhi")
        //         .likeLeft("email","@qq.com");

        // 5.子查询
        // wrapper.inSql("id","select id from user where id >= 5");

        // 6.排序
        // wrapper.orderBy(true,true,"id");
        wrapper.orderByAsc("id");
    }

    @Test
    void testAll(){
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
