package yutong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yutong.entity.YtUser;
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

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResponseJson login(@RequestBody YtUser ytUser){
        YtUser user = userService.getOne(new QueryWrapper<YtUser>()
                .eq("user_phone", ytUser.getUserPhone())
                .eq("user_password", ytUser.getUserPassword()));
        System.out.println(user);
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
                .set("user_name",ytUser.getUserName())
                .set("user_phone",ytUser.getUserPhone()));
        return new ResponseJson(200,null,ytUser);
    }

    /**
     * 修改密码
     */
    @PutMapping("editPassword")
    public ResponseJson editPassword(@RequestBody YtUser ytUser){
        userService.update(new UpdateWrapper<YtUser>()
                .eq("user_id",ytUser.getUserId())
                .set("user_password",ytUser.getUserPassword()));
        return new ResponseJson(200,null,ytUser);
    }

}

