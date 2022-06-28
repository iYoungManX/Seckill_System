package com.seckill.controller;
import com.seckill.pojo.User;
import com.seckill.service.IOrderService;
import com.seckill.vo.OrderDetailVo;
import com.seckill.vo.RespBean;
import com.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author iYoungMan
 * @since 2022-06-21
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;
    /**
     * 订单详情
     * @param user
     * @param orderId
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user,Long orderId){
        if (null==user){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        OrderDetailVo detail = orderService.detail(orderId);
        return RespBean.success(detail);
    }

}
