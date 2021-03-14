package com.buko.education.interceptor;

import com.buko.commons.util.IPUtils;
import com.buko.commons.util.JWTUtil;
import com.buko.education.annotation.PassToken;
import com.buko.education.annotation.SignIn;
import com.buko.education.exception.AuthenticateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * @author 徐健威
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    private IPUtils ipUtils;
    @Resource
    private JWTUtil jwtUtil;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${spring.profiles.active}")
    private String profiles;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Token");
        log.debug("interceptor: in");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        String requestIP = ipUtils.getIpAddr(request);
        log.info("request ip address is " + requestIP);

        if (method.isAnnotationPresent(SignIn.class)) {
            SignIn signIn = method.getAnnotation(SignIn.class);
            if (signIn.required()) {
                request.setAttribute("host", requestIP);
                return true;
            }
        }
        // 验证 token
        if (token == null || "".equals(token)) {
            throw new AuthenticateException("请重新登录");
        }
        Map<String, Object> claims = jwtUtil.parseJWT(secret, token);
        // 验证 id
        String id = (String) claims.get("id");
        idHandle(id);
        request.setAttribute("id", id);
        // 验证 ip
        String tokenIP = (String) claims.get("host");
        ipHandle(id, tokenIP, requestIP);
        return true;
    }

    public void idHandle(String id) {
        log.debug("request id is " + id);
        if ("".equals(id)) {
            throw new AuthenticateException("请重新登录");
        }
    }

    public void ipHandle(String key, String tokenIP, String requestIP) {
        if (!requestIP.equals(tokenIP)) {
            throw new AuthenticateException("请重新登录");
        }
        String cache = redisTemplate.opsForValue().get("host:" + key);
        log.debug("request host cache is " + cache);
        if (!requestIP.equals(cache)) {
            throw new AuthenticateException("请重新登录");
        }
    }
}
