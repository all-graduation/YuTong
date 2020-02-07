package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtTarget;
import yutong.service.YtTargetService;
import yutong.util.ResponseJson;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 于童
 * @since 2020-02-07
 */
@RestController
@RequestMapping("/target")
public class TargetController {

    @Autowired
    YtTargetService ytTargetService;

    /**
     * 增加一个标签
     * @param ytTarget
     * @return
     */
    @PostMapping("/target")
    public ResponseJson add(@RequestBody YtTarget ytTarget){
        boolean save = ytTargetService.save(ytTarget);
        return new ResponseJson(200,null,save);
    }

    /**
     * 删除一个标签
     * @param ytTarget
     * @return
     */
    @DeleteMapping("/target")
    public ResponseJson delete(@RequestBody YtTarget ytTarget){
        boolean remove = ytTargetService.remove(new QueryWrapper<YtTarget>().eq("target_id  ",ytTarget.getTargetId()));
        return new ResponseJson(200,null,remove);
    }

    /**
     * 根据id查询一个标签
     * @param ytTarget
     * @return
     */
    @GetMapping("/target")
    public ResponseJson getOne(@RequestBody YtTarget ytTarget){
        YtTarget target_id = ytTargetService.getOne(new QueryWrapper<YtTarget>().eq("target_id", ytTarget.getTargetId()));
        return new ResponseJson(200,null,target_id);
    }

    /**
     * 查询所有标签list
     * @return
     */
    @GetMapping("/list")
    public ResponseJson list(){
        return new ResponseJson(200,null,ytTargetService.list());
    }

}

