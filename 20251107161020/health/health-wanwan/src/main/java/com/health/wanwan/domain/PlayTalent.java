package com.health.wanwan.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 陪玩专区-人才库对象 play_talent
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class PlayTalent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人才ID */
    private Long talentId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 陪玩昵称 */
    @Excel(name = "陪玩昵称")
    private String nickname;

    /** 个人形象照片URL */
    @Excel(name = "个人形象照片URL")
    private String avatar;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 声音样本文件URL (吸引用户的个人介绍) */
    @Excel(name = "声音样本文件URL (吸引用户的个人介绍)")
    private String voiceSampleUrl;

    /** 个人标签 (如：话痨, 搞笑, 技术流, 逗号分隔) */
    @Excel(name = "个人标签 (如：话痨, 搞笑, 技术流, 逗号分隔)")
    private String profileTags;

    /** 时薪 (单位: 元/小时) */
    @Excel(name = "时薪 (单位: 元/小时)")
    private BigDecimal hourlyRate;

    /** 局薪 (单位: 元/局) */
    @Excel(name = "局薪 (单位: 元/局)")
    private BigDecimal perGameRate;

    /** 综合评分 (1.00-5.00) */
    @Excel(name = "综合评分 (1.00-5.00)")
    private BigDecimal averageRating;

    /** 累计完成订单数 */
    @Excel(name = "累计完成订单数")
    private Long totalOrdersCompleted;

    /** 在线状态 (0=离线, 1=在线空闲, 2=服务中) */
    @Excel(name = "在线状态 (0=离线, 1=在线空闲, 2=服务中)")
    private String onlineStatus;

    /** 删除标志 (0=存在, 1=删除) */
    private String delFlag;

    public void setTalentId(Long talentId) 
    {
        this.talentId = talentId;
    }

    public Long getTalentId() 
    {
        return talentId;
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

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }

    public void setVoiceSampleUrl(String voiceSampleUrl) 
    {
        this.voiceSampleUrl = voiceSampleUrl;
    }

    public String getVoiceSampleUrl() 
    {
        return voiceSampleUrl;
    }

    public void setProfileTags(String profileTags) 
    {
        this.profileTags = profileTags;
    }

    public String getProfileTags() 
    {
        return profileTags;
    }

    public void setHourlyRate(BigDecimal hourlyRate) 
    {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getHourlyRate() 
    {
        return hourlyRate;
    }

    public void setPerGameRate(BigDecimal perGameRate) 
    {
        this.perGameRate = perGameRate;
    }

    public BigDecimal getPerGameRate() 
    {
        return perGameRate;
    }

    public void setAverageRating(BigDecimal averageRating) 
    {
        this.averageRating = averageRating;
    }

    public BigDecimal getAverageRating() 
    {
        return averageRating;
    }

    public void setTotalOrdersCompleted(Long totalOrdersCompleted) 
    {
        this.totalOrdersCompleted = totalOrdersCompleted;
    }

    public Long getTotalOrdersCompleted() 
    {
        return totalOrdersCompleted;
    }

    public void setOnlineStatus(String onlineStatus) 
    {
        this.onlineStatus = onlineStatus;
    }

    public String getOnlineStatus() 
    {
        return onlineStatus;
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
            .append("talentId", getTalentId())
            .append("userId", getUserId())
            .append("nickname", getNickname())
            .append("avatar", getAvatar())
            .append("gender", getGender())
            .append("voiceSampleUrl", getVoiceSampleUrl())
            .append("profileTags", getProfileTags())
            .append("hourlyRate", getHourlyRate())
            .append("perGameRate", getPerGameRate())
            .append("averageRating", getAverageRating())
            .append("totalOrdersCompleted", getTotalOrdersCompleted())
            .append("onlineStatus", getOnlineStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
