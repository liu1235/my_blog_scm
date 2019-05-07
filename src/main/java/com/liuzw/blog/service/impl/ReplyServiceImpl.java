/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.service.impl;



import com.github.pagehelper.PageHelper;
import com.liuzw.blog.bean.ReplyBean;
import com.liuzw.blog.bean.ReplyQueryBean;
import com.liuzw.blog.mapper.ReplyMapper;
import com.liuzw.blog.model.ReplyModel;
import com.liuzw.blog.service.ReplyService;
import com.liuzw.blog.utils.CopyDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 *  ReplyServiceImpl
 *
 *  @liuzw
 */
@Service("replyService")
public class ReplyServiceImpl  implements ReplyService {
     		
		
     @Autowired
     private ReplyMapper replyMapper;


     @Override
     public List<ReplyModel> getList(ReplyQueryBean replyQueryBean) {
	    PageHelper.startPage(replyQueryBean.getPageNumberByDefault(), replyQueryBean.getPageSizeByDefault());
	    Example example = new Example(ParamsModel.class);
            Example.Criteria createCriteria = example.createCriteria();
	    //此处写查询条件
//         if (StringUtils.isNotEmpty(replyQueryBean.getName())) {
//               createCriteria.andLike("name", "%" + bean.getName().trim() + "%");
//          }
	     return replyMapper.selectByExample(example);
     }

    @Override
    public ReplyModel getById(Long id) {
    	  return replyMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
		rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean insert(ReplyBean replyBean) {
	   ReplyModel insertObj = CopyDataUtil.copyObject(replyBean, ReplyModel.class);
	   insertObj.setId(null);
	   return replyMapper.insertSelective(insertObj) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
		rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean update(ReplyBean replyBean) {
	     ReplyModel updateObj = CopyDataUtil.copyObject(replyBean, ReplyModel.class);
	     return replyMapper.updateByPrimaryKeySelective(updateObj) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
		rollbackFor = {RuntimeException.class, Exception.class})
    public Boolean delete(Long id) {
	      return replyMapper.deleteByPrimaryKey(id) > 0;
    }


}