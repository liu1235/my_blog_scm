package com.blog.framework.service.impl;

import com.blog.framework.common.enums.ClassStatusEnum;
import com.blog.framework.common.exception.ServiceException;
import com.blog.framework.common.utils.CopyDataUtil;
import com.blog.framework.dao.ClassDao;
import com.blog.framework.dto.classs.ClassAddDto;
import com.blog.framework.dto.classs.ClassQueryDto;
import com.blog.framework.dto.classs.ClassUpdateDto;
import com.blog.framework.model.ClassModel;
import com.blog.framework.service.ClassService;
import com.blog.framework.vo.classs.ClassListVo;
import com.blog.framework.vo.classs.ClassVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    public List<ClassListVo> list(ClassQueryDto queryDto) {
        List<ClassModel> list = classDao.select(0L);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Long> parentIds = list.stream().map(ClassModel::getId).collect(Collectors.toList());

        List<ClassListVo> classListVos = CopyDataUtil.copyList(list, ClassListVo.class);

        //获取二级分类
        List<ClassModel> childList = classDao.select(parentIds);
        if (CollectionUtils.isNotEmpty(childList)) {
            Map<Long, List<ClassModel>> map = childList.stream().collect(Collectors.groupingBy(ClassModel::getParentId));
            classListVos.forEach(v -> {
                v.setClassStatus(ClassStatusEnum.getMsg(v.getStatus()));
                List<ClassModel> models = map.get(v.getId());
                if (CollectionUtils.isNotEmpty(models)) {
                    v.setChildren(models.stream().map(v1 -> ClassListVo.builder()
                            .id(v1.getId())
                            .status(v1.getStatus())
                            .classStatus(ClassStatusEnum.getMsg(v1.getStatus()))
                            .className(v1.getClassName())
                            .createTime(v1.getCreateTime())
                            .build()).collect(Collectors.toList()));
                }
            });
        }
        return classListVos;
    }

    @Override
    public ClassListVo detail(Long id) {
        ClassModel classModel = classDao.selectById(id);
        return CopyDataUtil.copyObject(classModel, ClassListVo.class);
    }

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

    @Override
    public Boolean add(ClassAddDto addDto) {
        ClassModel addModel = CopyDataUtil.copyObject(addDto, ClassModel.class);
        List<ClassModel> list = classDao.select(ClassModel.builder().className(addDto.getClassName()).build());
        if (CollectionUtils.isNotEmpty(list)) {
            throw new ServiceException("分类[" + addDto.getClassName() + "]已存在");
        }
        return classDao.add(addModel);
    }

    @Override
    public Boolean edit(ClassUpdateDto updateDto) {
        ClassModel classModel = classDao.selectById(updateDto.getId());
        if (classModel.getParentId() == 0 && updateDto.getParentId() != 0) {
            throw new ServiceException("一级分类不允许修改上级分类");
        }
        ClassModel updateModel = CopyDataUtil.copyObject(updateDto, ClassModel.class);
        return classDao.edit(updateModel);
    }

    @Override
    public Boolean delete(Long id) {
        return classDao.deleteById(id);
    }

    @Override
    public List<ClassVo> selectList() {
        List<ClassModel> list = classDao.select(ClassModel.builder().status(ClassStatusEnum.ENABLE.getCode()).build());
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(v -> ClassVo.builder()
                .classId(v.getId())
                .className(v.getClassName())
                .build()
        ).collect(Collectors.toList());
    }


    private List<ClassVo> select(Long parentId) {
        List<ClassModel> list = classDao.select(parentId);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(v -> ClassVo.builder()
                .className(v.getClassName())
                .classId(v.getId())
                .build())
                .collect(Collectors.toList());
    }

}
