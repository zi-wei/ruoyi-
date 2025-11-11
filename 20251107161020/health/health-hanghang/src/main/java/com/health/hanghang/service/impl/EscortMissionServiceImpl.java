package com.health.hanghang.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.hanghang.mapper.EscortMissionMapper;
import com.health.hanghang.domain.EscortMission;
import com.health.hanghang.service.IEscortMissionService;

/**
 * 护航专区-任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class EscortMissionServiceImpl implements IEscortMissionService 
{
    @Autowired
    private EscortMissionMapper escortMissionMapper;

    /**
     * 查询护航专区-任务
     * 
     * @param missionId 护航专区-任务主键
     * @return 护航专区-任务
     */
    @Override
    public EscortMission selectEscortMissionByMissionId(Long missionId)
    {
        return escortMissionMapper.selectEscortMissionByMissionId(missionId);
    }

    /**
     * 查询护航专区-任务列表
     * 
     * @param escortMission 护航专区-任务
     * @return 护航专区-任务
     */
    @Override
    public List<EscortMission> selectEscortMissionList(EscortMission escortMission)
    {
        return escortMissionMapper.selectEscortMissionList(escortMission);
    }

    /**
     * 新增护航专区-任务
     * 
     * @param escortMission 护航专区-任务
     * @return 结果
     */
    @Override
    public int insertEscortMission(EscortMission escortMission)
    {
        escortMission.setCreateTime(DateUtils.getNowDate());
        return escortMissionMapper.insertEscortMission(escortMission);
    }

    /**
     * 修改护航专区-任务
     * 
     * @param escortMission 护航专区-任务
     * @return 结果
     */
    @Override
    public int updateEscortMission(EscortMission escortMission)
    {
        escortMission.setUpdateTime(DateUtils.getNowDate());
        return escortMissionMapper.updateEscortMission(escortMission);
    }

    /**
     * 批量删除护航专区-任务
     * 
     * @param missionIds 需要删除的护航专区-任务主键
     * @return 结果
     */
    @Override
    public int deleteEscortMissionByMissionIds(Long[] missionIds)
    {
        return escortMissionMapper.deleteEscortMissionByMissionIds(missionIds);
    }

    /**
     * 删除护航专区-任务信息
     * 
     * @param missionId 护航专区-任务主键
     * @return 结果
     */
    @Override
    public int deleteEscortMissionByMissionId(Long missionId)
    {
        return escortMissionMapper.deleteEscortMissionByMissionId(missionId);
    }
}
