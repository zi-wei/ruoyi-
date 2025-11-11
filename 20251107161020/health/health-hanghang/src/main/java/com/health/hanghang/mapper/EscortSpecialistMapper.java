package com.health.hanghang.mapper;

import java.util.List;
import com.health.hanghang.domain.EscortSpecialist;

/**
 * 护航专区-打手Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface EscortSpecialistMapper 
{
    /**
     * 查询护航专区-打手
     * 
     * @param specialistId 护航专区-打手主键
     * @return 护航专区-打手
     */
    public EscortSpecialist selectEscortSpecialistBySpecialistId(Long specialistId);

    /**
     * 查询护航专区-打手列表
     * 
     * @param escortSpecialist 护航专区-打手
     * @return 护航专区-打手集合
     */
    public List<EscortSpecialist> selectEscortSpecialistList(EscortSpecialist escortSpecialist);

    /**
     * 新增护航专区-打手
     * 
     * @param escortSpecialist 护航专区-打手
     * @return 结果
     */
    public int insertEscortSpecialist(EscortSpecialist escortSpecialist);

    /**
     * 修改护航专区-打手
     * 
     * @param escortSpecialist 护航专区-打手
     * @return 结果
     */
    public int updateEscortSpecialist(EscortSpecialist escortSpecialist);

    /**
     * 删除护航专区-打手
     * 
     * @param specialistId 护航专区-打手主键
     * @return 结果
     */
    public int deleteEscortSpecialistBySpecialistId(Long specialistId);

    /**
     * 批量删除护航专区-打手
     * 
     * @param specialistIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEscortSpecialistBySpecialistIds(Long[] specialistIds);
}
