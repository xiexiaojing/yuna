package com.brmayi.yuna.ai.aiOps.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.brmayi.yuna.ai.aiOps.AiOpsController;
import com.brmayi.yuna.ai.aiOps.constant.CheckStatusEnum;
import com.brmayi.yuna.ai.aiOps.infrastructure.entity.BrmayiEventContentEntity;
import com.brmayi.yuna.ai.aiOps.infrastructure.entity.BrmayiScheduleEntity;
import com.brmayi.yuna.ai.aiOps.infrastructure.mapper.BrmayiEventContentMapper;
import com.brmayi.yuna.ai.aiOps.infrastructure.mapper.BrmayiScheduleMapper;
import dev.langchain4j.agent.tool.Tool;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AiOpsTools {
    @Resource
    private BrmayiScheduleMapper brmayiScheduleMapper;
    @Resource
    private BrmayiEventContentMapper brmayiEventContentMapper;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Tool("今天我有什么安排？")
    public String todayArrange() {
        return arrange(LocalDateTime.now());
    }

    @Tool("明天我有什么安排？")
    public String tomorrowArrange() {
        return arrange(LocalDateTime.now().plusDays(1));
    }

    @Tool("后天我有什么安排？")
    public String theDayAfterTomorrowArrange() {
        return arrange(LocalDateTime.now().plusDays(2));
    }

    @Tool("请为我生成日程、执行更新SQL工具")
    public String updateDB(String sql) {
        try {
            if(StringUtils.isEmpty(sql)) {
                return "SQL为空";
            }
            return "共影响"+jdbcTemplate.update(sql)+"条数据";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private String arrange(LocalDateTime date) {
        date = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime oneDayLater = date.plusDays(1);
        QueryWrapper<BrmayiScheduleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.between(BrmayiScheduleEntity.TIME, date, oneDayLater);
        List<BrmayiScheduleEntity> schedules = brmayiScheduleMapper.selectList(queryWrapper);
        String userText = CollUtil.isEmpty(schedules) ? "没有代办事项，好好享受" + DateUtil.format(date, "E") + "吧！" : "日程显示待办事项如下：";
        for (BrmayiScheduleEntity schedule : schedules) {
            userText += schedule.getEvent();
            if (StringUtils.isNotEmpty(schedule.getComment())) {
                userText += "备注：" + schedule.getComment();
            }
            if (StringUtils.isNotEmpty(schedule.getEventType())) {
                userText += "please return in table format:<br/>";
                QueryWrapper<BrmayiEventContentEntity> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq(BrmayiEventContentEntity.TYPE, schedule.getEventType());
                List<BrmayiEventContentEntity> eventContents = brmayiEventContentMapper.selectList(queryWrapper1);

                for (BrmayiEventContentEntity eventContent : eventContents) {
                    boolean isFinished = true;
                    if (CheckStatusEnum.UNFINISHED.getCode() == eventContent.getCheckStatus()) {
                        isFinished = false;
                        AiOpsController.userText = "如果我仅仅输入数字且输入数字匹配" + eventContent.getTaskId() + "则执行sql[UPDATE `brmayi_event_content` SET check_status='' WHERE task_id=输入数字]，给我回复内容为对应的内容已完成";
                    }
                    userText += "<br/>任务ID很重要，一定要展示：任务ID为" + eventContent.getTaskId()
                            + "  " + eventContent.getContext()
                            + "  (状态为" + (isFinished?"已完成":"未完成") + ")";
                }
                if (StringUtils.isEmpty(AiOpsController.userText)) {
                    userText += "该检查项目已经全部完成";
                } else {
                    userText += "项目完成后您输入对应的任务ID我会为您标记状态。";
                }
            }
        }
        return userText;
    }
}
