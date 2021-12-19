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
 * 
 * </p>
 *
 * @author xjb
 * @since 2021-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BizConsumer对象", description="")
public class BizConsumer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "物资消费方")
    private String name;

    @ApiModelProperty(value = "地址")
    private String address;

    private Date createTime;

    private Date modifiedTime;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    private Integer sort;

    @ApiModelProperty(value = "联系人姓名")
    private String contact;


}
