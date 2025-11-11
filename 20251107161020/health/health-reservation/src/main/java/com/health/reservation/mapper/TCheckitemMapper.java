package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.TCheckitem;

/**
 * 检查项信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-07
 */
public interface TCheckitemMapper 
{
    /**
     * 查询检查项信息
     * 
     * @param id 检查项信息主键
     * @return 检查项信息
     */
    public TCheckitem selectTCheckitemById(Long id);

    /**
     * 查询检查项信息列表
     * 
     * @param tCheckitem 检查项信息
     * @return 检查项信息集合
     */
    public List<TCheckitem> selectTCheckitemList(TCheckitem tCheckitem);

    /**
     * 新增检查项信息
     * 
     * @param tCheckitem 检查项信息
     * @return 结果
     */
    public int insertTCheckitem(TCheckitem tCheckitem);

    /**
     * 修改检查项信息
     * 
     * @param tCheckitem 检查项信息
     * @return 结果
     */
    public int updateTCheckitem(TCheckitem tCheckitem);

    /**
     * 删除检查项信息
     * 
     * @param id 检查项信息主键
     * @return 结果
     */
    public int deleteTCheckitemById(Long id);

    /**
     * 批量删除检查项信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCheckitemByIds(Long[] ids);
}
