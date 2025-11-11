package com.health.hanghang.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.hanghang.mapper.EscortSpecialistMapper;
import com.health.hanghang.domain.EscortSpecialist;
import com.health.hanghang.service.IEscortSpecialistService;

/**
 * 护航专区-打手Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class EscortSpecialistServiceImpl implements IEscortSpecialistService 
{
    @Autowired
    private EscortSpecialistMapper escortSpecialistMapper;

    /**
     * 查询护航专区-打手
     * 
     * @param specialistId 护航专区-打手主键
     * @return 护航专区-打手
     */
    @Override
    public EscortSpecialist selectEscortSpecialistBySpecialistId(Long specialistId)
    {
        return escortSpecialistMapper.selectEscortSpecialistBySpecialistId(specialistId);
    }

    /**
     * 查询护航专区-打手列表
     * 
     * @param escortSpecialist 护航专区-打手
     * @return 护航专区-打手
     */
    @Override
    public List<EscortSpecialist> selectEscortSpecialistList(EscortSpecialist escortSpecialist)
    {
        return escortSpecialistMapper.selectEscortSpecialistList(escortSpecialist);
    }

    /**
     * 新增护航专区-打手
     * 
     * @param escortSpecialist 护航专区-打手
     * @return 结果
     */
    @Override
    public int insertEscortSpecialist(EscortSpecialist escortSpecialist)
    {
        escortSpecialist.setCreateTime(DateUtils.getNowDate());
        return escortSpecialistMapper.insertEscortSpecialist(escortSpecialist);
    }

    /**
     * 修改护航专区-打手
     * 
     * @param escortSpecialist 护航专区-打手
     * @return 结果
     */
    @Override
    public int updateEscortSpecialist(EscortSpecialist escortSpecialist)
    {
        escortSpecialist.setUpdateTime(DateUtils.getNowDate());
        return escortSpecialistMapper.updateEscortSpecialist(escortSpecialist);
    }

    /**
     * 批量删除护航专区-打手
     * 
     * @param specialistIds 需要删除的护航专区-打手主键
     * @return 结果
     */
    @Override
    public int deleteEscortSpecialistBySpecialistIds(Long[] specialistIds)
    {
        return escortSpecialistMapper.deleteEscortSpecialistBySpecialistIds(specialistIds);
    }

    /**
     * 删除护航专区-打手信息
     * 
     * @param specialistId 护航专区-打手主键
     * @return 结果
     */
    @Override
    public int deleteEscortSpecialistBySpecialistId(Long specialistId)
    {
        return escortSpecialistMapper.deleteEscortSpecialistBySpecialistId(specialistId);
    }
}
