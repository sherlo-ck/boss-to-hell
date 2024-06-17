package org.sherlock.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登录实体类，实现了UserDetails接口，用于Spring Security的身份验证。
 * 该类封装了SysUser对象的信息，以适应Spring Security的认证需求。
 */
@Data
@NoArgsConstructor
public class LoginEntity implements UserDetails {

    /**
     * 系统用户对象，包含了登录所需的用户信息。
     */
    private SysUser sysUser;

    /**
     * 构造函数，用于创建LoginEntity实例。
     *
     * @param sysUser 系统用户对象，包含了登录所需的用户信息。
     */
    public LoginEntity(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    /**
     * 获取用户的权限集合。
     * 该方法当前未实现，需要根据实际需求进行扩展。
     *
     * @return 用户的权限集合。
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取用户密码。
     *
     * @return 用户密码。
     */
    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    /**
     * 获取用户用户名。
     *
     * @return 用户用户名。
     */
    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    /**
     * 判断用户的账户是否未过期。
     *
     * @return 如果账户未过期返回true，否则返回false。
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断用户的账户是否未被锁定。
     *
     * @return 如果账户未被锁定返回true，否则返回false。
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 判断用户的密码是否未过期。
     *
     * @return 如果密码未过期返回true，否则返回false。
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断用户是否被启用。
     *
     * @return 如果用户被启用返回true，否则返回false。
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}