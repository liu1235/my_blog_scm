/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.service.impl;



import com.github.pagehelper.PageHelper;
import com.liuzw.blog.bean.CommentBean;
import com.liuzw.blog.bean.CommentQueryBean;
import com.liuzw.blog.mapper.CommentMapper;
import com.liuzw.blog.model.CommentModel;
import com.liuzw.blog.service.CommentService;
import com.liuzw.blog.utils.CopyDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 *  CommentServiceImpl
 *
 *  @liuzw
 */
@Service("commentService")
public class CommentServiceImpl  implements CommentService {
     		
		
     @Autowired
     private CommentMapper commentMapper;


     @Override
     public List<CommentModel> getList(CommentQueryBean commentQueryBean) {
	    PageHelper.startPage(commentQueryBean.getPageNumberByDefault(), commentQueryBean.getPageSizeByDefault());
	    Example example = new Example(ParamsModel.class);
            Example.Criteria createCriteria = example.createCriteria();
	    //此处写查询条件
//         if (StringUtils.isNotEmpty(commentQueryBean.getName())) {
//               createCriteria.andLike("name", "%" + bean.getName().trim() + "%");
//          }
	     return commentMapper.selectByExample(example);
     }

    @Override
    public CommentModel getById(Long id) {
    	  return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
		rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean insert(CommentBean commentBean) {
	   CommentModel insertObj = CopyDataUtil.copyObject(commentBean, CommentModel.class);
	   insertObj.setId(null);
	   return commentMapper.insertSelective(insertObj) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
		rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean delete(Long id) {
	      return commentMapper.deleteByPrimaryKey(id) > 0;
    }


}