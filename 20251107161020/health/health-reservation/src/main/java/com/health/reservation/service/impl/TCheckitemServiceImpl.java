package com.health.reservation.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.reservation.mapper.TCheckitemMapper;
import com.health.reservation.domain.TCheckitem;
import com.health.reservation.service.ITCheckitemService;

/**
 * 检查项信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-07
 */
@Service
public class TCheckitemServiceImpl implements ITCheckitemService 
{
    @Autowired
    private TCheckitemMapper tCheckitemMapper;

    /**
     * 查询检查项信息
     * 
     * @param id 检查项信息主键
     * @return 检查项信息
     */
    @Override
    public TCheckitem selectTCheckitemById(Long id)
    {
        return tCheckitemMapper.selectTCheckitemById(id);
    }

    /**
     * 查询检查项信息列表
     * 
     * @param tCheckitem 检查项信息
     * @return 检查项信息
     */
    @Override
    public List<TCheckitem> selectTCheckitemList(TCheckitem tCheckitem)
    {
        return tCheckitemMapper.selectTCheckitemList(tCheckitem);
    }

    /**
     * 新增检查项信息
     * 
     * @param tCheckitem 检查项信息
     * @return 结果
     */
    @Override
    public int insertTCheckitem(TCheckitem tCheckitem)
    {
        tCheckitem.setCreateTime(DateUtils.getNowDate());
        return tCheckitemMapper.insertTCheckitem(tCheckitem);
    }

    /**
     * 修改检查项信息
     * 
     * @param tCheckitem 检查项信息
     * @return 结果
     */
    @Override
    public int updateTCheckitem(TCheckitem tCheckitem)
    {
        tCheckitem.setUpdateTime(DateUtils.getNowDate());
        return tCheckitemMapper.updateTCheckitem(tCheckitem);
    }

    /**
     * 批量删除检查项信息
     * 
     * @param ids 需要删除的检查项信息主键
     * @return 结果
     */
    @Override
    public int deleteTCheckitemByIds(Long[] ids)
    {
        return tCheckitemMapper.deleteTCheckitemByIds(ids);
    }

    /**
     * 删除检查项信息信息
     * 
     * @param id 检查项信息主键
     * @return 结果
     */
    @Override
    public int deleteTCheckitemById(Long id)
    {
        return tCheckitemMapper.deleteTCheckitemById(id);
    }
}
