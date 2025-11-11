package com.health.wanwan.mapper;

import java.util.List;
import com.health.wanwan.domain.PlayOrder;

/**
 * 陪玩专区-订单Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface PlayOrderMapper 
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
     * 删除陪玩专区-订单
     * 
     * @param orderId 陪玩专区-订单主键
     * @return 结果
     */
    public int deletePlayOrderByOrderId(Long orderId);

    /**
     * 批量删除陪玩专区-订单
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePlayOrderByOrderIds(Long[] orderIds);
}
