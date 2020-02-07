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
public class YtTarget implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "target_id", type = IdType.AUTO)
    private Integer targetId;

    /**
     * 标签属性
     */
    private String targetName;


    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    @Override
    public String toString() {
        return "YtTarget{" +
        "targetId=" + targetId +
        ", targetName=" + targetName +
        "}";
    }
}
