package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtGame;
import yutong.service.YtGameService;
import yutong.util.ResponseJson;

import java.util.Comparator;
import java.util.List;
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
@RequestMapping("/game")
public class GameController {

    @Autowired
    YtGameService ytGameService;

    /**
     * 查询游戏列表
     * @return
     */
    @GetMapping("/list")
    public ResponseJson list(){
        return new ResponseJson(200,null,ytGameService.list());
    }

    /**
     * 查询游戏列表 按照时间排序，只拿前三条
     * @return
     */
    @GetMapping("/listTime")
    public ResponseJson listTIme(){
        List<YtGame> list = ytGameService.list();
        list=list.stream().sorted(Comparator.comparing(YtGame::getCreateTime)).limit(3).collect(Collectors.toList());
        return new ResponseJson(200,null,list);
    }



    /**
     * 查询一个游戏通过id
     * @return
     */
    @GetMapping("/game")
    public ResponseJson getById(@RequestBody YtGame ytGame){
        YtGame ytGame1 = ytGameService.getOne(new QueryWrapper<YtGame>().eq("game_id", ytGame.getGameId()));
        return new ResponseJson(200,null,ytGame1);
    }

    /**
     * 上架一个游戏
     * @return
     */
    @PostMapping("/game")
    public ResponseJson game(@RequestBody YtGame ytGame){
        boolean ytGame1 = ytGameService.save(ytGame);
        return new ResponseJson(200,null,ytGame1);
    }

    /**
     * 下架一个游戏
     * @return
     */
    @DeleteMapping("/game")
    public ResponseJson delete(@RequestBody YtGame ytGame){
        boolean ytGame1 = ytGameService.remove(new QueryWrapper<YtGame>().eq("game_id",ytGame.getGameId()));
        return new ResponseJson(200,null,ytGame1);
    }

}

