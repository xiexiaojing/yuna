/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package com.brmayi.yuna.ai.aiOps.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2024-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("brmayi_event_content")
public class BrmayiEventContentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableId(value = "task_id", type = IdType.AUTO)
    private Long taskId;

    /**
     * 例如：上线准备、上线前通知
     */
    @TableField("type")
    private String type;

    /**
     * 例如：基础上线准备
     */
    @TableField("category")
    private String category;

    /**
     * 例如：SQL变更
     */
    @TableField("context")
    private String context;

    /**
     * 例如：具体的SQL语句
     */
    @TableField("comment")
    private String comment;

    /**
     * 值包含0:未完成,1:已完成
     */
    @TableField("check_status")
    private Integer checkStatus;


    public static final String TASK_ID = "task_id";

    public static final String TYPE = "type";

    public static final String CATEGORY = "category";

    public static final String CONTEXT = "context";

    public static final String COMMENT = "comment";

    public static final String CHECK_STATUS = "check_status";

}
