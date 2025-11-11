package com.health.shetuan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 社团成员对象 t_club_member
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class TClubMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成员ID */
    private Long memberId;

    /** 社团ID */
    @Excel(name = "社团ID")
    private Long clubId;

    /** 成员用户ID (关联 sys_user) */
    @Excel(name = "成员用户ID (关联 sys_user)")
    private Long userId;

    /** 加入日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加入日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinDate;

    /** 在社团内的角色（字典值） */
    @Excel(name = "在社团内的角色", readConverterExp = "字=典值")
    private String roleInClub;

    /** 申请状态 (0:待审批, 1:已通过, 2:已拒绝) */
    @Excel(name = "申请状态 (0:待审批, 1:已通过, 2:已拒绝)")
    private String applyStatus;

    /** 删除标志 */
    private String delFlag;

    public void setMemberId(Long memberId) 
    {
        this.memberId = memberId;
    }

    public Long getMemberId() 
    {
        return memberId;
    }

    public void setClubId(Long clubId) 
    {
        this.clubId = clubId;
    }

    public Long getClubId() 
    {
        return clubId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setJoinDate(Date joinDate) 
    {
        this.joinDate = joinDate;
    }

    public Date getJoinDate() 
    {
        return joinDate;
    }

    public void setRoleInClub(String roleInClub) 
    {
        this.roleInClub = roleInClub;
    }

    public String getRoleInClub() 
    {
        return roleInClub;
    }

    public void setApplyStatus(String applyStatus) 
    {
        this.applyStatus = applyStatus;
    }

    public String getApplyStatus() 
    {
        return applyStatus;
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
            .append("memberId", getMemberId())
            .append("clubId", getClubId())
            .append("userId", getUserId())
            .append("joinDate", getJoinDate())
            .append("roleInClub", getRoleInClub())
            .append("applyStatus", getApplyStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
