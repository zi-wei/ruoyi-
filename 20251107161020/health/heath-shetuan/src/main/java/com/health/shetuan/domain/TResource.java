package com.health.shetuan.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 资源信息对象 t_resource
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class TResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资源ID */
    private Long resourceId;

    /** 资源名称 */
    @Excel(name = "资源名称")
    private String resourceName;

    /** 资源类型（字典值：场地/物资） */
    @Excel(name = "资源类型", readConverterExp = "字=典值：场地/物资")
    private String resourceType;

    /** 资源总数量 */
    @Excel(name = "资源总数量")
    private Long quantity;

    /** 存放或所在地点 */
    @Excel(name = "存放或所在地点")
    private String location;

    /** 状态 (0:可用, 1:维护中) */
    @Excel(name = "状态 (0:可用, 1:维护中)")
    private String status;

    /** 删除标志 */
    private String delFlag;

    public void setResourceId(Long resourceId) 
    {
        this.resourceId = resourceId;
    }

    public Long getResourceId() 
    {
        return resourceId;
    }

    public void setResourceName(String resourceName) 
    {
        this.resourceName = resourceName;
    }

    public String getResourceName() 
    {
        return resourceName;
    }

    public void setResourceType(String resourceType) 
    {
        this.resourceType = resourceType;
    }

    public String getResourceType() 
    {
        return resourceType;
    }

    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
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
            .append("resourceId", getResourceId())
            .append("resourceName", getResourceName())
            .append("resourceType", getResourceType())
            .append("quantity", getQuantity())
            .append("location", getLocation())
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
