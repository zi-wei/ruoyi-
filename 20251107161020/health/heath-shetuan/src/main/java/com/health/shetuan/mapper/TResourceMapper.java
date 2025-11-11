package com.health.shetuan.mapper;

import java.util.List;
import com.health.shetuan.domain.TResource;

/**
 * 资源信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface TResourceMapper 
{
    /**
     * 查询资源信息
     * 
     * @param resourceId 资源信息主键
     * @return 资源信息
     */
    public TResource selectTResourceByResourceId(Long resourceId);

    /**
     * 查询资源信息列表
     * 
     * @param tResource 资源信息
     * @return 资源信息集合
     */
    public List<TResource> selectTResourceList(TResource tResource);

    /**
     * 新增资源信息
     * 
     * @param tResource 资源信息
     * @return 结果
     */
    public int insertTResource(TResource tResource);

    /**
     * 修改资源信息
     * 
     * @param tResource 资源信息
     * @return 结果
     */
    public int updateTResource(TResource tResource);

    /**
     * 删除资源信息
     * 
     * @param resourceId 资源信息主键
     * @return 结果
     */
    public int deleteTResourceByResourceId(Long resourceId);

    /**
     * 批量删除资源信息
     * 
     * @param resourceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTResourceByResourceIds(Long[] resourceIds);
}
