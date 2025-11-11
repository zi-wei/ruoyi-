package com.health.wanwan.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.wanwan.mapper.PlayOrderMapper;
import com.health.wanwan.domain.PlayOrder;
import com.health.wanwan.service.IPlayOrderService;

/**
 * 陪玩专区-订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class PlayOrderServiceImpl implements IPlayOrderService 
{
    @Autowired
    private PlayOrderMapper playOrderMapper;

    /**
     * 查询陪玩专区-订单
     * 
     * @param orderId 陪玩专区-订单主键
     * @return 陪玩专区-订单
     */
    @Override
    public PlayOrder selectPlayOrderByOrderId(Long orderId)
    {
        return playOrderMapper.selectPlayOrderByOrderId(orderId);
    }

    /**
     * 查询陪玩专区-订单列表
     * 
     * @param playOrder 陪玩专区-订单
     * @return 陪玩专区-订单
     */
    @Override
    public List<PlayOrder> selectPlayOrderList(PlayOrder playOrder)
    {
        return playOrderMapper.selectPlayOrderList(playOrder);
    }

    /**
     * 新增陪玩专区-订单
     * 
     * @param playOrder 陪玩专区-订单
     * @return 结果
     */
    @Override
    public int insertPlayOrder(PlayOrder playOrder)
    {
        playOrder.setCreateTime(DateUtils.getNowDate());
        return playOrderMapper.insertPlayOrder(playOrder);
    }

    /**
     * 修改陪玩专区-订单
     * 
     * @param playOrder 陪玩专区-订单
     * @return 结果
     */
    @Override
    public int updatePlayOrder(PlayOrder playOrder)
    {
        playOrder.setUpdateTime(DateUtils.getNowDate());
        return playOrderMapper.updatePlayOrder(playOrder);
    }

    /**
     * 批量删除陪玩专区-订单
     * 
     * @param orderIds 需要删除的陪玩专区-订单主键
     * @return 结果
     */
    @Override
    public int deletePlayOrderByOrderIds(Long[] orderIds)
    {
        return playOrderMapper.deletePlayOrderByOrderIds(orderIds);
    }

    /**
     * 删除陪玩专区-订单信息
     * 
     * @param orderId 陪玩专区-订单主键
     * @return 结果
     */
    @Override
    public int deletePlayOrderByOrderId(Long orderId)
    {
        return playOrderMapper.deletePlayOrderByOrderId(orderId);
    }
}
