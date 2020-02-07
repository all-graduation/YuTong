package yutong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
public class YtOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 购买的游戏id
     */
    private Integer gameId;

    /**
     * 购买用户id
     */
    private Integer userId;

    /**
     * 购买时间
     */
    private Date createTime;

    /**
     * 订单状态（0 未支付 1 已支付 2 已删除）
     */
    private Integer status;

    /**
     * 金额
     */
    private Double money;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "YtOrder{" +
        "id=" + id +
        ", gameId=" + gameId +
        ", userId=" + userId +
        ", createTime=" + createTime +
        ", status=" + status +
        ", money=" + money +
        "}";
    }
}
