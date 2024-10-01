package com.brmayi.yuna.ai.aiOps.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.brmayi.yuna.ai.aiOps.AiOpsController;
import com.brmayi.yuna.ai.aiOps.constant.CheckStatusEnum;
import com.brmayi.yuna.ai.aiOps.infrastructure.entity.BrmayiEventContentEntity;
import com.brmayi.yuna.ai.aiOps.infrastructure.entity.BrmayiScheduleEntity;
import com.brmayi.yuna.ai.aiOps.infrastructure.mapper.BrmayiEventContentMapper;
import com.brmayi.yuna.ai.aiOps.infrastructure.mapper.BrmayiScheduleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {
    @Resource
    private BrmayiScheduleMapper brmayiScheduleMapper;
    @Resource
    private BrmayiEventContentMapper brmayiEventContentMapper;
    @Resource
    private AlertService alertService;

    @Scheduled(cron = "40 0/15 10-21 * * ?") // 每天从10点到21点，每小时整点执行
    public void checkSchedules() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fiveMinutesLater = now.plusMinutes(5);

        QueryWrapper<BrmayiScheduleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.between(BrmayiScheduleEntity.TIME, now, fiveMinutesLater);
        List<BrmayiScheduleEntity> schedules = brmayiScheduleMapper.selectList(queryWrapper);
        for (BrmayiScheduleEntity schedule : schedules) {
            String msg = "您的日程显示在" + DateUtil.format(now, "yyyy年MM月dd日") + schedule.getEvent();
            if (StringUtils.isNotEmpty(schedule.getComment())) {
                msg += "\n备注：" + schedule.getComment();
            }
            if (StringUtils.isNotEmpty(schedule.getEventType())) {
                msg += "\n--------以下检查项目需要进行--------";
                QueryWrapper<BrmayiEventContentEntity> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq(BrmayiEventContentEntity.TYPE, schedule.getEventType());
                List<BrmayiEventContentEntity> eventContents = brmayiEventContentMapper.selectList(queryWrapper1);
                for (BrmayiEventContentEntity eventContent : eventContents) {
                    boolean isFinished = true;
                    if (CheckStatusEnum.UNFINISHED.getCode() == eventContent.getCheckStatus()) {
                        AiOpsController.userText = "如果我仅仅输入数字且输入数字匹配" + eventContent.getTaskId() + "则执行sql[UPDATE `brmayi_event_content` SET check_status='' WHERE task_id=输入数字]，给我回复内容为对应的内容已完成";
                    }
                    msg += "\n任务ID" + eventContent.getTaskId() + "  " + eventContent.getContext() + "  " + (isFinished?"已完成√":"未完成X") ;
                }
            }
            alertService.sendWarning(msg, schedule.getPhones());
        }
    }
}
