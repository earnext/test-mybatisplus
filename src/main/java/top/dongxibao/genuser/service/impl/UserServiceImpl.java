package top.dongxibao.genuser.service.impl;

import top.dongxibao.genuser.entity.User;
import top.dongxibao.genuser.mapper.UserMapper;
import top.dongxibao.genuser.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Dongxibao
 * @since 2020-01-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
