package com.xjb.newcrowncore.bean;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value="BizProductStock对象", description="")
public class BizProductStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String pNum;

    @ApiModelProperty(value = "商品库存结余")
    private Long stock;


}
