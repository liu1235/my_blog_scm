package com.blog.framework.dao.impl;

import com.blog.framework.bo.BlogLikeOrCollectBo;
import com.blog.framework.bo.BlogQueryBo;
import com.blog.framework.dao.BlogDao;
import com.blog.framework.mapper.BlogMapper;
import com.blog.framework.mapper.ClassMapper;
import com.blog.framework.model.BlogModel;
import com.blog.framework.model.ClassModel;
import com.blog.framework.vo.blog.BlogArchiveVO;
import com.blog.framework.vo.blog.BlogTopVO;
import com.blog.framework.vo.blog.BlogVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * blog
 *
 * @author liuzw
 * @date 2020-01-02
 **/
@Repository
public class BlogDaoImpl implements BlogDao {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ClassMapper classMapper;


    @Override
    public List<BlogVO> list(BlogQueryBo bo) {
        bo.setClassIds(getClassIds(bo.getClassId()));
        return blogMapper.list(bo);
    }

    @Override
    public List<BlogArchiveVO> archive() {
        return blogMapper.archive();
    }

    @Override
    public List<BlogTopVO> topBlogList() {
        return blogMapper.topBlogList();
    }

    @Override
    public List<BlogVO> getLikeOrCollectBlogList(BlogLikeOrCollectBo bo) {
        bo.setClassIds(getClassIds(bo.getClassId()));
        return blogMapper.getLikeOrCollectBlogList(bo);
    }

    @Override
    public BlogModel detail(Long id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(BlogModel model) {
        return blogMapper.updateByPrimaryKeySelective(model) > 0;
    }

    @Override
    public List<BlogModel> getByIds(List<Long> blogIds) {
        Example example = new Example(BlogModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", blogIds);
        return blogMapper.selectByExample(example);
    }


    /**
     * 获取该分类下所有下级分类
     *
     * @param classId 分类id
     * @return List<Long>
     */
    private List<Long> getClassIds(Long classId) {
        List<Long> classIds = new ArrayList<>();
        if (classId != null) {
            classIds.add(classId);
            //获取该分类是否有下级分类
            List<ClassModel> list = classMapper.select(ClassModel.builder().parentId(classId).build());
            if (CollectionUtils.isNotEmpty(list)) {
                classIds.addAll(list.stream().map(ClassModel::getId).collect(Collectors.toList()));
            }
        }
        return classIds;
    }

}
