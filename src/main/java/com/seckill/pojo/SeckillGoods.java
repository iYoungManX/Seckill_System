package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@TableName("seckill_goods")
@ApiModel(value = "SeckillGoods对象", description = "")
public class SeckillGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("秒杀的商品表")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("商品Id")
    private Long goodsId;

    @ApiModelProperty("秒杀价")
    private BigDecimal seckillPrice;

    @ApiModelProperty("库存数量")
    private Integer stockCount;

    @ApiModelProperty("秒杀开始时间")
    private LocalDateTime startDate;

    @ApiModelProperty("秒杀结束时间")
    private LocalDateTime endDate;


}
