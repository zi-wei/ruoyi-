package com.health.shetuan.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.shetuan.mapper.TClubMemberMapper;
import com.health.shetuan.domain.TClubMember;
import com.health.shetuan.service.ITClubMemberService;

/**
 * 社团成员Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class TClubMemberServiceImpl implements ITClubMemberService 
{
    @Autowired
    private TClubMemberMapper tClubMemberMapper;

    /**
     * 查询社团成员
     * 
     * @param memberId 社团成员主键
     * @return 社团成员
     */
    @Override
    public TClubMember selectTClubMemberByMemberId(Long memberId)
    {
        return tClubMemberMapper.selectTClubMemberByMemberId(memberId);
    }

    /**
     * 查询社团成员列表
     * 
     * @param tClubMember 社团成员
     * @return 社团成员
     */
    @Override
    public List<TClubMember> selectTClubMemberList(TClubMember tClubMember)
    {
        return tClubMemberMapper.selectTClubMemberList(tClubMember);
    }

    /**
     * 新增社团成员
     * 
     * @param tClubMember 社团成员
     * @return 结果
     */
    @Override
    public int insertTClubMember(TClubMember tClubMember)
    {
        tClubMember.setCreateTime(DateUtils.getNowDate());
        return tClubMemberMapper.insertTClubMember(tClubMember);
    }

    /**
     * 修改社团成员
     * 
     * @param tClubMember 社团成员
     * @return 结果
     */
    @Override
    public int updateTClubMember(TClubMember tClubMember)
    {
        tClubMember.setUpdateTime(DateUtils.getNowDate());
        return tClubMemberMapper.updateTClubMember(tClubMember);
    }

    /**
     * 批量删除社团成员
     * 
     * @param memberIds 需要删除的社团成员主键
     * @return 结果
     */
    @Override
    public int deleteTClubMemberByMemberIds(Long[] memberIds)
    {
        return tClubMemberMapper.deleteTClubMemberByMemberIds(memberIds);
    }

    /**
     * 删除社团成员信息
     * 
     * @param memberId 社团成员主键
     * @return 结果
     */
    @Override
    public int deleteTClubMemberByMemberId(Long memberId)
    {
        return tClubMemberMapper.deleteTClubMemberByMemberId(memberId);
    }
}
