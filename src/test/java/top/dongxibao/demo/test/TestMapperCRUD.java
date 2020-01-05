package top.dongxibao.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.dongxibao.demo.entity.OtherInfo;
import top.dongxibao.demo.entity.User;
import top.dongxibao.demo.mapper.UserMapper;

/**
 * @ClassName TestMapperCRUD
 * @description 测试Mapper CRUD 接口
 * @author Dongxibao
 * @date 2020/1/4
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMapperCRUD {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setAge(22 + i).setName("abc" + i).setEmail("abc" + i + "@163.com").setVersion(1);
            userMapper.insert(user);
        }
        /*
        Preparing: INSERT INTO user ( ID, NAME, AGE, EMAIL, CREATE_TIME ) VALUES ( ?, ?, ?, ?, ? )
        Parameters: 1213426563115225090(Long), abc(String), 22(Integer), abc@163.com(String), 2020-01-04 19:46:51.682(Timestamp)
         */
    }
    @Test
    public void testDelete() {
        // 根据 entity 条件，删除记录
        userMapper.delete(new QueryWrapper<User>()
                .lambda().gt(User::getAge,100));
        /*
        Preparing: DELETE FROM user WHERE (AGE > ?)
        Parameters: 110(Integer)
         */

        // 删除（根据ID 批量删除）
        //userMapper.deleteBatchIds(Arrays.asList(1213428972445007874L,1213428972436619266L,1213428972428230658L));
        /*
        Preparing: DELETE FROM user WHERE ID IN ( ? , ? , ? )
        Parameters: 1213428972445007874(Long), 1213428972436619266(Long), 1213428972428230658(Long)
         */

        // 根据 ID 删除
        //userMapper.deleteById(1213428972411453441L);
        /*
        Preparing: DELETE FROM user WHERE ID=?
        Parameters: 1213428972411453441(Long)
         */

        // 根据 columnMap 条件，删除记录
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "abc84");
//        map.put("email", "abc84@163.com");
//        userMapper.deleteByMap(map);
        /*
        Preparing: DELETE FROM user WHERE name = ? AND email = ?
        Parameters: abc84(String), abc84@163.com(String)
         */
    }
    @Test
    public void testUpdate() {
        // 根据 ID 修改
//        userMapper.updateById(new User()
//                                .setAge(999)
//                                .setName("abc999")
//                                .setEmail("abc999@163.com")
//                                .setId(1213435727317135361L));
        /*
        Preparing: UPDATE user SET NAME=?, AGE=?, EMAIL=? WHERE ID=?
        Parameters: abc999(String), 999(Integer), abc999@163.com(String), 1213435727317135361(Long)
         */

        // 根据 whereEntity 条件，更新记录
        userMapper.update(new User().setName("defg"),Wrappers.<User>lambdaUpdate()
                .set(User::getEmail, "1234@126.com")
                .eq(User::getId, 1213435727082254338L)
                .eq(User::getAge, 99));
        /*
        Preparing: UPDATE user SET NAME=?, EMAIL=? WHERE (ID = ? AND AGE = ?)
        Parameters: defg(String), 1234@126.com(String), 1213435727082254338(Long), 99(Integer)
         */
    }

    @Test
    public void testSelect() {
        // 根据 ID 查询
//        User user = userMapper.selectById(1213435727082254338L);
//        System.out.println(user);
        /*
        Preparing: SELECT * FROM user WHERE ID=?
        Parameters: 1213435727082254338(Long)
         */

        // 根据 entity 条件，查询一条记录
//        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 1213435727082254338L));
//        System.out.println(user);
        /*
        Preparing: SELECT * FROM user WHERE (ID = ?)
        Parameters: 1213435727082254338(Long)
        // 执行结果不唯一时报错：TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 100
         */

        // 查询（根据ID 批量查询）
//        List<User> users = userMapper.selectBatchIds(Arrays.asList(1213435727065477121L, 1213435727082254338L,
//                1213435727090642945L));
//        users.forEach(System.out::println);
        /*
        Preparing: SELECT * FROM user WHERE ID IN ( ? , ? , ? )
        Parameters: 1213435727065477121(Long), 1213435727082254338(Long), 1213435727090642945(Long)
         */

        // 根据 entity 条件，查询全部记录
//        List<User> users = userMapper.selectList(new QueryWrapper<User>()
//                        .select( "id", "name")
//                        .lambda()
//                        .like(User::getEmail,"abc")
//                        .inSql(User::getId, "SELECT id FROM user WHERE age < 25")
//                        .between(User::getAge,22,99)
//                        // 主动调用or表示紧接着下一个方法不是用and连接!(不调用or则默认为使用and连接)
//                        .or().eq(User::getName, "abc98")
//                        .last("limit 1"));
//        users.forEach(System.out::println);
        /*
        Preparing: SELECT id,name FROM user WHERE (EMAIL LIKE ? AND ID IN (SELECT id FROM user WHERE age < 25) AND AGE BETWEEN ? AND ? OR NAME = ?) limit 1
        Parameters: %abc%(String), 22(Integer), 99(Integer), abc98(String)
         */

        // 查询（根据 columnMap 条件）
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "abc70");
//        map.put("email", "abc70@163.com");
//        List<User> users = userMapper.selectByMap(map);
//        users.forEach(System.out::println);
        /*
        Preparing: SELECT * FROM user WHERE name = ? AND email = ?
        Parameters: abc70(String), abc70@163.com(String)
         */

        // 根据 Wrapper 条件，查询全部记录
//        List<Map<String, Object>> maps = userMapper.selectMaps(null);
//        maps.forEach(System.out::println);
        /*
        Preparing: SELECT * FROM user
         */

        // 根据 Wrapper 条件，查询全部记录。注意： 只返回第一个字段的值
//        List<Object> objects = userMapper.selectObjs(null);
//        objects.forEach(System.out::println);
        /*
        Preparing: SELECT ID,NAME,AGE,EMAIL,DEL_FLAG,VERSION,CREATE_TIME FROM user
        // 只返回了id字段的值。
         */

        // 根据 entity 条件，查询全部记录（并翻页）
//        userMapper.selectPage(new Page<>(1, 5), Wrappers.<User>query().orderByAsc("age"));
        /*
        Preparing: SELECT * FROM user ORDER BY age ASC LIMIT ?,?
        Parameters: 0(Long), 5(Long)
         */

        // 根据 Wrapper 条件，查询总记录数
        Integer count = userMapper.selectCount(null);
        System.out.println(count);
        /*
        Preparing: SELECT COUNT( 1 ) FROM user
         */
    }

    @Test
    public void testLogic() {
//        userMapper.selectCount(null);
        /*
        Preparing: SELECT COUNT( 1 ) FROM user WHERE DEL_FLAG=0
        */
        userMapper.delete(new QueryWrapper<User>()
                .lambda().gt(User::getAge,100));
        /*
        Preparing: UPDATE user SET DEL_FLAG=1 WHERE DEL_FLAG=0 AND (AGE > ?)
        Parameters: 100(Integer)
        */
    }

    @Test
    public void testVersion() {
        userMapper.insert(new User().setName("小明").setAge(23).setEmail("xm@163.com").setVersion(1));
        /*
        Preparing: INSERT INTO user ( ID, NAME, AGE, EMAIL, VERSION, CREATE_TIME ) VALUES ( ?, ?, ?, ?, ?, ? )
        Parameters: 1213659384740179970(Long), 小明(String), 23(Integer), xm@163.com(String), 1(Integer), 2020-01-05 11:12:00.682(Timestamp)
         */

        // 1.当Version一致时,执行成功version加1
//        userMapper.updateById(new User().setVersion(2).setName("小红").setId(1213659384740179970L));
        /*
        Preparing: UPDATE user SET NAME=?, VERSION=? WHERE ID=? AND VERSION=? AND DEL_FLAG=0
        Parameters: 小红(String), 3(Integer), 1213659384740179970(Long), 2(Integer)
         */
        // 2.当Version不一致时  Updates: 0
//        userMapper.updateById(new User().setVersion(2).setName("小蓝").setId(1213659384740179970L));
        /*
        Preparing: UPDATE user SET NAME=?, VERSION=? WHERE ID=? AND VERSION=? AND DEL_FLAG=0
        Parameters: 小蓝(String), 3(Integer), 1213659384740179970(Long), 2(Integer)
         */
        // 3.当不传Version,就不判断version
//        userMapper.updateById(new User().setName("小白").setId(1213659384740179970L));
        /*
        Preparing: UPDATE user SET NAME=? WHERE ID=? AND DEL_FLAG=0
        Parameters: 小白(String), 1213659384740179970(Long)
         */
    }

    @Test
    public void testType() {
        userMapper.insert(new User()
                .setName("小明")
                .setAge(23)
                .setEmail("xm@163.com")
                .setVersion(1)
                .setOtherInfo(new OtherInfo().setPhone(13556688888L).setAddress("山东")));
        /*
        Preparing: INSERT INTO user ( ID, NAME, AGE, EMAIL, VERSION, CREATE_TIME, OTHER_INFO ) VALUES ( ?, ?, ?, ?, ?, ?, ? )
        Parameters: 1213672699721781250(Long), 小明(String), 23(Integer), xm@163.com(String), 1(Integer), 2020-01-05 12:04:55.221(Timestamp), {"phone":13556688888,"address":"山东"}(String)
         */
    }
}
