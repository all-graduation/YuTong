package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtOrder;
import yutong.service.YtOrderService;
import yutong.util.ResponseJson;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 于童
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    YtOrderService ytOrderService;

    /**
     * 查询一个用户的所有订单
     * @param ytOrder
     * @return
     */
    @GetMapping("list")
    public ResponseJson listByUserId(@RequestBody YtOrder ytOrder){
        List<YtOrder> user_id = ytOrderService.list(new QueryWrapper<YtOrder>().eq("user_id", ytOrder.getUserId()));
        return new ResponseJson(200,null,user_id);
    }

    /**
     * 查询一个订单通过id
     * @param ytOrder
     * @return
     */
    @GetMapping("order")
    public ResponseJson getById(@RequestBody YtOrder ytOrder){
        YtOrder user_id = ytOrderService.getOne(new QueryWrapper<YtOrder>().eq("id", ytOrder.getId()));
        return new ResponseJson(200,null,user_id);
    }

    /**
     * 更改订单状态
     * @param ytOrder
     * @return
     */
    @PutMapping("order")
    public ResponseJson editOrder(@RequestBody YtOrder ytOrder){
        boolean user_id = ytOrderService.update(new UpdateWrapper<YtOrder>()
                .eq("id",ytOrder.getId())
                .set("status",ytOrder.getStatus()));
        return new ResponseJson(200,null,user_id);
    }
}

