package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 检查组检查项关联对象 t_checkgroup_checkitem
 * 
 * @author ruoyi
 * @date 2025-11-09
 */
public class TCheckgroupCheckitem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检查组ID */
    private Long checkgroupId;

    /** 检查项ID */
    private Long checkitemId;

    public void setCheckgroupId(Long checkgroupId) 
    {
        this.checkgroupId = checkgroupId;
    }

    public Long getCheckgroupId() 
    {
        return checkgroupId;
    }

    public void setCheckitemId(Long checkitemId) 
    {
        this.checkitemId = checkitemId;
    }

    public Long getCheckitemId() 
    {
        return checkitemId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("checkgroupId", getCheckgroupId())
            .append("checkitemId", getCheckitemId())
            .toString();
    }
}
