package org.sherlock;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.junit.jupiter.api.Test;
import org.sherlock.entity.SysUser;
import org.sherlock.service.SysUserService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.annotation.Validated;

@Slf4j
@SpringBootTest
public class SysUserTest {

    @Resource
    private SysUserService servicel;

    private SysUser buildUser(@Validated(Insert.class) SysUser sysUser) {
        sysUser.setUserName("admin").setPassword("admin").setUserType("0").setUserStatus("0").setDeleted("0");
        return sysUser;
    }
}
