package com.health.hanghang.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 护航专区-任务对象 escort_mission
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class EscortMission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long missionId;

    /** 业务任务编号 */
    @Excel(name = "业务任务编号")
    private String missionSn;

    /** 发布任务的客户ID (老板) */
    @Excel(name = "发布任务的客户ID (老板)")
    private Long publisherId;

    /** 接取任务的专家ID (悬赏时可为空) */
    @Excel(name = "接取任务的专家ID (悬赏时可为空)")
    private Long specialistId;

    /** 任务状态 (10=悬赏中, 20=已接取/进行中, 30=待验收, 40=已完成, 50=已失败, 60=已取消) */
    @Excel(name = "任务状态 (10=悬赏中, 20=已接取/进行中, 30=待验收, 40=已完成, 50=已失败, 60=已取消)")
    private String missionStatus;

    /** 目标地图 (字典项) */
    @Excel(name = "目标地图 (字典项)")
    private String gameMap;

    /** 核心目标描述 (如：从电视台安全撤离) */
    @Excel(name = "核心目标描述 (如：从电视台安全撤离)")
    private String objective;

    /** 保底物资价值 */
    @Excel(name = "保底物资价值")
    private Long guaranteedAssetValue;

    /** 任务酬劳 */
    @Excel(name = "任务酬劳")
    private BigDecimal rewardAmount;

    /** 已支付定金 */
    @Excel(name = "已支付定金")
    private BigDecimal depositAmount;

    /** 任务最晚完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务最晚完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /** 删除标志 (0=存在, 1=删除) */
    private String delFlag;

    public void setMissionId(Long missionId) 
    {
        this.missionId = missionId;
    }

    public Long getMissionId() 
    {
        return missionId;
    }

    public void setMissionSn(String missionSn) 
    {
        this.missionSn = missionSn;
    }

    public String getMissionSn() 
    {
        return missionSn;
    }

    public void setPublisherId(Long publisherId) 
    {
        this.publisherId = publisherId;
    }

    public Long getPublisherId() 
    {
        return publisherId;
    }

    public void setSpecialistId(Long specialistId) 
    {
        this.specialistId = specialistId;
    }

    public Long getSpecialistId() 
    {
        return specialistId;
    }

    public void setMissionStatus(String missionStatus) 
    {
        this.missionStatus = missionStatus;
    }

    public String getMissionStatus() 
    {
        return missionStatus;
    }

    public void setGameMap(String gameMap) 
    {
        this.gameMap = gameMap;
    }

    public String getGameMap() 
    {
        return gameMap;
    }

    public void setObjective(String objective) 
    {
        this.objective = objective;
    }

    public String getObjective() 
    {
        return objective;
    }

    public void setGuaranteedAssetValue(Long guaranteedAssetValue) 
    {
        this.guaranteedAssetValue = guaranteedAssetValue;
    }

    public Long getGuaranteedAssetValue() 
    {
        return guaranteedAssetValue;
    }

    public void setRewardAmount(BigDecimal rewardAmount) 
    {
        this.rewardAmount = rewardAmount;
    }

    public BigDecimal getRewardAmount() 
    {
        return rewardAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) 
    {
        this.depositAmount = depositAmount;
    }

    public BigDecimal getDepositAmount() 
    {
        return depositAmount;
    }

    public void setDeadline(Date deadline) 
    {
        this.deadline = deadline;
    }

    public Date getDeadline() 
    {
        return deadline;
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
            .append("missionId", getMissionId())
            .append("missionSn", getMissionSn())
            .append("publisherId", getPublisherId())
            .append("specialistId", getSpecialistId())
            .append("missionStatus", getMissionStatus())
            .append("gameMap", getGameMap())
            .append("objective", getObjective())
            .append("guaranteedAssetValue", getGuaranteedAssetValue())
            .append("rewardAmount", getRewardAmount())
            .append("depositAmount", getDepositAmount())
            .append("deadline", getDeadline())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
