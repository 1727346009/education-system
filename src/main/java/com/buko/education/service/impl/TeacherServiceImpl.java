package com.buko.education.service.impl;

import com.buko.commons.util.JWTUtil;
import com.buko.education.dao.TeacherDao;
import com.buko.education.exception.BaseException;
import com.buko.education.po.Teacher;
import com.buko.education.po.Token;
import com.buko.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 徐健威
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private BCryptPasswordEncoder encoder;
    @Resource
    private JWTUtil jwtUtil;
    @Value("${jwt.secret}")
    private String secret;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Token loginTeacher(Teacher teacher, String host) {
        String auth = teacherDao.auth(teacher.getPassword());
        if (auth.isEmpty()) {
            throw new BaseException("用户名或密码错误");
        }
        if (!encoder.matches(teacher.getPassword(), auth)) {
            throw new BaseException("用户名或密码错误");
        }
        redisTemplate.opsForValue().set("host:" + teacher.getId(), host,
                jwtUtil.getJwtConfig().getExpiresSeconds(), TimeUnit.MILLISECONDS);
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("id", teacher.getId());
        claims.put("host", host);
        Token token = new Token();
        token.setAuthenticate(jwtUtil.generateJWT(claims, secret));
        return token;
    }
}
