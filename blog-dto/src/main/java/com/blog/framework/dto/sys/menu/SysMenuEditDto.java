package com.blog.framework.dto.sys.menu;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * TABLE_NAME:(t_sys_menu)
 *
 * @author liuzw
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenuEditDto extends SysMenuAddDto implements Serializable {

    private static final long serialVersionUID = -6627557363165259253L;
    /**
     * id
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id", required = true, example = "1")
    private Long id;

}