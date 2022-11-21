package com.yxy.xyzqsx_task.controller;

import com.yxy.xyzqsx_task.pojo.User;
import com.yxy.xyzqsx_task.service.IOrderService;
import com.yxy.xyzqsx_task.vo.OrderDeatilVo;
import com.yxy.xyzqsx_task.vo.RespBean;
import com.yxy.xyzqsx_task.vo.RespBeanEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 */
@RestController
@RequestMapping("/order")
@Api(value = "订单", tags = "订单")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @ApiOperation("订单")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public RespBean detail(User tUser, Long orderId) {
        if (tUser == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        OrderDeatilVo orderDeatilVo = orderService.detail(orderId);
        return RespBean.success(orderDeatilVo);
    }
}
