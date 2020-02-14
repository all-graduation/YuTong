package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtGame;
import yutong.entity.YtOrder;
import yutong.entity.YtOrderVo;
import yutong.service.YtGameService;
import yutong.service.YtOrderService;
import yutong.util.ResponseJson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @Autowired
    YtGameService ytGameService;

    /**
     * 查询一个用户的所有订单
     * @param id
     * @return
     */
    @GetMapping("list")
    public ResponseJson listByUserId(@RequestParam int id){
        List<YtOrder> orders = ytOrderService.list(new QueryWrapper<YtOrder>().eq("user_id", id));
        if(CollectionUtils.isEmpty(orders)){
            return new ResponseJson(200,null,null);
        }
        List<YtOrderVo> ytOrderVos = new ArrayList<>();

        List<YtGame> games = ytGameService.list(new QueryWrapper<YtGame>()
                .in("game_id", orders.stream().map(YtOrder::getGameId).collect(Collectors.toList())));
        //拿到游戏名字对id
        Map<Integer, String> collect = games.stream().collect(Collectors.toMap(YtGame::getGameId, YtGame::getGameName));
        orders.forEach(item->{
            YtOrderVo ytOrderVo = new YtOrderVo();
            BeanUtils.copyProperties(item,ytOrderVo);
            ytOrderVo.setGame(collect.get(item.getGameId()));
            if(item.getStatus()==0){
                ytOrderVo.setStatus("未支付");
            }
            if(item.getStatus()==1){
                ytOrderVo.setStatus("已支付");
            }else {
                ytOrderVo.setStatus("已删除");
            }
            ytOrderVos.add(ytOrderVo);
        });

        return new ResponseJson(200,null,ytOrderVos);
    }

    /**
     * 查询用户的所有订单
     * @return
     */
    @GetMapping("allList")
    public ResponseJson allList(){
        List<YtOrder> orders = ytOrderService.list();
        if(CollectionUtils.isEmpty(orders)){
            return new ResponseJson(200,null,null);
        }
        List<YtOrderVo> ytOrderVos = new ArrayList<>();

        List<YtGame> games = ytGameService.list(new QueryWrapper<YtGame>()
                .in("game_id", orders.stream().map(YtOrder::getGameId).collect(Collectors.toList())));
        //拿到游戏名字对id
        Map<Integer, String> collect = games.stream().collect(Collectors.toMap(YtGame::getGameId, YtGame::getGameName));
        orders.forEach(item->{
            YtOrderVo ytOrderVo = new YtOrderVo();
            BeanUtils.copyProperties(item,ytOrderVo);
            ytOrderVo.setGame(collect.get(item.getGameId()));
            if(item.getStatus()==0){
                ytOrderVo.setStatus("未支付");
            }
            if(item.getStatus()==1){
                ytOrderVo.setStatus("已支付");
            }else {
                ytOrderVo.setStatus("已删除");
            }
            ytOrderVos.add(ytOrderVo);
        });

        return new ResponseJson(200,null,ytOrderVos);
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


    /**
     * 下订单
     * @param ytOrder
     * @return
     */
    @PostMapping("order")
    public ResponseJson buy(@RequestBody YtOrder ytOrder){
        //先判断是不是已经有订单存在了，如果有的话就不用再买了
        List<YtOrder> exitOrder = ytOrderService.list(new QueryWrapper<YtOrder>()
                .eq("game_id",ytOrder.getGameId())
                .eq("user_id",ytOrder.getUserId()));
        if(!CollectionUtils.isEmpty(exitOrder)){
            return new ResponseJson(200,null,null);
        }
        YtGame game = ytGameService.getOne(new QueryWrapper<YtGame>().eq("game_id", ytOrder.getGameId()));
        game.setButCount(game.getGameId()+1);
        ytOrder.setMoney(game.getMoney());
        boolean user_id = ytOrderService.save(ytOrder);
        ytGameService.updateById(game);
        return new ResponseJson(200,null,user_id);
    }
}

