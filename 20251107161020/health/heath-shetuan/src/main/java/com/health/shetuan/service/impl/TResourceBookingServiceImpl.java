package com.health.shetuan.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.shetuan.mapper.TResourceBookingMapper;
import com.health.shetuan.domain.TResourceBooking;
import com.health.shetuan.service.ITResourceBookingService;

/**
 * 资源预约Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class TResourceBookingServiceImpl implements ITResourceBookingService 
{
    @Autowired
    private TResourceBookingMapper tResourceBookingMapper;

    /**
     * 查询资源预约
     * 
     * @param bookingId 资源预约主键
     * @return 资源预约
     */
    @Override
    public TResourceBooking selectTResourceBookingByBookingId(Long bookingId)
    {
        return tResourceBookingMapper.selectTResourceBookingByBookingId(bookingId);
    }

    /**
     * 查询资源预约列表
     * 
     * @param tResourceBooking 资源预约
     * @return 资源预约
     */
    @Override
    public List<TResourceBooking> selectTResourceBookingList(TResourceBooking tResourceBooking)
    {
        return tResourceBookingMapper.selectTResourceBookingList(tResourceBooking);
    }

    /**
     * 新增资源预约
     * 
     * @param tResourceBooking 资源预约
     * @return 结果
     */
    @Override
    public int insertTResourceBooking(TResourceBooking tResourceBooking)
    {
        tResourceBooking.setCreateTime(DateUtils.getNowDate());
        return tResourceBookingMapper.insertTResourceBooking(tResourceBooking);
    }

    /**
     * 修改资源预约
     * 
     * @param tResourceBooking 资源预约
     * @return 结果
     */
    @Override
    public int updateTResourceBooking(TResourceBooking tResourceBooking)
    {
        tResourceBooking.setUpdateTime(DateUtils.getNowDate());
        return tResourceBookingMapper.updateTResourceBooking(tResourceBooking);
    }

    /**
     * 批量删除资源预约
     * 
     * @param bookingIds 需要删除的资源预约主键
     * @return 结果
     */
    @Override
    public int deleteTResourceBookingByBookingIds(Long[] bookingIds)
    {
        return tResourceBookingMapper.deleteTResourceBookingByBookingIds(bookingIds);
    }

    /**
     * 删除资源预约信息
     * 
     * @param bookingId 资源预约主键
     * @return 结果
     */
    @Override
    public int deleteTResourceBookingByBookingId(Long bookingId)
    {
        return tResourceBookingMapper.deleteTResourceBookingByBookingId(bookingId);
    }
}
