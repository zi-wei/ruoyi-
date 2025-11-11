package com.health.wanwan.service;

import java.util.List;
import com.health.wanwan.domain.PlayOrder;

/**
 * 陪玩专区-订单Service接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface IPlayOrderService 
{
    /**
     * 查询陪玩专区-订单
     * 
     * @param orderId 陪玩专区-订单主键
     * @return 陪玩专区-订单
     */
    public PlayOrder selectPlayOrderByOrderId(Long orderId);

    /**
     * 查询陪玩专区-订单列表
     * 
     * @param playOrder 陪玩专区-订单
     * @return 陪玩专区-订单集合
     */
    public List<PlayOrder> selectPlayOrderList(PlayOrder playOrder);

    /**
     * 新增陪玩专区-订单
     * 
     * @param playOrder 陪玩专区-订单
     * @return 结果
     */
    public int insertPlayOrder(PlayOrder playOrder);

    /**
     * 修改陪玩专区-订单
     * 
     * @param playOrder 陪玩专区-订单
     * @return 结果
     */
    public int updatePlayOrder(PlayOrder playOrder);

    /**
     * 批量删除陪玩专区-订单
     * 
     * @param orderIds 需要删除的陪玩专区-订单主键集合
     * @return 结果
     */
    public int deletePlayOrderByOrderIds(Long[] orderIds);

    /**
     * 删除陪玩专区-订单信息
     * 
     * @param orderId 陪玩专区-订单主键
     * @return 结果
     */
    public int deletePlayOrderByOrderId(Long orderId);
}
