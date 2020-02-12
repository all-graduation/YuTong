package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtReply;
import yutong.entity.YtReplyVo;
import yutong.entity.YtTarget;
import yutong.entity.YtUser;
import yutong.service.YtReplyService;
import yutong.service.YtTargetService;
import yutong.service.YtUserService;
import yutong.util.ResponseJson;

import java.util.*;
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
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    YtReplyService ytReplyService;

    @Autowired
    YtUserService ytUserService;

    @Autowired
    YtTargetService ytTargetService;

    /**
     * 查询
     * @return
     */
    @GetMapping("list")
    public ResponseJson list(@RequestParam int id){
        List<YtReply> game_id = ytReplyService.list(new QueryWrapper<YtReply>().eq("game_id", id));
        if(game_id.isEmpty()){
            return new ResponseJson(200,null,null);
        }
        List<Integer> userIds = game_id.stream().map(YtReply::getUserId).collect(Collectors.toList());
        //查询用户姓名
        List<YtUser> users = ytUserService.list(new QueryWrapper<YtUser>().in("user_id", userIds));
        //取出用户的id name 的map
        Map<Integer, String> userNameMap = users.stream().collect(Collectors.toMap(YtUser::getUserId, YtUser::getUserName));

        //获取target
        List<YtTarget> target_id = ytTargetService.list();
        Map<Integer, String> collect = target_id.stream().collect(Collectors.toMap(YtTarget::getTargetId, YtTarget::getTargetName));
        HashMap<Integer,String> map = new HashMap<>();
        users.forEach(item->{
            map.put(item.getUserId(),collect.get(item.getTarget()));
        });
        //构建返回列表，需要把id换成名字
        List<YtReplyVo> replyVos = new ArrayList<>();
        for (YtReply i : game_id) {
            YtReplyVo ytReplyVo = new YtReplyVo();
            BeanUtils.copyProperties(i,ytReplyVo);
            ytReplyVo.setUser(userNameMap.get(i.getUserId()));
            ytReplyVo.setReplyTo(userNameMap.get(i.getReplyToId()));
            ytReplyVo.setTarget(collect.get(i.getUserId()));
            replyVos.add(ytReplyVo);
        }

        //先按照第一个人评论排序，让回复在底下。再按照时间去排序
        replyVos = replyVos.stream().sorted(Comparator.comparing(YtReplyVo::getReplyToReplyId))
                         .sorted(Comparator.comparing(YtReplyVo::getCreateTime)).collect(Collectors.toList());
        return new ResponseJson(200,null,replyVos);
    }

    /**
     * 增加评论
     * @param ytReply
     * @return
     */
    @PostMapping("add")
    public ResponseJson add(@RequestBody YtReply ytReply){
        //查询爸爸评论
        YtReply reply = ytReplyService.getOne(new QueryWrapper<YtReply>().eq("id", ytReply.getReplyToReplyId()));
        if(reply==null){
            //是评论游戏
            ytReply.setReplyToId(0);
        }else {
            ytReply.setReplyToId(reply.getUserId());
        }
        ytReply.setLevel(0);

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

