package com.yxy.xyzqsx_task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.xyzqsx_task.pojo.Goods;
import com.yxy.xyzqsx_task.vo.GoodsVo;

import java.util.List;

/**
 * 商品表 服务类
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 返回商品列表
     *
     * @param
     **/
    List<GoodsVo> findGoodsVo();

    /**
     * 获取商品详情
     *
     * @param goodsId
     **/
    GoodsVo findGoodsVobyGoodsId(Long goodsId);
}
