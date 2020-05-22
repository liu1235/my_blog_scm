package com.blog.framework.dto.sys.role;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * TABLE_NAME:(t_sys_role)
 *
 * @author liuzw
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleEditDto extends SysRoleAddDto implements Serializable {

    private static final long serialVersionUID = -3323839582637474170L;

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id", required = true)
    private Long id;

}