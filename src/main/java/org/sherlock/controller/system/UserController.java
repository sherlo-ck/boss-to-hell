package org.sherlock.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "用户接口")
public class UserController {

    @GetMapping("/login")
    public String login() {

        return "login";
    }
}
