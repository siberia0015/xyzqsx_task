package com.yxy.xyzqsx_task.vo;

import com.yxy.xyzqsx_task.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单详情返回对象
 *
 * @author: yxy
 * @ClassName: OrderDeatilVo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDeatilVo {

    private Order tOrder;

    private GoodsVo goodsVo;
}
