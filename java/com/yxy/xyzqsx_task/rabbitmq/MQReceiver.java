package com.yxy.xyzqsx_task.rabbitmq;

import com.yxy.xyzqsx_task.pojo.SeckillOrder;
import com.yxy.xyzqsx_task.pojo.User;
import com.yxy.xyzqsx_task.service.IGoodsService;
import com.yxy.xyzqsx_task.service.IOrderService;
import com.yxy.xyzqsx_task.utils.JsonUtil;
import com.yxy.xyzqsx_task.vo.GoodsVo;
import com.yxy.xyzqsx_task.vo.SeckillMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 消息消费者
 *
 * @author: yxy
 * @ClassName: MQReceiver
 */
@Service
@Slf4j
public class MQReceiver {

    @Autowired
    private IGoodsService goodsServicel;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IOrderService orderService;


    /**
     * 下单操作
     **/
    @RabbitListener(queues = "seckillQueue")
    public void receive(String message) {
        log.info("接收消息：" + message);
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(message, SeckillMessage.class);
        Long goodsId = seckillMessage.getGoodsId();
        User user = seckillMessage.getUser();
        GoodsVo goodsVo = goodsServicel.findGoodsVobyGoodsId(goodsId);
        if (goodsVo.getStockCount() < 1) {
            return;
        }
        //判断是否重复抢购
        SeckillOrder tSeckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (tSeckillOrder != null) {
            return;
        }
        //下单操作
        orderService.secKill(user, goodsVo);

    }


//    @RabbitListener(queues = "queue")
//    public void receive(Object msg) {
//        System.out.println("接收到的消息" + msg);
//    }
//
//
//    @RabbitListener(queues = "queue_fanout01")
//    public void receive01(Object msg) {
//        log.info("QUEUE01接收消息" + msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout02")
//    public void receive02(Object msg) {
//        log.info("QUEUE02接收消息" + msg);
//    }
//
//    @RabbitListener(queues = "queue_direct01")
//    public void receive03(Object msg) {
//        log.info("QUEUE01接收消息" + msg);
//    }
//
//    @RabbitListener(queues = "queue_direct02")
//    public void receive04(Object msg) {
//        log.info("QUEUE02接收消息" + msg);
//    }
//
//    @RabbitListener(queues = "queue_topic01")
//    public void receive05(Object msg) {
//        log.info("QUEUE01接收消息" + msg);
//    }
//
//    @RabbitListener(queues = "queue_topic02")
//    public void receive06(Object msg) {
//        log.info("QUEUE02接收消息" + msg);
//    }
//
//    @RabbitListener(queues = "queue_header01")
//    public void receive07(Message message) {
//        log.info("QUEUE01接收消息 message对象" + message);
//        log.info("QUEUE01接收消息" + new String(message.getBody()));
//    }
//
//    @RabbitListener(queues = "queue_header02")
//    public void receive08(Message message) {
//        log.info("QUEUE02接收消息 message对象" + message);
//        log.info("QUEUE02接收消息" + new String(message.getBody()));
//    }

}
