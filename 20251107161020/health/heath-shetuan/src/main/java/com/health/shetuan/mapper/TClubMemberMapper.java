package com.health.shetuan.mapper;

import java.util.List;
import com.health.shetuan.domain.TClubMember;

/**
 * 社团成员Mapper接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface TClubMemberMapper 
{
    /**
     * 查询社团成员
     * 
     * @param memberId 社团成员主键
     * @return 社团成员
     */
    public TClubMember selectTClubMemberByMemberId(Long memberId);

    /**
     * 查询社团成员列表
     * 
     * @param tClubMember 社团成员
     * @return 社团成员集合
     */
    public List<TClubMember> selectTClubMemberList(TClubMember tClubMember);

    /**
     * 新增社团成员
     * 
     * @param tClubMember 社团成员
     * @return 结果
     */
    public int insertTClubMember(TClubMember tClubMember);

    /**
     * 修改社团成员
     * 
     * @param tClubMember 社团成员
     * @return 结果
     */
    public int updateTClubMember(TClubMember tClubMember);

    /**
     * 删除社团成员
     * 
     * @param memberId 社团成员主键
     * @return 结果
     */
    public int deleteTClubMemberByMemberId(Long memberId);

    /**
     * 批量删除社团成员
     * 
     * @param memberIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTClubMemberByMemberIds(Long[] memberIds);
}
