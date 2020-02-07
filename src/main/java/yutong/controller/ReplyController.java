package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtReply;
import yutong.service.YtReplyService;
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
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    YtReplyService ytReplyService;

    /**
     * 查询
     * @param ytReply
     * @return
     */
    @GetMapping("list")
    public ResponseJson list(@RequestBody YtReply ytReply){
        List<YtReply> game_id = ytReplyService.list(new QueryWrapper<YtReply>().eq("game_id", ytReply.getGameId()));
        return new ResponseJson(200,null,game_id);
    }

    /**
     * 增加评论
     * @param ytReply
     * @return
     */
    @PostMapping("add")
    public ResponseJson add(@RequestBody YtReply ytReply){
        boolean save = ytReplyService.save(ytReply);
        return new ResponseJson(200,null,save);
    }

    /**
     * 删除评论
     * @param ytReply
     * @return
     */
    @PostMapping("remove")
    public ResponseJson remove(@RequestBody YtReply ytReply){
        boolean save = ytReplyService.remove(new QueryWrapper<YtReply>().eq("id",ytReply.getId()));
        return new ResponseJson(200,null,save);
    }


}

