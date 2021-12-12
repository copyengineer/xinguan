package com.xjb.newcrowncore.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="BizOutStockInfo对象", description="")
public class BizOutStockInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String outNum;

    private String pNum;

    private Integer productNumber;

    private Date createTime;

    private Date modifiedTime;


}
