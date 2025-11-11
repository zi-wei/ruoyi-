package com.health.shetuan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 社团信息对象 t_club_info
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class TClubInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 社团ID */
    private Long clubId;

    /** 社团名称 */
    @Excel(name = "社团名称")
    private String clubName;

    /** 社团类型（字典值） */
    @Excel(name = "社团类型", readConverterExp = "字=典值")
    private String clubType;

    /** 负责人用户ID (关联 sys_user) */
    @Excel(name = "负责人用户ID (关联 sys_user)")
    private Long leaderId;

    /** 社团简介 */
    @Excel(name = "社团简介")
    private String description;

    /** 成立日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成立日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date foundDate;

    /** 社团状态 (0:正常, 1:冻结) */
    @Excel(name = "社团状态 (0:正常, 1:冻结)")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setClubId(Long clubId) 
    {
        this.clubId = clubId;
    }

    public Long getClubId() 
    {
        return clubId;
    }

    public void setClubName(String clubName) 
    {
        this.clubName = clubName;
    }

    public String getClubName() 
    {
        return clubName;
    }

    public void setClubType(String clubType) 
    {
        this.clubType = clubType;
    }

    public String getClubType() 
    {
        return clubType;
    }

    public void setLeaderId(Long leaderId) 
    {
        this.leaderId = leaderId;
    }

    public Long getLeaderId() 
    {
        return leaderId;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setFoundDate(Date foundDate) 
    {
        this.foundDate = foundDate;
    }

    public Date getFoundDate() 
    {
        return foundDate;
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
            .append("clubId", getClubId())
            .append("clubName", getClubName())
            .append("clubType", getClubType())
            .append("leaderId", getLeaderId())
            .append("description", getDescription())
            .append("foundDate", getFoundDate())
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
