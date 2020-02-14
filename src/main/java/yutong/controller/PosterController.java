package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtPoster;
import yutong.service.YtPosterService;
import yutong.util.ResponseJson;

/**
 * <p>
 *  广告模块
 * </p>
 *
 * @author 于童
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/poster")
public class PosterController {

    @Autowired
    YtPosterService ytPosterService;

    /**
     * 修改一个广告
     * @param ytPoster
     * @return
     */
    @PutMapping("/poster")
    public ResponseJson addPoster(@RequestBody YtPoster ytPoster){
        boolean save = ytPosterService.update(new UpdateWrapper<YtPoster>().eq("id",ytPoster.getId()).set("image",ytPoster.getImage()));
        return new ResponseJson(200,null,save);
    }

    /**
     * 删除一个广告通过id
     */
    @DeleteMapping("/poster")
    public ResponseJson removePoster(@RequestBody YtPoster ytPoster){
        boolean save = ytPosterService.remove(new QueryWrapper<YtPoster>().eq("id",ytPoster.getId()));
        return new ResponseJson(200,null,save);
    }

    /**
     * 查询一个广告通过id
     */
    @GetMapping("/poster")
    public ResponseJson getOne(@RequestBody YtPoster ytPoster){
        YtPoster save = ytPosterService.getOne(new QueryWrapper<YtPoster>().eq("id",ytPoster.getId()));
        return new ResponseJson(200,null,save);
    }

    /**
     * 查询广告list
     */
    @GetMapping("/list")
    public ResponseJson list(){
        return new ResponseJson(200,null,ytPosterService.list());
    }
}

