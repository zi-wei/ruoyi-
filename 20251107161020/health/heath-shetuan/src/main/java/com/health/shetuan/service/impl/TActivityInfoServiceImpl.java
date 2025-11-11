package com.health.shetuan.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.shetuan.mapper.TActivityInfoMapper;
import com.health.shetuan.domain.TActivityInfo;
import com.health.shetuan.service.ITActivityInfoService;

/**
 * 活动信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class TActivityInfoServiceImpl implements ITActivityInfoService 
{
    @Autowired
    private TActivityInfoMapper tActivityInfoMapper;

    /**
     * 查询活动信息
     * 
     * @param activityId 活动信息主键
     * @return 活动信息
     */
    @Override
    public TActivityInfo selectTActivityInfoByActivityId(Long activityId)
    {
        return tActivityInfoMapper.selectTActivityInfoByActivityId(activityId);
    }

    /**
     * 查询活动信息列表
     * 
     * @param tActivityInfo 活动信息
     * @return 活动信息
     */
    @Override
    public List<TActivityInfo> selectTActivityInfoList(TActivityInfo tActivityInfo)
    {
        return tActivityInfoMapper.selectTActivityInfoList(tActivityInfo);
    }

    /**
     * 新增活动信息
     * 
     * @param tActivityInfo 活动信息
     * @return 结果
     */
    @Override
    public int insertTActivityInfo(TActivityInfo tActivityInfo)
    {
        tActivityInfo.setCreateTime(DateUtils.getNowDate());
        return tActivityInfoMapper.insertTActivityInfo(tActivityInfo);
    }

    /**
     * 修改活动信息
     * 
     * @param tActivityInfo 活动信息
     * @return 结果
     */
    @Override
    public int updateTActivityInfo(TActivityInfo tActivityInfo)
    {
        tActivityInfo.setUpdateTime(DateUtils.getNowDate());
        return tActivityInfoMapper.updateTActivityInfo(tActivityInfo);
    }

    /**
     * 批量删除活动信息
     * 
     * @param activityIds 需要删除的活动信息主键
     * @return 结果
     */
    @Override
    public int deleteTActivityInfoByActivityIds(Long[] activityIds)
    {
        return tActivityInfoMapper.deleteTActivityInfoByActivityIds(activityIds);
    }

    /**
     * 删除活动信息信息
     * 
     * @param activityId 活动信息主键
     * @return 结果
     */
    @Override
    public int deleteTActivityInfoByActivityId(Long activityId)
    {
        return tActivityInfoMapper.deleteTActivityInfoByActivityId(activityId);
    }
}
