package com.health.shetuan.service;

import java.util.List;
import com.health.shetuan.domain.TClubInfo;

/**
 * 社团信息Service接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface ITClubInfoService 
{
    /**
     * 查询社团信息
     * 
     * @param clubId 社团信息主键
     * @return 社团信息
     */
    public TClubInfo selectTClubInfoByClubId(Long clubId);

    /**
     * 查询社团信息列表
     * 
     * @param tClubInfo 社团信息
     * @return 社团信息集合
     */
    public List<TClubInfo> selectTClubInfoList(TClubInfo tClubInfo);

    /**
     * 新增社团信息
     * 
     * @param tClubInfo 社团信息
     * @return 结果
     */
    public int insertTClubInfo(TClubInfo tClubInfo);

    /**
     * 修改社团信息
     * 
     * @param tClubInfo 社团信息
     * @return 结果
     */
    public int updateTClubInfo(TClubInfo tClubInfo);

    /**
     * 批量删除社团信息
     * 
     * @param clubIds 需要删除的社团信息主键集合
     * @return 结果
     */
    public int deleteTClubInfoByClubIds(Long[] clubIds);

    /**
     * 删除社团信息信息
     * 
     * @param clubId 社团信息主键
     * @return 结果
     */
    public int deleteTClubInfoByClubId(Long clubId);
}
