package com.blog.framework.vo.sys.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * TABLE_NAME:(t_sys_user)
 *
 *  @author liuzw
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserVo implements Serializable {

    private static final long serialVersionUID = -3109975512722541576L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", example = "1")
    private Long id;

    /**
     * 账户
     */
    @ApiModelProperty(value = "账户", example = "zhangsan")
    private String account;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", example = "zhangsan")
    private String userName;

    /**
     * 状态(0,禁用;1,启用)
     */
    @ApiModelProperty(value = "状态(0,禁用;1,启用)", example = "1")
    private Integer userStatus;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", example = "1")
    private Date createTime;


}