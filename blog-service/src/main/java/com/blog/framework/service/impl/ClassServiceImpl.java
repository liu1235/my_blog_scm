package com.blog.framework.service.impl;

import com.blog.framework.dao.ClassDao;
import com.blog.framework.model.ClassModel;
import com.blog.framework.service.ClassService;
import com.blog.framework.vo.ClassVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-01-14
 **/
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassDao classDao;

    @Override
    public List<ClassVo> first() {
        return select(0L);
    }

    @Override
    public ClassVo second(Long classId) {
        //获取一级分类详情
        ClassModel classModel = classDao.selectById(classId);
        if (classModel == null) {
            return new ClassVo();
        }
        return ClassVo.builder()
                .classId(classId)
                .className(classModel.getClassName())
                .child(select(classId))
                .build();
    }


    private List<ClassVo> select(Long parentId) {
        List<ClassModel> list = classDao.select(parentId);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(v -> ClassVo.builder()
                .className(v.getClassName())
                .classId(v.getId())
                .build()).collect(Collectors.toList());
    }

}
