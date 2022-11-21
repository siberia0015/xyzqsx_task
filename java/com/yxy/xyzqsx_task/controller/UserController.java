package com.yxy.xyzqsx_task.controller;

import com.yxy.xyzqsx_task.rabbitmq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxy
 * @since 2022-10-07
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MQSender mqSender;

//    @RequestMapping(value = "/mq", method = RequestMethod.GET)
//    @ResponseBody
//    public void mq() {
//        mqSender.send("Hello");
//    }

}
