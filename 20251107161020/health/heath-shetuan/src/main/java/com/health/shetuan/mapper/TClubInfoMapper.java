package com.health.shetuan.mapper;

import java.util.List;
import com.health.shetuan.domain.TClubInfo;

/**
 * 社团信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface TClubInfoMapper 
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
     * 删除社团信息
     * 
     * @param clubId 社团信息主键
     * @return 结果
     */
    public int deleteTClubInfoByClubId(Long clubId);

    /**
     * 批量删除社团信息
     * 
     * @param clubIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTClubInfoByClubIds(Long[] clubIds);
}
