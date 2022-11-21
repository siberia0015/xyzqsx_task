package com.yxy.xyzqsx_task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxy.xyzqsx_task.mapper.SeckillGoodsMapper;
import com.yxy.xyzqsx_task.pojo.SeckillGoods;
import com.yxy.xyzqsx_task.service.ISeckillGoodsService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 秒杀商品表 服务实现类
 */
@Service
@Primary
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements ISeckillGoodsService {

}
