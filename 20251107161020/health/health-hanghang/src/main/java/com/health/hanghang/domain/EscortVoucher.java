package com.health.hanghang.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 护航专区-任务凭证对象 escort_voucher
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class EscortVoucher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 凭证ID */
    private Long voucherId;

    /** 关联的护航任务ID */
    @Excel(name = "关联的护航任务ID")
    private Long missionId;

    /** 上传者用户ID (通常是护航专家) */
    @Excel(name = "上传者用户ID (通常是护航专家)")
    private Long uploaderId;

    /** 凭证类型 (1=截图, 2=录屏链接) */
    @Excel(name = "凭证类型 (1=截图, 2=录屏链接)")
    private String voucherType;

    /** 凭证文件URL或视频链接 */
    @Excel(name = "凭证文件URL或视频链接")
    private String voucherUrl;

    /** 凭证描述 (如：撤离时结算界面截图) */
    @Excel(name = "凭证描述 (如：撤离时结算界面截图)")
    private String description;

    /** 最终物资价值 (根据凭证填写) */
    @Excel(name = "最终物资价值 (根据凭证填写)")
    private Long finalAssetValue;

    /** 客户是否已确认 (0=未确认, 1=已确认, 2=有争议) */
    @Excel(name = "客户是否已确认 (0=未确认, 1=已确认, 2=有争议)")
    private String isConfirmedByCustomer;

    /** 管理员审核状态 (0=未审核, 1=审核通过, 2=审核不通过) */
    @Excel(name = "管理员审核状态 (0=未审核, 1=审核通过, 2=审核不通过)")
    private String adminAuditStatus;

    /** 删除标志 (0=存在, 1=删除) */
    private String delFlag;

    public void setVoucherId(Long voucherId) 
    {
        this.voucherId = voucherId;
    }

    public Long getVoucherId() 
    {
        return voucherId;
    }

    public void setMissionId(Long missionId) 
    {
        this.missionId = missionId;
    }

    public Long getMissionId() 
    {
        return missionId;
    }

    public void setUploaderId(Long uploaderId) 
    {
        this.uploaderId = uploaderId;
    }

    public Long getUploaderId() 
    {
        return uploaderId;
    }

    public void setVoucherType(String voucherType) 
    {
        this.voucherType = voucherType;
    }

    public String getVoucherType() 
    {
        return voucherType;
    }

    public void setVoucherUrl(String voucherUrl) 
    {
        this.voucherUrl = voucherUrl;
    }

    public String getVoucherUrl() 
    {
        return voucherUrl;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setFinalAssetValue(Long finalAssetValue) 
    {
        this.finalAssetValue = finalAssetValue;
    }

    public Long getFinalAssetValue() 
    {
        return finalAssetValue;
    }

    public void setIsConfirmedByCustomer(String isConfirmedByCustomer) 
    {
        this.isConfirmedByCustomer = isConfirmedByCustomer;
    }

    public String getIsConfirmedByCustomer() 
    {
        return isConfirmedByCustomer;
    }

    public void setAdminAuditStatus(String adminAuditStatus) 
    {
        this.adminAuditStatus = adminAuditStatus;
    }

    public String getAdminAuditStatus() 
    {
        return adminAuditStatus;
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
            .append("voucherId", getVoucherId())
            .append("missionId", getMissionId())
            .append("uploaderId", getUploaderId())
            .append("voucherType", getVoucherType())
            .append("voucherUrl", getVoucherUrl())
            .append("description", getDescription())
            .append("finalAssetValue", getFinalAssetValue())
            .append("isConfirmedByCustomer", getIsConfirmedByCustomer())
            .append("adminAuditStatus", getAdminAuditStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
