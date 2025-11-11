package com.health.wanwan.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 陪玩专区-订单对象 play_order
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class PlayOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long orderId;

    /** 业务订单编号 */
    @Excel(name = "业务订单编号")
    private String orderSn;

    /** 下单客户ID (老板) */
    @Excel(name = "下单客户ID (老板)")
    private Long customerId;

    /** 接单陪玩ID */
    @Excel(name = "接单陪玩ID")
    private Long talentId;

    /** 计价方式 (1=按小时, 2=按局数) */
    @Excel(name = "计价方式 (1=按小时, 2=按局数)")
    private String pricingType;

    /** 下单时单价 */
    @Excel(name = "下单时单价")
    private BigDecimal unitPrice;

    /** 购买数量 (小时数或局数) */
    @Excel(name = "购买数量 (小时数或局数)")
    private Long quantity;

    /** 订单总金额 */
    @Excel(name = "订单总金额")
    private BigDecimal totalAmount;

    /** 订单状态 (10=待支付, 20=待服务, 30=服务中, 40=待确认, 50=已完成, 60=已取消) */
    @Excel(name = "订单状态 (10=待支付, 20=待服务, 30=服务中, 40=待确认, 50=已完成, 60=已取消)")
    private String orderStatus;

    /** 预约开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduledStartTime;

    /** 实际开始服务时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际开始服务时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualStartTime;

    /** 服务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "服务结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualEndTime;

    /** 删除标志 (0=存在, 1=删除) */
    private String delFlag;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    public void setOrderSn(String orderSn) 
    {
        this.orderSn = orderSn;
    }

    public String getOrderSn() 
    {
        return orderSn;
    }

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }

    public void setTalentId(Long talentId) 
    {
        this.talentId = talentId;
    }

    public Long getTalentId() 
    {
        return talentId;
    }

    public void setPricingType(String pricingType) 
    {
        this.pricingType = pricingType;
    }

    public String getPricingType() 
    {
        return pricingType;
    }

    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }

    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }

    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }

    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() 
    {
        return orderStatus;
    }

    public void setScheduledStartTime(Date scheduledStartTime) 
    {
        this.scheduledStartTime = scheduledStartTime;
    }

    public Date getScheduledStartTime() 
    {
        return scheduledStartTime;
    }

    public void setActualStartTime(Date actualStartTime) 
    {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualStartTime() 
    {
        return actualStartTime;
    }

    public void setActualEndTime(Date actualEndTime) 
    {
        this.actualEndTime = actualEndTime;
    }

    public Date getActualEndTime() 
    {
        return actualEndTime;
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
            .append("orderId", getOrderId())
            .append("orderSn", getOrderSn())
            .append("customerId", getCustomerId())
            .append("talentId", getTalentId())
            .append("pricingType", getPricingType())
            .append("unitPrice", getUnitPrice())
            .append("quantity", getQuantity())
            .append("totalAmount", getTotalAmount())
            .append("orderStatus", getOrderStatus())
            .append("scheduledStartTime", getScheduledStartTime())
            .append("actualStartTime", getActualStartTime())
            .append("actualEndTime", getActualEndTime())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
