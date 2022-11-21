package com.yxy.xyzqsx_task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.xyzqsx_task.mapper.GoodsMapper;
import com.yxy.xyzqsx_task.pojo.Goods;
import com.yxy.xyzqsx_task.service.IGoodsService;
import com.yxy.xyzqsx_task.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品表 服务实现类
 */
@Service
@Primary
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    @Override
    public GoodsVo findGoodsVobyGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVobyGoodsId(goodsId);
    }
}
