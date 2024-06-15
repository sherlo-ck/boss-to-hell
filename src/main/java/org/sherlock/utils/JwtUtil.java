package org.sherlock.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.sherlock.common.exception.BusinessExcepiton;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JwtUtil {

    private static final String SECRET = "sherlockhomleslkjhgzxcvuwehkfjsdbkzx678q90wc";
    private static final Long EXPIRE = TimeUnit.DAYS.toSeconds(1);

    public static String createJwt(String subject) {
        DateTime date = DateUtil.date();
        return Jwts.builder().id(UUID.randomUUID().toString()).subject(subject)
                .issuer("sherlock").issuedAt(date).signWith(SignatureAlgorithm.HS256, generSecret())
                .expiration(DateUtil.date(date.getTime() + EXPIRE)).compact();
    }
    public static SecretKey generSecret() {
        byte[] decode = Base64.getDecoder().decode(JwtUtil.SECRET);
        return new SecretKeySpec(decode, 0, decode.length, "HmacSHA256");
    }
    public static Claims parseJwt(String jwt) {
        if (jwt == null) {
            throw new BusinessExcepiton("token为空");
        }
        try {
            return Jwts.parser().setSigningKey(generSecret()).build().parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            log.error("token解析失败", e);
            throw new BusinessExcepiton("token解析失败");
        }
    }
}
