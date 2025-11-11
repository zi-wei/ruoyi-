package com.health.wanwan.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.wanwan.mapper.PlayReviewMapper;
import com.health.wanwan.domain.PlayReview;
import com.health.wanwan.service.IPlayReviewService;

/**
 * 陪玩专区-评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class PlayReviewServiceImpl implements IPlayReviewService 
{
    @Autowired
    private PlayReviewMapper playReviewMapper;

    /**
     * 查询陪玩专区-评价
     * 
     * @param reviewId 陪玩专区-评价主键
     * @return 陪玩专区-评价
     */
    @Override
    public PlayReview selectPlayReviewByReviewId(Long reviewId)
    {
        return playReviewMapper.selectPlayReviewByReviewId(reviewId);
    }

    /**
     * 查询陪玩专区-评价列表
     * 
     * @param playReview 陪玩专区-评价
     * @return 陪玩专区-评价
     */
    @Override
    public List<PlayReview> selectPlayReviewList(PlayReview playReview)
    {
        return playReviewMapper.selectPlayReviewList(playReview);
    }

    /**
     * 新增陪玩专区-评价
     * 
     * @param playReview 陪玩专区-评价
     * @return 结果
     */
    @Override
    public int insertPlayReview(PlayReview playReview)
    {
        playReview.setCreateTime(DateUtils.getNowDate());
        return playReviewMapper.insertPlayReview(playReview);
    }

    /**
     * 修改陪玩专区-评价
     * 
     * @param playReview 陪玩专区-评价
     * @return 结果
     */
    @Override
    public int updatePlayReview(PlayReview playReview)
    {
        playReview.setUpdateTime(DateUtils.getNowDate());
        return playReviewMapper.updatePlayReview(playReview);
    }

    /**
     * 批量删除陪玩专区-评价
     * 
     * @param reviewIds 需要删除的陪玩专区-评价主键
     * @return 结果
     */
    @Override
    public int deletePlayReviewByReviewIds(Long[] reviewIds)
    {
        return playReviewMapper.deletePlayReviewByReviewIds(reviewIds);
    }

    /**
     * 删除陪玩专区-评价信息
     * 
     * @param reviewId 陪玩专区-评价主键
     * @return 结果
     */
    @Override
    public int deletePlayReviewByReviewId(Long reviewId)
    {
        return playReviewMapper.deletePlayReviewByReviewId(reviewId);
    }
}
