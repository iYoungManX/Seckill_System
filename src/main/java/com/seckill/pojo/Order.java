package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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
@TableName("order_info")
@ApiModel(value = "OrderInfo对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("收获地址ID")
    private Long deliveryAddrId;

    @ApiModelProperty("冗余过来的商品名称")
    private String goodsName;

    @ApiModelProperty("商品数量")
    private Integer goodsCount;

    @ApiModelProperty("商品单价")
    private BigDecimal goodsPrice;

    @ApiModelProperty("1-pc，2-android，3-ios")
    private Integer orderChannel;

    @ApiModelProperty("订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成")
    private Integer status;

    @ApiModelProperty("订单的创建时间")
    private Date createDate;

    @ApiModelProperty("支付时间")
    private Date payDate;


}
