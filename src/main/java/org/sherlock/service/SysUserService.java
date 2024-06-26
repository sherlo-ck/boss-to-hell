package org.sherlock.service;

import org.sherlock.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sherlock
 * @since 2024-06-16
 */
public interface SysUserService extends IService<SysUser> {

    Map<String, String> login(SysUser user);
}
