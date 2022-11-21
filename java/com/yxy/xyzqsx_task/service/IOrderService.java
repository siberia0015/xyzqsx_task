package com.yxy.xyzqsx_task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxy.xyzqsx_task.pojo.Order;
import com.yxy.xyzqsx_task.pojo.User;
import com.yxy.xyzqsx_task.vo.GoodsVo;
import com.yxy.xyzqsx_task.vo.OrderDeatilVo;

/**
 * 服务类
 *
 * @author: yxy
 */
public interface IOrderService extends IService<Order> {

    /**
     * 秒杀
     *
     * @param user    用户对象
     * @param goodsVo 商品对象
     **/
    Order secKill(User user, GoodsVo goodsVo);

    /**
     * 订单详情方法
     *
     * @param orderId
     **/
    OrderDeatilVo detail(Long orderId);

    /**
     * 获取秒杀地址
     *
     * @param user
     * @param goodsId
     **/
    String createPath(User user, Long goodsId);

    /**
     * 校验秒杀地址
     *
     * @param user
     * @param goodsId
     * @param path
     **/
    boolean checkPath(User user, Long goodsId, String path);

    /**
     * 校验验证码
     * @param user
     * @param goodsId
     * @param captcha
     * @return boolean
     **/
    boolean checkCaptcha(User user, Long goodsId, String captcha);
}
