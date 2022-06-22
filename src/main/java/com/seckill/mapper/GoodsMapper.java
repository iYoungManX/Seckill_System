package com.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.pojo.Goods;
import com.seckill.vo.GoodsVo;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-21
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(long goodsId);
}
