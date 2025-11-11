package com.health.hanghang.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 护航专区-打手对象 escort_specialist
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class EscortSpecialist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 打手ID */
    private Long specialistId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 打手代号 */
    @Excel(name = "打手代号")
    private String nickname;

    /** 个人形象照片URL */
    @Excel(name = "个人形象照片URL")
    private String avatar;

    /** 游戏段位/等级 */
    @Excel(name = "游戏段位/等级")
    private String gameRank;

    /** 任务成功率 (%) */
    @Excel(name = "任务成功率 (%)")
    private BigDecimal successRate;

    /** 场均带出资产价值 */
    @Excel(name = "场均带出资产价值")
    private Long avgAssetValue;

    /** 擅长领域 (如: 电视台专家, 北山指挥官, 跑刀王, 逗号分隔) */
    @Excel(name = "擅长领域 (如: 电视台专家, 北山指挥官, 跑刀王, 逗号分隔)")
    private String specialties;

    /** 个人简介 (展示战绩和能力) */
    @Excel(name = "个人简介 (展示战绩和能力)")
    private String profileBio;

    /** 接单所需定金比例 (%) */
    @Excel(name = "接单所需定金比例 (%)")
    private BigDecimal depositRatio;

    /** 状态 (0=可接单, 1=任务中, 2=休息) */
    @Excel(name = "状态 (0=可接单, 1=任务中, 2=休息)")
    private String status;

    /** 删除标志 (0=存在, 1=删除) */
    private String delFlag;

    public void setSpecialistId(Long specialistId) 
    {
        this.specialistId = specialistId;
    }

    public Long getSpecialistId() 
    {
        return specialistId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }

    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }

    public void setGameRank(String gameRank) 
    {
        this.gameRank = gameRank;
    }

    public String getGameRank() 
    {
        return gameRank;
    }

    public void setSuccessRate(BigDecimal successRate) 
    {
        this.successRate = successRate;
    }

    public BigDecimal getSuccessRate() 
    {
        return successRate;
    }

    public void setAvgAssetValue(Long avgAssetValue) 
    {
        this.avgAssetValue = avgAssetValue;
    }

    public Long getAvgAssetValue() 
    {
        return avgAssetValue;
    }

    public void setSpecialties(String specialties) 
    {
        this.specialties = specialties;
    }

    public String getSpecialties() 
    {
        return specialties;
    }

    public void setProfileBio(String profileBio) 
    {
        this.profileBio = profileBio;
    }

    public String getProfileBio() 
    {
        return profileBio;
    }

    public void setDepositRatio(BigDecimal depositRatio) 
    {
        this.depositRatio = depositRatio;
    }

    public BigDecimal getDepositRatio() 
    {
        return depositRatio;
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
            .append("specialistId", getSpecialistId())
            .append("userId", getUserId())
            .append("nickname", getNickname())
            .append("avatar", getAvatar())
            .append("gameRank", getGameRank())
            .append("successRate", getSuccessRate())
            .append("avgAssetValue", getAvgAssetValue())
            .append("specialties", getSpecialties())
            .append("profileBio", getProfileBio())
            .append("depositRatio", getDepositRatio())
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
