package com.health.shetuan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 资源预约对象 t_resource_booking
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class TResourceBooking extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 预约ID */
    private Long bookingId;

    /** 预约资源ID */
    @Excel(name = "预约资源ID")
    private Long resourceId;

    /** 关联活动ID (可选) */
    @Excel(name = "关联活动ID (可选)")
    private Long activityId;

    /** 预约人ID (关联 sys_user) */
    @Excel(name = "预约人ID (关联 sys_user)")
    private Long userId;

    /** 预约开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 预约结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 预约数量（仅物资类） */
    @Excel(name = "预约数量", readConverterExp = "仅=物资类")
    private Long bookingQuantity;

    /** 预约状态 (0:待审, 1:通过, 2:驳回) */
    @Excel(name = "预约状态 (0:待审, 1:通过, 2:驳回)")
    private String status;

    /** 删除标志 */
    private String delFlag;

    public void setBookingId(Long bookingId) 
    {
        this.bookingId = bookingId;
    }

    public Long getBookingId() 
    {
        return bookingId;
    }

    public void setResourceId(Long resourceId) 
    {
        this.resourceId = resourceId;
    }

    public Long getResourceId() 
    {
        return resourceId;
    }

    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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

    public void setBookingQuantity(Long bookingQuantity) 
    {
        this.bookingQuantity = bookingQuantity;
    }

    public Long getBookingQuantity() 
    {
        return bookingQuantity;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("bookingId", getBookingId())
            .append("resourceId", getResourceId())
            .append("activityId", getActivityId())
            .append("userId", getUserId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("bookingQuantity", getBookingQuantity())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
