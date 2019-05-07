package com.liuzw.blog.service;


import com.liuzw.blog.model.CommentModel;

import java.util.List;

/**
 * interface Comment
 *
 * @liuzw
 */
public interface CommentService {


   /**
    * 返回分页列表信息
    *
    * @param comment   数据
    * @return       list<Comment>
    */
    List<Comment> getList(CommentQueryBean commentQueryBean);

    /**
     * 根据id返回信息
     * @param id     id
     * @return       CommentModel
     */
    CommentModel getById(Long id);


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
     * @param commentBean   数据
     * @return      Boolean
     */
    Boolean insert(CommentBean commentBean);

    /**
     *更新
     *
     * @param commentBean  数据
     * @return     Boolean
     */
    Boolean update(CommentBean commentBean);
	
}