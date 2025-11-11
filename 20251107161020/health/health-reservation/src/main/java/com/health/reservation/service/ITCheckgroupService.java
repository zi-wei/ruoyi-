package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.vo.TCheckgroupVo;
import com.health.reservation.dto.TCheckgroupDto;

/**
 * 检查组Service接口
 * 
 * @author ruoyi
 * @date 2025-11-08
 */
public interface ITCheckgroupService 
{
    /**
     * 查询检查组
     * 
     * @param id 检查组主键
     * @return 检查组
     */
    public TCheckgroupVo selectTCheckgroupById(Long id);

    /**
     * 查询检查组列表
     * 
     * @param tCheckgroup 检查组
     * @return 检查组集合
     */
    public List<TCheckgroup> selectTCheckgroupList(TCheckgroup tCheckgroup);

    /**
     * 新增检查组
     * 
     * @param dto 检查组 DTO（包含检查项关联列表）
     * @return 结果
     */
    public int insertTCheckgroup(TCheckgroupDto dto);

    /**
     * 修改检查组
     * 
     * @param dto 检查组 DTO（包含检查项关联列表）
     * @return 结果
     */
    public int updateTCheckgroup(TCheckgroupDto dto);

    /**
     * 批量删除检查组
     * 
     * @param ids 需要删除的检查组主键集合
     * @return 结果
     */
    public int deleteTCheckgroupByIds(Long[] ids);

    /**
     * 删除检查组信息
     * 
     * @param id 检查组主键
     * @return 结果
     */
    public int deleteTCheckgroupById(Long id);

    /**
     * 查询所有检查组
     * 
     * @return 检查组集合
     */
    public List<TCheckgroup> selectAllTCheckgroup();
}
