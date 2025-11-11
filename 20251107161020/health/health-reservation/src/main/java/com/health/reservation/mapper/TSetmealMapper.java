package com.health.reservation.mapper;

import java.util.List;
import com.health.reservation.domain.TSetmeal;

/**
 * 套餐Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-08
 */
public interface TSetmealMapper 
{
    /**
     * 查询套餐
     * 
     * @param id 套餐主键
     * @return 套餐
     */
    public TSetmeal selectTSetmealById(Long id);

    /**
     * 查询套餐列表
     * 
     * @param tSetmeal 套餐
     * @return 套餐集合
     */
    public List<TSetmeal> selectTSetmealList(TSetmeal tSetmeal);

    /**
     * 新增套餐
     * 
     * @param tSetmeal 套餐
     * @return 结果
     */
    public int insertTSetmeal(TSetmeal tSetmeal);

    /**
     * 修改套餐
     * 
     * @param tSetmeal 套餐
     * @return 结果
     */
    public int updateTSetmeal(TSetmeal tSetmeal);

    /**
     * 删除套餐
     * 
     * @param id 套餐主键
     * @return 结果
     */
    public int deleteTSetmealById(Long id);

    /**
     * 批量删除套餐
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTSetmealByIds(Long[] ids);
}
