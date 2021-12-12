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
@ApiModel(value="BizHealth对象", description="")
public class BizHealth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String address;

    private Long userId;

    private Integer situation;

    private Integer touch;

    private Integer passby;

    private Integer reception;

    private Date createTime;


}
