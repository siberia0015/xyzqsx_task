package com.yxy.xyzqsx_task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.xyzqsx_task.pojo.SeckillOrder;
import com.yxy.xyzqsx_task.pojo.User;

/**
 * 秒杀订单表 服务类
 *
 * @author yxy
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    /**
     * 获取秒杀结果
     *
     * @param user
     * @param goodsId
     * @return orderId 成功 ；-1 秒杀失败 ；0 排队中
     * @author yxy
     * @operation add
     **/
    Long getResult(User user, Long goodsId);
}
