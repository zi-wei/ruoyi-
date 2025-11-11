package com.health.wanwan.mapper;

import java.util.List;
import com.health.wanwan.domain.PlayTalent;

/**
 * 陪玩专区-人才库Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface PlayTalentMapper 
{
    /**
     * 查询陪玩专区-人才库
     * 
     * @param talentId 陪玩专区-人才库主键
     * @return 陪玩专区-人才库
     */
    public PlayTalent selectPlayTalentByTalentId(Long talentId);

    /**
     * 查询陪玩专区-人才库列表
     * 
     * @param playTalent 陪玩专区-人才库
     * @return 陪玩专区-人才库集合
     */
    public List<PlayTalent> selectPlayTalentList(PlayTalent playTalent);

    /**
     * 新增陪玩专区-人才库
     * 
     * @param playTalent 陪玩专区-人才库
     * @return 结果
     */
    public int insertPlayTalent(PlayTalent playTalent);

    /**
     * 修改陪玩专区-人才库
     * 
     * @param playTalent 陪玩专区-人才库
     * @return 结果
     */
    public int updatePlayTalent(PlayTalent playTalent);

    /**
     * 删除陪玩专区-人才库
     * 
     * @param talentId 陪玩专区-人才库主键
     * @return 结果
     */
    public int deletePlayTalentByTalentId(Long talentId);

    /**
     * 批量删除陪玩专区-人才库
     * 
     * @param talentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePlayTalentByTalentIds(Long[] talentIds);
}
