package yutong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class YtReplyVo {
    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发帖人
     */
    private String user;

    /**
     * 回复级别0是发帖1 是第一个回复
     */
    private Integer level;

    /**
     * 被回复的人
     */
    private String replyTo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 游戏id
     */
    private Integer gameId;

    private int replyToReplyId;

    private String target ;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public int getReplyToReplyId() {
        return replyToReplyId;
    }

    public void setReplyToReplyId(int replyToReplyId) {
        this.replyToReplyId = replyToReplyId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
