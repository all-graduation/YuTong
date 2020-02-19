package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtPost;
import yutong.entity.YtPostVo;
import yutong.entity.YtUser;
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
@RequestMapping("/ytPost")
public class YtPostController {

    @Autowired
    YtPostService ytPostService;

    @Autowired
    YtUserService ytUserService;

    /**
     * 查询帖子列表
     * @return
     */
    @GetMapping("/list")
    public ResponseJson list(){
        List<YtPost> list = ytPostService.list();
        if(list.isEmpty()){
            return new ResponseJson(200,null,null);
        }
        List<YtPostVo> ytPostVos = new ArrayList<>();
        List<Integer> collect = list.stream().map(YtPost::getUserId).collect(Collectors.toList());
        Map<Integer, String> user = ytUserService.list(new QueryWrapper<YtUser>().in("user_id", collect))
                .stream().collect(Collectors.toMap(YtUser::getUserId,YtUser::getUserName));
        list.forEach(item->{
            YtPostVo ytPostVo = new YtPostVo();
            BeanUtils.copyProperties(item,ytPostVo);
            ytPostVo.setUserId(user.get(item.getUserId()));
            ytPostVos.add(ytPostVo);
        });
        return new ResponseJson(200,null,ytPostVos);
    }

    /**
     * 查询一个帖子
     * @return
     */
    @GetMapping("/one")
    public ResponseJson one(@RequestParam int id){
        YtPost ytPost = ytPostService.getOne(new QueryWrapper<YtPost>().eq("id",id));
        YtUser user = ytUserService.getOne(new QueryWrapper<YtUser>().eq("user_id", ytPost.getUserId()));
            YtPostVo ytPostVo = new YtPostVo();
            BeanUtils.copyProperties(ytPost,ytPostVo);
            ytPostVo.setUserId(user.getUserName());

        return new ResponseJson(200,null,ytPost);
    }

    /**
     * 增加一个帖子
     * @return
     */
    @PostMapping("/add")
    public ResponseJson add(@RequestBody YtPost ytPost){
        ytPostService.save(ytPost);
        return new ResponseJson(200,null,ytPost);
    }
}

