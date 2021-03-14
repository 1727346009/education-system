package com.buko.education.service.impl;

import com.buko.commons.util.JWTUtil;
import com.buko.education.dao.StudentDao;
import com.buko.education.exception.BaseException;
import com.buko.education.po.Student;
import com.buko.education.po.Token;
import com.buko.education.service.StudentService;
import com.buko.education.vo.ShowStudentVO;
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
public class StudentServiceImpl implements StudentService {
    @Resource
    private BCryptPasswordEncoder encoder;
    @Resource
    private JWTUtil jwtUtil;
    @Value("${jwt.secret}")
    private String secret;
    @Resource
    private StudentDao studentDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ShowStudentVO queryStudent(String id) {
        return studentDao.queryStudent(id);
    }

    @Override
    public Token loginStudent(Student student, String host) {
        String auth = studentDao.auth(student.getId());
        if (auth.isEmpty()) {
            throw new BaseException("用户名或密码错误");
        }
        if (!encoder.matches(student.getPassword(), auth)) {
            throw new BaseException("用户名或密码错误");
        }
        redisTemplate.opsForValue().set("host:" + student.getId(), host,
                jwtUtil.getJwtConfig().getExpiresSeconds(), TimeUnit.MILLISECONDS);
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("id", student.getId());
        claims.put("host", host);
        Token token = new Token();
        token.setAuthenticate(jwtUtil.generateJWT(claims, secret));
        return token;
    }
}
