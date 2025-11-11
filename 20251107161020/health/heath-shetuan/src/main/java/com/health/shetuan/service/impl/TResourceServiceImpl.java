package com.health.shetuan.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.shetuan.mapper.TResourceMapper;
import com.health.shetuan.domain.TResource;
import com.health.shetuan.service.ITResourceService;

/**
 * 资源信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class TResourceServiceImpl implements ITResourceService 
{
    @Autowired
    private TResourceMapper tResourceMapper;

    /**
     * 查询资源信息
     * 
     * @param resourceId 资源信息主键
     * @return 资源信息
     */
    @Override
    public TResource selectTResourceByResourceId(Long resourceId)
    {
        return tResourceMapper.selectTResourceByResourceId(resourceId);
    }

    /**
     * 查询资源信息列表
     * 
     * @param tResource 资源信息
     * @return 资源信息
     */
    @Override
    public List<TResource> selectTResourceList(TResource tResource)
    {
        return tResourceMapper.selectTResourceList(tResource);
    }

    /**
     * 新增资源信息
     * 
     * @param tResource 资源信息
     * @return 结果
     */
    @Override
    public int insertTResource(TResource tResource)
    {
        tResource.setCreateTime(DateUtils.getNowDate());
        return tResourceMapper.insertTResource(tResource);
    }

    /**
     * 修改资源信息
     * 
     * @param tResource 资源信息
     * @return 结果
     */
    @Override
    public int updateTResource(TResource tResource)
    {
        tResource.setUpdateTime(DateUtils.getNowDate());
        return tResourceMapper.updateTResource(tResource);
    }

    /**
     * 批量删除资源信息
     * 
     * @param resourceIds 需要删除的资源信息主键
     * @return 结果
     */
    @Override
    public int deleteTResourceByResourceIds(Long[] resourceIds)
    {
        return tResourceMapper.deleteTResourceByResourceIds(resourceIds);
    }

    /**
     * 删除资源信息信息
     * 
     * @param resourceId 资源信息主键
     * @return 结果
     */
    @Override
    public int deleteTResourceByResourceId(Long resourceId)
    {
        return tResourceMapper.deleteTResourceByResourceId(resourceId);
    }
}
