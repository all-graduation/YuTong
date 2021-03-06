package yutong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 于童
 * @since 2020-02-10
 */
public class YtReply implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发帖人id
     */
    private Integer userId;

    /**
     * 回复级别0是发帖1 是第一个回复
     */
    private Integer level;

    /**
     * 被回复的人的id
     */
    private Integer replyToId;

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

    private Integer replyToReplyId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(Integer replyToId) {
        this.replyToId = replyToId;
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

    public Integer getReplyToReplyId() {
        return replyToReplyId;
    }

    public void setReplyToReplyId(Integer replyToReplyId) {
        this.replyToReplyId = replyToReplyId;
    }
}
