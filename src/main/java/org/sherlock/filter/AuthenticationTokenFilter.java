package org.sherlock.filter;


import com.alibaba.fastjson2.JSON;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.sherlock.common.exception.BusinessExcepiton;
import org.sherlock.entity.LoginEntity;
import org.sherlock.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.startsWith("/bosstohell/user/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (StringUtils.isEmpty(request.getHeader("Authorization"))) {
            response.setStatus(401);
            response.getWriter().write("Unauthorized");
            BusinessExcepiton.throwException(401, "Unauthorized，该用户没有访问权限！");
        }
        String token = request.getHeader("Authorization");
        String subject = JwtUtil.parseJwt(token).getSubject();
        LoginEntity loginEntity = JSON.parseObject(subject, LoginEntity.class);
        log.info("令牌解析成功: {}", JSON.toJSONString(loginEntity.getSysUser()));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginEntity, null, loginEntity.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
