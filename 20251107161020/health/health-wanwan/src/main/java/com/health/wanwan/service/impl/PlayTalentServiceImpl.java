package com.health.wanwan.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.wanwan.mapper.PlayTalentMapper;
import com.health.wanwan.domain.PlayTalent;
import com.health.wanwan.service.IPlayTalentService;

/**
 * 陪玩专区-人才库Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class PlayTalentServiceImpl implements IPlayTalentService 
{
    @Autowired
    private PlayTalentMapper playTalentMapper;

    /**
     * 查询陪玩专区-人才库
     * 
     * @param talentId 陪玩专区-人才库主键
     * @return 陪玩专区-人才库
     */
    @Override
    public PlayTalent selectPlayTalentByTalentId(Long talentId)
    {
        return playTalentMapper.selectPlayTalentByTalentId(talentId);
    }

    /**
     * 查询陪玩专区-人才库列表
     * 
     * @param playTalent 陪玩专区-人才库
     * @return 陪玩专区-人才库
     */
    @Override
    public List<PlayTalent> selectPlayTalentList(PlayTalent playTalent)
    {
        return playTalentMapper.selectPlayTalentList(playTalent);
    }

    /**
     * 新增陪玩专区-人才库
     * 
     * @param playTalent 陪玩专区-人才库
     * @return 结果
     */
    @Override
    public int insertPlayTalent(PlayTalent playTalent)
    {
        playTalent.setCreateTime(DateUtils.getNowDate());
        return playTalentMapper.insertPlayTalent(playTalent);
    }

    /**
     * 修改陪玩专区-人才库
     * 
     * @param playTalent 陪玩专区-人才库
     * @return 结果
     */
    @Override
    public int updatePlayTalent(PlayTalent playTalent)
    {
        playTalent.setUpdateTime(DateUtils.getNowDate());
        return playTalentMapper.updatePlayTalent(playTalent);
    }

    /**
     * 批量删除陪玩专区-人才库
     * 
     * @param talentIds 需要删除的陪玩专区-人才库主键
     * @return 结果
     */
    @Override
    public int deletePlayTalentByTalentIds(Long[] talentIds)
    {
        return playTalentMapper.deletePlayTalentByTalentIds(talentIds);
    }

    /**
     * 删除陪玩专区-人才库信息
     * 
     * @param talentId 陪玩专区-人才库主键
     * @return 结果
     */
    @Override
    public int deletePlayTalentByTalentId(Long talentId)
    {
        return playTalentMapper.deletePlayTalentByTalentId(talentId);
    }
}
