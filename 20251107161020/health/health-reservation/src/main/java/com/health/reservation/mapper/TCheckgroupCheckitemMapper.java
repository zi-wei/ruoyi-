package com.health.reservation.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.health.reservation.domain.TCheckgroupCheckitem;

/**
 * 检查组检查项关联Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-09
 */
public interface TCheckgroupCheckitemMapper 
{
    /**
     * 查询检查组检查项关联
     * 
     * @param checkgroupId 检查组检查项关联主键
     * @return 检查组检查项关联
     */
    public TCheckgroupCheckitem selectTCheckgroupCheckitemByCheckgroupId(Long checkgroupId);

    /**
     * 查询检查组检查项关联列表
     * 
     * @param tCheckgroupCheckitem 检查组检查项关联
     * @return 检查组检查项关联集合
     */
    public List<TCheckgroupCheckitem> selectTCheckgroupCheckitemList(TCheckgroupCheckitem tCheckgroupCheckitem);

    /**
     * 新增检查组检查项关联
     * 
     * @param tCheckgroupCheckitem 检查组检查项关联
     * @return 结果
     */
    public int insertTCheckgroupCheckitem(TCheckgroupCheckitem tCheckgroupCheckitem);

    /**
     * 批量插入检查组和检查项关联
     * @param checkItemIds 检查项 id 列表
     * @param groupId 检查组 id
     * @return 影响行数
     */
    int insertCheckitemIds(@Param("list") List<Long> checkItemIds, @Param("groupId") Long groupId);

    /**
     * 修改检查组检查项关联
     * 
     * @param tCheckgroupCheckitem 检查组检查项关联
     * @return 结果
     */
    public int updateTCheckgroupCheckitem(TCheckgroupCheckitem tCheckgroupCheckitem);

    /**
     * 删除检查组检查项关联
     * 
     * @param checkgroupId 检查组检查项关联主键
     * @return 结果
     */
    public int deleteTCheckgroupCheckitemByCheckgroupId(Long checkgroupId);

    /**
     * 批量删除检查组检查项关联
     * 
     * @param checkgroupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCheckgroupCheckitemByCheckgroupIds(Long[] checkgroupIds);

    /**
     * 根据检查组 id 查询关联的检查项 id 列表
     * @param id 检查组 id
     * @return 检查项 id 列表
     */
    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id=#{id}")
    List<Long> selectCheckitemIds(Long id);
}
