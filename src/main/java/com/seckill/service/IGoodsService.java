package com.seckill.service;

import com.seckill.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-21
 */
public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(long goodsId);
}
