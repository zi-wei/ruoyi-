package com.health.hanghang.service;

import java.util.List;
import com.health.hanghang.domain.EscortMission;

/**
 * 护航专区-任务Service接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface IEscortMissionService 
{
    /**
     * 查询护航专区-任务
     * 
     * @param missionId 护航专区-任务主键
     * @return 护航专区-任务
     */
    public EscortMission selectEscortMissionByMissionId(Long missionId);

    /**
     * 查询护航专区-任务列表
     * 
     * @param escortMission 护航专区-任务
     * @return 护航专区-任务集合
     */
    public List<EscortMission> selectEscortMissionList(EscortMission escortMission);

    /**
     * 新增护航专区-任务
     * 
     * @param escortMission 护航专区-任务
     * @return 结果
     */
    public int insertEscortMission(EscortMission escortMission);

    /**
     * 修改护航专区-任务
     * 
     * @param escortMission 护航专区-任务
     * @return 结果
     */
    public int updateEscortMission(EscortMission escortMission);

    /**
     * 批量删除护航专区-任务
     * 
     * @param missionIds 需要删除的护航专区-任务主键集合
     * @return 结果
     */
    public int deleteEscortMissionByMissionIds(Long[] missionIds);

    /**
     * 删除护航专区-任务信息
     * 
     * @param missionId 护航专区-任务主键
     * @return 结果
     */
    public int deleteEscortMissionByMissionId(Long missionId);
}
