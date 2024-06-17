package org.sherlock.controller.system;

import jakarta.annotation.Resource;
import org.sherlock.common.entiry.Result;
import org.sherlock.entity.SysUser;
import org.sherlock.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sherlock
 * @since 2024-06-16
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/login")
    public Result<Map<String, String>> login(SysUser user) {
        return Result.success(sysUserService.login(user), "用户登录成功");
    }

    @GetMapping("/getUser")
    public Result<String> getUser() {
        return Result.success("user");
    }
}
