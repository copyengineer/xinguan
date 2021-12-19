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
@ApiModel(value="BizInStock对象", description="")
public class BizInStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "入库单编号")
    private String inNum;

    @ApiModelProperty(value = "类型：1：捐赠，2：下拨，3：采购,4:退货入库")
    private Integer type;

    @ApiModelProperty(value = "操作人员")
    private String operator;

    @ApiModelProperty(value = "入库单创建时间")
    private Date createTime;

    @ApiModelProperty(value = "入库单修改时间")
    private Date modified;

    @ApiModelProperty(value = "物资总数")
    private Integer productNumber;

    @ApiModelProperty(value = "来源")
    private Long supplierId;

    @ApiModelProperty(value = "描述信息")
    private String remark;

    @ApiModelProperty(value = "0:正常入库单,1:已进入回收,2:等待审核")
    private Integer status;


}
