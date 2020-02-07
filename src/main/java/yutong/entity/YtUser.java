package yutong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 于童
 * @since 2020-02-07
 */
public class YtUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 名字
     */
    private String userName;

    /**
     * 电话
     */
    private String userPhone;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 0 管理员 1 普通用户 2会员
     */
    private Boolean type;

    /**
     * 标签id
     */
    private Integer target;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "YtUser{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", userPhone=" + userPhone +
        ", userPassword=" + userPassword +
        ", type=" + type +
        ", target=" + target +
        "}";
    }
}
