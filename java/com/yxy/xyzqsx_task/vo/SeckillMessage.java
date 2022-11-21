package com.yxy.xyzqsx_task.vo;

import com.yxy.xyzqsx_task.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 秒杀信息
 *
 * @author: yxy
 * @date 2022/3/8 6:38 下午
 * @ClassName: SeckillMessage
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillMessage {

    private User user;

    private Long goodsId;
}
