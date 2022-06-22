package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-21
 */
@Getter
@Setter
@ApiModel(value = "Goods对象", description = "")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("商品标题")
    private String goodsTitle;

    @ApiModelProperty("商品的图片")
    private String goodsImg;

    @ApiModelProperty("商品的详情介绍")
    private String goodsDetail;

    @ApiModelProperty("商品单价")
    private BigDecimal goodsPrice;

    @ApiModelProperty("商品库存，-1表示没有限制")
    private Integer goodsStock;


}
