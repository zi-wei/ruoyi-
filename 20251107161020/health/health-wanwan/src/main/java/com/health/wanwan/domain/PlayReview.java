package com.health.wanwan.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 陪玩专区-评价对象 play_review
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public class PlayReview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价ID */
    private Long reviewId;

    /** 关联的陪玩订单ID */
    @Excel(name = "关联的陪玩订单ID")
    private Long orderId;

    /** 被评价的陪玩ID */
    @Excel(name = "被评价的陪玩ID")
    private Long talentId;

    /** 给出评价的客户ID */
    @Excel(name = "给出评价的客户ID")
    private Long customerId;

    /** 服务评分 (1-5星) */
    @Excel(name = "服务评分 (1-5星)")
    private Integer ratingScore;

    /** 评价标签 (如: 声音好听, 很有耐心, 逗号分隔) */
    @Excel(name = "评价标签 (如: 声音好听, 很有耐心, 逗号分隔)")
    private String reviewTags;

    /** 文字评价内容 */
    @Excel(name = "文字评价内容")
    private String comment;

    /** 是否有回复 (0=无, 1=有) */
    @Excel(name = "是否有回复 (0=无, 1=有)")
    private String hasReply;

    /** 陪玩的回复内容 */
    @Excel(name = "陪玩的回复内容")
    private String replyContent;

    /** 删除标志 (0=存在, 1=删除) */
    private String delFlag;

    public void setReviewId(Long reviewId) 
    {
        this.reviewId = reviewId;
    }

    public Long getReviewId() 
    {
        return reviewId;
    }

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    public void setTalentId(Long talentId) 
    {
        this.talentId = talentId;
    }

    public Long getTalentId() 
    {
        return talentId;
    }

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }

    public void setRatingScore(Integer ratingScore) 
    {
        this.ratingScore = ratingScore;
    }

    public Integer getRatingScore() 
    {
        return ratingScore;
    }

    public void setReviewTags(String reviewTags) 
    {
        this.reviewTags = reviewTags;
    }

    public String getReviewTags() 
    {
        return reviewTags;
    }

    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }

    public void setHasReply(String hasReply) 
    {
        this.hasReply = hasReply;
    }

    public String getHasReply() 
    {
        return hasReply;
    }

    public void setReplyContent(String replyContent) 
    {
        this.replyContent = replyContent;
    }

    public String getReplyContent() 
    {
        return replyContent;
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
            .append("reviewId", getReviewId())
            .append("orderId", getOrderId())
            .append("talentId", getTalentId())
            .append("customerId", getCustomerId())
            .append("ratingScore", getRatingScore())
            .append("reviewTags", getReviewTags())
            .append("comment", getComment())
            .append("hasReply", getHasReply())
            .append("replyContent", getReplyContent())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
