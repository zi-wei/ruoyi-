package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.TCheckgroup;

/**
 * 检查组Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-08
 */
public interface TCheckgroupMapper 
{
    /**
     * 查询检查组
     * 
     * @param id 检查组主键
     * @return 检查组
     */
    public TCheckgroup selectTCheckgroupById(Long id);

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
     * @param tCheckgroup 检查组
     * @return 结果
     */
    public int insertTCheckgroup(TCheckgroup tCheckgroup);

    /**
     * 修改检查组
     * 
     * @param tCheckgroup 检查组
     * @return 结果
     */
    public int updateTCheckgroup(TCheckgroup tCheckgroup);

    /**
     * 删除检查组
     * 
     * @param id 检查组主键
     * @return 结果
     */
    public int deleteTCheckgroupById(Long id);

    /**
     * 批量删除检查组
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCheckgroupByIds(Long[] ids);
}
