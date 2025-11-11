package com.health.reservation.service;

import java.util.List;
import com.health.reservation.domain.TSetmeal;

/**
 * 套餐Service接口
 * 
 * @author ruoyi
 * @date 2025-11-08
 */
public interface ITSetmealService 
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
     * 批量删除套餐
     * 
     * @param ids 需要删除的套餐主键集合
     * @return 结果
     */
    public int deleteTSetmealByIds(Long[] ids);

    /**
     * 删除套餐信息
     * 
     * @param id 套餐主键
     * @return 结果
     */
    public int deleteTSetmealById(Long id);
}
