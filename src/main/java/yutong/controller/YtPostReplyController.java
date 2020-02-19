package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.*;
import yutong.service.YtPostReplyService;
import yutong.service.YtPostService;
import yutong.service.YtUserService;
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
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/ytPostReply")
public class YtPostReplyController {

    @Autowired
    YtPostService ytPostService;
    @Autowired
    YtPostReplyService ytPostReplyService;

    @Autowired
    YtUserService ytUserService;

    @GetMapping("list")
    public ResponseJson list(@RequestParam int postId){
        List<YtPostReply> lsit = ytPostReplyService.list(new QueryWrapper<YtPostReply>().eq("post_id", postId));
        YtPost ytPost = ytPostService.getOne(new QueryWrapper<YtPost>().eq("id", postId));
        List<YtPostReplyVo> ytPostReplyVos = new ArrayList<>();
        YtPostReply ytPostReply = new YtPostReply();
        ytPostReply.setId(0);
        ytPostReply.setContent(ytPost.getContent());
        ytPostReply.setcTime(ytPost.getcTime());
        ytPostReply.setUserId(ytPost.getUserId());
        lsit.add(ytPostReply);
        List<Integer> collect = lsit.stream().map(YtPostReply::getUserId).collect(Collectors.toList());
        Map<Integer, String> user = ytUserService.list(new QueryWrapper<YtUser>().in("user_id", collect))
                .stream().collect(Collectors.toMap(YtUser::getUserId,YtUser::getUserName));
        lsit.forEach(item->{
            YtPostReplyVo ytPostVo = new YtPostReplyVo();
            BeanUtils.copyProperties(item,ytPostVo);
            ytPostVo.setUserId(user.get(item.getUserId()));
            ytPostReplyVos.add(ytPostVo);
        });
        return new ResponseJson(200,null,ytPostReplyVos);

    }

    /**
     * 添加回复
     * @param ytPostReply
     * @return
     */
    @PostMapping("/add")
    public ResponseJson add(@RequestBody YtPostReply ytPostReply){
        ytPostReplyService.save(ytPostReply);
        return new ResponseJson(200,null,1);
    }

    /**
     * 获取回复某人的帖子的回复list
     * @param id
     * @return
     */
    @GetMapping("/listReply")
    public ResponseJson listReply(@RequestParam int id){
        List<YtPost> ytPosts = ytPostService.list(new QueryWrapper<YtPost>().eq("user_id", id));
        List<Integer> ids = ytPosts.stream().map(YtPost::getId).collect(Collectors.toList());
        List<YtPostReply> ytPostReplies = ytPostReplyService.list(new QueryWrapper<YtPostReply>().in("post_id", ids));
        return new ResponseJson(200,null,ytPostReplies);
    }

}

