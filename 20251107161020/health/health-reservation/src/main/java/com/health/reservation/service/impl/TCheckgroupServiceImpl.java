package com.health.reservation.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import com.health.reservation.vo.TCheckgroupVo;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.health.reservation.mapper.TCheckgroupMapper;
import com.health.reservation.mapper.TCheckgroupCheckitemMapper;
import com.health.reservation.mapper.TSetmealCheckgroupMapper;
import com.health.reservation.domain.TCheckgroup;
import com.health.reservation.dto.TCheckgroupDto;
import com.health.reservation.service.ITCheckgroupService;

/**
 * 检查组Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-08
 */
@Transactional
@Service
public class TCheckgroupServiceImpl implements ITCheckgroupService 
{
    @Autowired
    private TCheckgroupMapper tCheckgroupMapper;
    @Autowired
    private TCheckgroupCheckitemMapper tCheckgroupCheckitemMapper;
    @Autowired
    private TSetmealCheckgroupMapper tSetmealCheckgroupMapper;

    /**
     * 查询检查组
     * 
     * @param id 检查组主键
     * @return 检查组
     */
    @Override
    public TCheckgroupVo selectTCheckgroupById(Long id)
    {
        // 1. 查询检查组基本信息
        TCheckgroup tCheckgroup = tCheckgroupMapper.selectTCheckgroupById(id);
        if (tCheckgroup == null) {
            return null;
        }
        // 2. 根据检查组 id 查询关联的检查项 id 列表
        List<Long> list = tCheckgroupCheckitemMapper.selectCheckitemIds(id);
        // 3. 封装 VO 并返回
        TCheckgroupVo vo = new TCheckgroupVo();
        BeanUtils.copyProperties(tCheckgroup, vo);
        vo.setCheckItemIds(list);
        return vo;
    }

    /**
     * 查询检查组列表
     * 
     * @param tCheckgroup 检查组
     * @return 检查组
     */
    @Override
    public List<TCheckgroup> selectTCheckgroupList(TCheckgroup tCheckgroup)
    {
        return tCheckgroupMapper.selectTCheckgroupList(tCheckgroup);
    }

    /**
     * 新增检查组
     * 
     * @param tCheckgroup 检查组
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTCheckgroup(TCheckgroupDto dto)
    {
        dto.setCreateTime(DateUtils.getNowDate());
        int count = tCheckgroupMapper.insertTCheckgroup(dto);
        // 如果传入了检查项关联列表，批量插入到中间表
        if (dto.getCheckItemIds() != null && dto.getCheckItemIds().size() > 0) {
            tCheckgroupCheckitemMapper.insertCheckitemIds(dto.getCheckItemIds(), dto.getId());
        }
        return count;
    }

    /**
     * 修改检查组
     * 
     * @param dto 检查组
     * @return 结果
     */
    @Override
    public int updateTCheckgroup(TCheckgroupDto dto)
    {
        try {
            //修改检查组
            dto.setUpdateTime(DateUtils.getNowDate());

            if (dto.getCheckItemIds()!=null&&dto.getCheckItemIds().size()>0){
                //删除检查组与检查项关联关系
                tCheckgroupCheckitemMapper.deleteTCheckgroupCheckitemByCheckgroupId(dto.getId());
                //添加新的检查项与检查项关联关系
                tCheckgroupCheckitemMapper.insertCheckitemIds(dto.getCheckItemIds(), dto.getId());
            }

            return tCheckgroupMapper.updateTCheckgroup(dto);
        }catch (BeansException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量删除检查组
     * 
     * @param ids 需要删除的检查组主键
     * @return 结果
     */
    @Override
    public int deleteTCheckgroupByIds(Long[] ids)
    {
        return tCheckgroupMapper.deleteTCheckgroupByIds(ids);
    }

    /**
     * 删除检查组信息
     * 
     * @param id 检查组主键
     * @return 结果
     */
    @Override
    public int deleteTCheckgroupById(Long id)
    {
        //1.判断检查组是否被套餐关联
        int count = tSetmealCheckgroupMapper.selectCountByCheckgroupId(id);
        if (count > 0){
            throw new RuntimeException("当前检查组被套餐关联，不能删除");
        }
        //2.删除检查组和检查项的关联关系
        tCheckgroupCheckitemMapper.deleteTCheckgroupCheckitemByCheckgroupId(id);
        //3.删除检查组信息
        return tCheckgroupMapper.deleteTCheckgroupById(id);
    }

    /**
     * 查询所有检查组
     * 
     * @return 检查组集合
     */
    @Override
    public List<TCheckgroup> selectAllTCheckgroup()
    {
        return tCheckgroupMapper.selectTCheckgroupList(new TCheckgroup());
    }
}
