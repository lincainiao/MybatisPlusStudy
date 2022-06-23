package com.lin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.mapper.UserMapper;
import com.lin.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlus02CrudApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

    }

    @Test
    void selectTest(){
        // 查询全部
        List<User> userList = userMapper.selectList(null);
        // 查询多个
        List<User> userList1 = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));

        // 条件查询
        Map<String, Object> map = new HashMap();
        // 查询条件，使用Map操作
        map.put("name","zhizhi");
        List<User> userList2 = userMapper.selectByMap(map);

    }

    @Test
    void insertTest(){
        User user = new User();
        user.setId(8L);
        user.setName("ytm");
        user.setAge(22);
        user.setEmail("1054794825@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user);
    }

    @Test
    void updateTest(){
        User user = new User();
        user.setId(8l);
        user.setAge(18);
        // 根据对象的id修改，传入的参数为一个对象
        int i = userMapper.updateById(user);
    }

    // 测试乐观锁成功案例
    @Test
    void testLock(){
        User user = userMapper.selectById(1l);
        user.setName(user.getName()+1+"");
        userMapper.updateById(user);
    }

    // 测试乐观锁失败案例
    @Test
    void testLock2(){
        User user1 = userMapper.selectById(1l);
        user1.setName(user1.getName()+1+"");
        // 模拟另外的线程插队操作
        User user2 = userMapper.selectById(1l);
        user2.setName(user2.getName()+2+"");
        userMapper.updateById(user1);   // 如果没有乐观锁，就会覆盖插队线程的值
    }

    // 分页插件
    @Test
    void pageTest(){
        // 当前页,页面大小
        Page<User> page = new Page<>(2,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }

    @Test
    void deleteTest(){
        // 通过id批量删除
        // userMapper.deleteBatchIds(Arrays.asList(9,10));
        // 通过map条件删除
        Map<String, Object> map = new HashMap<>();
        map.put("id",11l);
        map.put("name","ytm");
        userMapper.deleteByMap(map);
    }

    @Test
    void loginDeleteTest(){
        // 测试为delete，但实际删执行的mysql为更新操作:UPDATE user SET deleted=1 WHERE id=? AND deleted=0
        userMapper.deleteById(8l);
        // 再执行删除的时候，会拼接山deleted字段
        // userMapper.selectList(null); ==> SELECT id,name,age,email,version,create_time,update_time,deleted FROM user WHERE deleted=0
    }

}
