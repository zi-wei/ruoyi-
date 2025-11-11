package com.health.shetuan.mapper;

import java.util.List;
import com.health.shetuan.domain.TResourceBooking;

/**
 * 资源预约Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface TResourceBookingMapper 
{
    /**
     * 查询资源预约
     * 
     * @param bookingId 资源预约主键
     * @return 资源预约
     */
    public TResourceBooking selectTResourceBookingByBookingId(Long bookingId);

    /**
     * 查询资源预约列表
     * 
     * @param tResourceBooking 资源预约
     * @return 资源预约集合
     */
    public List<TResourceBooking> selectTResourceBookingList(TResourceBooking tResourceBooking);

    /**
     * 新增资源预约
     * 
     * @param tResourceBooking 资源预约
     * @return 结果
     */
    public int insertTResourceBooking(TResourceBooking tResourceBooking);

    /**
     * 修改资源预约
     * 
     * @param tResourceBooking 资源预约
     * @return 结果
     */
    public int updateTResourceBooking(TResourceBooking tResourceBooking);

    /**
     * 删除资源预约
     * 
     * @param bookingId 资源预约主键
     * @return 结果
     */
    public int deleteTResourceBookingByBookingId(Long bookingId);

    /**
     * 批量删除资源预约
     * 
     * @param bookingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTResourceBookingByBookingIds(Long[] bookingIds);
}
