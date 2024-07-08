package org.sherlock.config.security;

import jakarta.annotation.Resource;
import org.sherlock.filter.AuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置类，用于配置Spring Security的各个方面。
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Resource
    private AuthenticationTokenFilter authenticationTokenFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置认证提供者，用于处理用户认证过程。
     *
     * @return DaoAuthenticationProvider 认证提供者实例
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * 配置安全过滤链，定义哪些URL需要什么样的安全控制。
     *
     * @param http 安全配置对象，用于构建安全过滤链
     * @return SecurityFilterChain 安全过滤链实例
     * @throws Exception 如果配置过程中出现错误
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/user/login")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ).addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // 执行UsernamePasswordAuthenticationFilter前执行authenticationTokenFilter
        return http.build();
    }
}
