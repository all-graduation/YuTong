package yutong.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 于童
 * @since 2020-02-07
 */
public class YtGame implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 游戏id
     */
    private Integer gameId;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 游戏地址
     */
    private String gameUrl;

    /**
     * 销量
     */
    private Integer butCount;

    /**
     * 状态（0 下架 1 上架中）
     */
    private Boolean status;

    /**
     * 制作人id
     */
    private Integer maker;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 价格
     */
    private Double money;


    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public Integer getButCount() {
        return butCount;
    }

    public void setButCount(Integer butCount) {
        this.butCount = butCount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getMaker() {
        return maker;
    }

    public void setMaker(Integer maker) {
        this.maker = maker;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "YtGame{" +
        "gameId=" + gameId +
        ", gameName=" + gameName +
        ", gameUrl=" + gameUrl +
        ", butCount=" + butCount +
        ", status=" + status +
        ", maker=" + maker +
        ", createTime=" + createTime +
        ", money=" + money +
        "}";
    }
}
