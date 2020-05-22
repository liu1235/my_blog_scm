package com.blog.framework.dto.sys.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * TABLE_NAME:(t_sys_user)
 *
 * @author liuzw
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserEditDto extends SysUserAddDto implements Serializable {

    private static final long serialVersionUID = 7186313928166271647L;

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id", required = true, example = "1")
    private Long id;
}