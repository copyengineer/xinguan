package com.xjb.newcrowncommon.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author xjb
 * @since 2021-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TbRole对象", description="角色表")
public class TbRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;

    @ApiModelProperty(value = "是否可用,0:不可用，1：可用")
    private Integer status;


}
