package com.health.wanwan.service;

import java.util.List;
import com.health.wanwan.domain.PlayReview;

/**
 * 陪玩专区-评价Service接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface IPlayReviewService 
{
    /**
     * 查询陪玩专区-评价
     * 
     * @param reviewId 陪玩专区-评价主键
     * @return 陪玩专区-评价
     */
    public PlayReview selectPlayReviewByReviewId(Long reviewId);

    /**
     * 查询陪玩专区-评价列表
     * 
     * @param playReview 陪玩专区-评价
     * @return 陪玩专区-评价集合
     */
    public List<PlayReview> selectPlayReviewList(PlayReview playReview);

    /**
     * 根据订单ID查询评价
     * 
     * @param orderId 订单ID
     * @return 陪玩专区-评价
     */
    public PlayReview selectPlayReviewByOrderId(Long orderId);

    /**
     * 新增陪玩专区-评价
     * 
     * @param playReview 陪玩专区-评价
     * @return 结果
     */
    public int insertPlayReview(PlayReview playReview);

    /**
     * 修改陪玩专区-评价
     * 
     * @param playReview 陪玩专区-评价
     * @return 结果
     */
    public int updatePlayReview(PlayReview playReview);

    /**
     * 批量删除陪玩专区-评价
     * 
     * @param reviewIds 需要删除的陪玩专区-评价主键集合
     * @return 结果
     */
    public int deletePlayReviewByReviewIds(Long[] reviewIds);

    /**
     * 删除陪玩专区-评价信息
     * 
     * @param reviewId 陪玩专区-评价主键
     * @return 结果
     */
    public int deletePlayReviewByReviewId(Long reviewId);
}
