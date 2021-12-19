package com.xjb.newcrowncommon.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author 辛集斌
 * @create 2021/12/19
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MenuTreeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单/按钮ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "上级菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单/按钮名称")
    private String menuName;

    @ApiModelProperty(value = "菜单URL")
    private String url;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "类型 0菜单 1按钮")
    private String type;

    @ApiModelProperty(value = "排序")
    private Long orderNum;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;

    @ApiModelProperty(value = "0：不可用，1：可用")
    private Integer available;

    @ApiModelProperty(value = "0:不展开，1：展开")
    private Integer open;

    @ApiModelProperty(value = "子菜单")
    private Set<MenuTreeVo> childMenus;
}
