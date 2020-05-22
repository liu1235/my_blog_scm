package com.blog.framework.dto.sys.user;

import com.blog.framework.common.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TABLE_NAME:(t_sys_user)
 *
 *  @author liuzw
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserQueryDto extends BasePage implements Serializable {

    private static final long serialVersionUID = -1047637505892508948L;

    /**
     * 账户
     */
    @ApiModelProperty(value = "账户", required = true, example = "zhangsan")
    private String account;

    /**
     * 状态(0,禁用;1,启用)
     */
    @ApiModelProperty(value = "状态(0,禁用;1,启用)", required = true, example = "1")
    private Integer userStatus;
}