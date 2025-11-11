package com.health.shetuan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 活动信息对象 t_activity_info
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class TActivityInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    private Long activityId;

    /** 所属社团ID */
    @Excel(name = "所属社团ID")
    private Long clubId;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String activityName;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 活动地点描述 */
    @Excel(name = "活动地点描述")
    private String location;

    /** 审批状态 (0:待审, 1:通过, 2:驳回) */
    @Excel(name = "审批状态 (0:待审, 1:通过, 2:驳回)")
    private String status;

    /** 当前审批人ID (关联 sys_user) */
    @Excel(name = "当前审批人ID (关联 sys_user)")
    private Long approvalUserId;

    /** 最大参与人数 */
    @Excel(name = "最大参与人数")
    private Long maxParticipants;

    /** 实际参与人数 */
    @Excel(name = "实际参与人数")
    private Long actualParticipants;

    /** 删除标志 */
    private String delFlag;

    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }

    public void setClubId(Long clubId) 
    {
        this.clubId = clubId;
    }

    public Long getClubId() 
    {
        return clubId;
    }

    public void setActivityName(String activityName) 
    {
        this.activityName = activityName;
    }

    public String getActivityName() 
    {
        return activityName;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setApprovalUserId(Long approvalUserId) 
    {
        this.approvalUserId = approvalUserId;
    }

    public Long getApprovalUserId() 
    {
        return approvalUserId;
    }

    public void setMaxParticipants(Long maxParticipants) 
    {
        this.maxParticipants = maxParticipants;
    }

    public Long getMaxParticipants() 
    {
        return maxParticipants;
    }

    public void setActualParticipants(Long actualParticipants) 
    {
        this.actualParticipants = actualParticipants;
    }

    public Long getActualParticipants() 
    {
        return actualParticipants;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("activityId", getActivityId())
            .append("clubId", getClubId())
            .append("activityName", getActivityName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("location", getLocation())
            .append("status", getStatus())
            .append("approvalUserId", getApprovalUserId())
            .append("maxParticipants", getMaxParticipants())
            .append("actualParticipants", getActualParticipants())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
