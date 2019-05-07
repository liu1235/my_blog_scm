/** * Copyright (c) 2018 ABC.Co.Ltd. All rights reserved. */

package com.liuzw.blog.service;


import com.liuzw.blog.bean.ReplyBean;
import com.liuzw.blog.bean.ReplyQueryBean;
import com.liuzw.blog.model.ReplyModel;

import java.util.List;

import .model.Reply;
import .dto.Reply;

/**
 * interface Reply
 *
 * @liuzw
 */
public interface ReplyService {


   /**
    * 返回分页列表信息
    *
    * @param reply   数据
    * @return       list<Reply>
    */
    List<Reply> getList(ReplyQueryBean replyQueryBean);

    /**
     * 根据id返回信息
     * @param id     id
     * @return       ReplyModel
     */
    ReplyModel getById(Long id);


    /**
     * 根据ID删除
     *
     * @param id     id
     * @return       Boolean
     */
    Boolean delete(Long id);

    /**
     *增加
     *
     * @param replyBean   数据
     * @return      Boolean
     */
    Boolean insert(ReplyBean replyBean);

    /**
     *更新
     *
     * @param replyBean  数据
     * @return     Boolean
     */
    Boolean update(ReplyBean replyBean);
	
}