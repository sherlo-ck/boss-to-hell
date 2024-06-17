package org.sherlock.service.impl;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sherlock.common.enums.ErrorCodeEnum;
import org.sherlock.common.exception.BusinessExcepiton;
import org.sherlock.entity.LoginEntity;
import org.sherlock.entity.SysUser;
import org.sherlock.mapper.SysUserMapper;
import org.sherlock.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sherlock.utils.JwtUtil;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sherlock
 * @since 2024-06-16
 */
@Slf4j
@Service
public class SysUserServiceImp extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 用户登录方法
     * 通过用户ID和密码尝试登录系统。如果登录成功，返回包含JWT的映射；如果失败，抛出业务异常。
     * @param user 登录用户的信息，包含用户ID和密码。
     * @return 包含JWT的映射，JWT用于后续的用户认证。
     * @throws BusinessExcepiton 如果认证失败，抛出业务异常，异常信息包含错误代码和消息。
     */
    @Override
    public Map<String, String> login(SysUser user) {
        // 创建认证令牌，包含用户ID和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword());
        // 尝试对用户进行认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证失败，抛出业务异常
        if (Objects.isNull(authenticate)) {
            BusinessExcepiton.throwException(ErrorCodeEnum.ResultCode.FAIL.getCode(), "用户登录失败");
        }
        // 从认证结果中获取登录实体
        LoginEntity loginEntity = (LoginEntity) authenticate.getPrincipal();
        // 生成JWT
        String jwt = JwtUtil.createJwt(JSON.toJSONString(loginEntity));
        // 返回包含JWT的映射
        return Map.of("token", jwt);
    }

}
