package top.dongxibao.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.dongxibao.demo.entity.User;

/**
 * @ClassName UserMapper
 * @description userMapper
 * @author Dongxibao
 * @date 2020/1/4
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
