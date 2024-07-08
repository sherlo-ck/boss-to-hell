package org.sherlock.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.sherlock.common.exception.BusinessExcepiton;
import org.sherlock.entity.LoginEntity;
import org.sherlock.entity.SysUser;
import org.sherlock.mapper.SysUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class LoginServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户id查询用户
     *
     * @param userid
     * @return UserDetails
     * @throws UsernameNotFoundException
     * 1.根据id查询用户，存在校验密码，不存在抛出异常 TODO 后续调整为创建用户
     * 2.校验用户登录密码
     * 3.封装用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserId, userid));
        if (Objects.isNull(sysUser)) {
            throw new BusinessExcepiton("用户不存在，请创建新用户！");
        }
        // 添加权限列表 todo 查询数据库
        List<String> authenticationList = new ArrayList<>();
        authenticationList.add("select");
        return new LoginEntity(sysUser, authenticationList);
    }
}
