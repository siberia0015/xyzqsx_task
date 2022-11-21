package com.yxy.xyzqsx_task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxy.xyzqsx_task.pojo.Goods;
import com.yxy.xyzqsx_task.vo.GoodsVo;

import java.util.List;

/**
 * 商品表 Mapper 接口
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 返回商品列表
     **/
    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVobyGoodsId(Long goodsId);
}
