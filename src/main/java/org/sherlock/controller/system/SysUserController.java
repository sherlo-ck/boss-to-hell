package org.sherlock.controller.system;

import jakarta.annotation.Resource;
import org.sherlock.common.entiry.Result;
import org.sherlock.entity.SysUser;
import org.sherlock.service.SysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody() SysUser user) {
        return Result.success(sysUserService.login(user), "用户登录成功");
    }

    @GetMapping("/getUser")
    @PreAuthorize("hasAuthority('select')")
    public Result<String> getUser() {
        return Result.success("user");
    }
}
