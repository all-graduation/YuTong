package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtTarget;
import yutong.entity.YtUser;
import yutong.entity.YtUserInfo;
import yutong.service.YtTargetService;
import yutong.service.YtUserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    YtUserService userService;

    @Autowired
    YtTargetService ytTargetService;
    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseJson login(@RequestBody YtUser ytUser){
        YtUser user = userService.getOne(new QueryWrapper<YtUser>()
                .eq("user_phone", ytUser.getUserPhone())
                .eq("user_password", ytUser.getUserPassword()));
        if(user==null){
            return new ResponseJson(200,"没有用户",null);
        }
        return new ResponseJson(200,null,user);
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/signUp")
    public ResponseJson signUp(@RequestBody YtUser ytUser){
        userService.save(ytUser);
        return new ResponseJson(200,null,ytUser);
    }

    /**
     * 修改个人信息
     * @return
     */
    @PutMapping("/edit")
    public ResponseJson edit(@RequestBody YtUser ytUser){
        userService.update(new UpdateWrapper<YtUser>()
                .eq("user_id",ytUser.getUserId())
                .set(ytUser.getUserName()!=null&& !ytUser.getUserName().equals(""),"user_name",ytUser.getUserName())
                .set(ytUser.getUserPassword()!=null&& !ytUser.getUserPassword().equals(""),"user_password",ytUser.getUserPassword())
                .set(ytUser.getUserPhone()!=null&& !ytUser.getUserPhone().equals(""),"user_phone",ytUser.getUserPhone()));
        return new ResponseJson(200,null,ytUser);
    }

    /**
     * 获取用户信息
     */
    @PostMapping("userInfo")
    public ResponseJson getUserInfo(@RequestBody YtUser ytUser){
        YtUser user = userService.getOne(new QueryWrapper<YtUser>()
                .eq("user_id", ytUser.getUserId()));
        //因为查出来的user是YTUser类型，其中target是id 所以需要转换成返回类型，并且赋值
        YtUserInfo ytUserInfo = new YtUserInfo();
        BeanUtils.copyProperties(user,ytUserInfo);
        YtTarget target_id = ytTargetService.getOne(new QueryWrapper<YtTarget>().eq("target_id", user.getTarget()));
        ytUserInfo.setTarget(target_id.getTargetName());
        if(user==null){
            return new ResponseJson(200,"没有用户",null);
        }
        return new ResponseJson(200,null,ytUserInfo);
    }

}

