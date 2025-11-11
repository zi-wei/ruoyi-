package com.health.reservation.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * 套餐检查组关联Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-10
 */
public interface TSetmealCheckgroupMapper {

    /**
     * 根据检查组ID查询关联的套餐数量
     * @param id 检查组ID
     * @return 关联的套餐数量
     */
    @Select("select count(1) from t_setmeal_checkgroup where checkgroup_id=#{id}")
    int selectCountByCheckgroupId(Long id);
}