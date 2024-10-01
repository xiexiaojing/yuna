/*
 * create by mybatis-plus-generator  https://github.com/xiweile
 */
package com.brmayi.yuna.ai.aiOps.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("brmayi_schedule")
public class BrmayiScheduleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("time")
    private LocalDateTime time;

    @TableField("event")
    private String event;

    @TableField("phones")
    private String phones;

    @TableField("comment")
    private String comment;

    /**
     * 关联brmayi_event_content表的type字段
     */
    @TableField("event_type")
    private String eventType;


    public static final String ID = "id";

    public static final String TIME = "time";

    public static final String EVENT = "event";

    public static final String PHONES = "phones";

    public static final String COMMENT = "comment";

    public static final String EVENT_TYPE = "event_type";

}
