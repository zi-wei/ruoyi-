package com.health.shetuan.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.shetuan.mapper.TClubInfoMapper;
import com.health.shetuan.domain.TClubInfo;
import com.health.shetuan.service.ITClubInfoService;

/**
 * 社团信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class TClubInfoServiceImpl implements ITClubInfoService 
{
    @Autowired
    private TClubInfoMapper tClubInfoMapper;

    /**
     * 查询社团信息
     * 
     * @param clubId 社团信息主键
     * @return 社团信息
     */
    @Override
    public TClubInfo selectTClubInfoByClubId(Long clubId)
    {
        return tClubInfoMapper.selectTClubInfoByClubId(clubId);
    }

    /**
     * 查询社团信息列表
     * 
     * @param tClubInfo 社团信息
     * @return 社团信息
     */
    @Override
    public List<TClubInfo> selectTClubInfoList(TClubInfo tClubInfo)
    {
        return tClubInfoMapper.selectTClubInfoList(tClubInfo);
    }

    /**
     * 新增社团信息
     * 
     * @param tClubInfo 社团信息
     * @return 结果
     */
    @Override
    public int insertTClubInfo(TClubInfo tClubInfo)
    {
        tClubInfo.setCreateTime(DateUtils.getNowDate());
        return tClubInfoMapper.insertTClubInfo(tClubInfo);
    }

    /**
     * 修改社团信息
     * 
     * @param tClubInfo 社团信息
     * @return 结果
     */
    @Override
    public int updateTClubInfo(TClubInfo tClubInfo)
    {
        tClubInfo.setUpdateTime(DateUtils.getNowDate());
        return tClubInfoMapper.updateTClubInfo(tClubInfo);
    }

    /**
     * 批量删除社团信息
     * 
     * @param clubIds 需要删除的社团信息主键
     * @return 结果
     */
    @Override
    public int deleteTClubInfoByClubIds(Long[] clubIds)
    {
        return tClubInfoMapper.deleteTClubInfoByClubIds(clubIds);
    }

    /**
     * 删除社团信息信息
     * 
     * @param clubId 社团信息主键
     * @return 结果
     */
    @Override
    public int deleteTClubInfoByClubId(Long clubId)
    {
        return tClubInfoMapper.deleteTClubInfoByClubId(clubId);
    }
}
