package com.health.shetuan.mapper;

import java.util.List;
import com.health.shetuan.domain.TActivityInfo;

/**
 * 活动信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface TActivityInfoMapper 
{
    /**
     * 查询活动信息
     * 
     * @param activityId 活动信息主键
     * @return 活动信息
     */
    public TActivityInfo selectTActivityInfoByActivityId(Long activityId);

    /**
     * 查询活动信息列表
     * 
     * @param tActivityInfo 活动信息
     * @return 活动信息集合
     */
    public List<TActivityInfo> selectTActivityInfoList(TActivityInfo tActivityInfo);

    /**
     * 新增活动信息
     * 
     * @param tActivityInfo 活动信息
     * @return 结果
     */
    public int insertTActivityInfo(TActivityInfo tActivityInfo);

    /**
     * 修改活动信息
     * 
     * @param tActivityInfo 活动信息
     * @return 结果
     */
    public int updateTActivityInfo(TActivityInfo tActivityInfo);

    /**
     * 删除活动信息
     * 
     * @param activityId 活动信息主键
     * @return 结果
     */
    public int deleteTActivityInfoByActivityId(Long activityId);

    /**
     * 批量删除活动信息
     * 
     * @param activityIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTActivityInfoByActivityIds(Long[] activityIds);
}
