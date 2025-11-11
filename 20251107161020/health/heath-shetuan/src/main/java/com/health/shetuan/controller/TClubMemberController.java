package com.health.shetuan.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.health.common.annotation.Log;
import com.health.common.core.controller.BaseController;
import com.health.common.core.domain.AjaxResult;
import com.health.common.enums.BusinessType;
import com.health.shetuan.domain.TClubMember;
import com.health.shetuan.service.ITClubMemberService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 社团成员Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/shetuan/member")
public class TClubMemberController extends BaseController
{
    @Autowired
    private ITClubMemberService tClubMemberService;

    /**
     * 查询社团成员列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(TClubMember tClubMember)
    {
        startPage();
        List<TClubMember> list = tClubMemberService.selectTClubMemberList(tClubMember);
        return getDataTable(list);
    }

    /**
     * 导出社团成员列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:member:export')")
    @Log(title = "社团成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TClubMember tClubMember)
    {
        List<TClubMember> list = tClubMemberService.selectTClubMemberList(tClubMember);
        ExcelUtil<TClubMember> util = new ExcelUtil<TClubMember>(TClubMember.class);
        util.exportExcel(response, list, "社团成员数据");
    }

    /**
     * 获取社团成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:member:query')")
    @GetMapping(value = "/{memberId}")
    public AjaxResult getInfo(@PathVariable("memberId") Long memberId)
    {
        return success(tClubMemberService.selectTClubMemberByMemberId(memberId));
    }

    /**
     * 新增社团成员
     */
    @PreAuthorize("@ss.hasPermi('shetuan:member:add')")
    @Log(title = "社团成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TClubMember tClubMember)
    {
        return toAjax(tClubMemberService.insertTClubMember(tClubMember));
    }

    /**
     * 修改社团成员
     */
    @PreAuthorize("@ss.hasPermi('shetuan:member:edit')")
    @Log(title = "社团成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TClubMember tClubMember)
    {
        return toAjax(tClubMemberService.updateTClubMember(tClubMember));
    }

    /**
     * 删除社团成员
     */
    @PreAuthorize("@ss.hasPermi('shetuan:member:remove')")
    @Log(title = "社团成员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{memberIds}")
    public AjaxResult remove(@PathVariable Long[] memberIds)
    {
        return toAjax(tClubMemberService.deleteTClubMemberByMemberIds(memberIds));
    }
}
