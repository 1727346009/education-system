package com.buko.education.service.impl;

import com.buko.education.dao.ClassDao;
import com.buko.education.service.ClassService;
import com.buko.education.vo.ShowClassVO;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 徐健威
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ClassServiceImpl implements ClassService {
    @Reference
    public ClassDao classDao;

    @Override
    public ShowClassVO queryStudents(String teacherId) {
        return classDao.getStudents(teacherId);
    }
}
