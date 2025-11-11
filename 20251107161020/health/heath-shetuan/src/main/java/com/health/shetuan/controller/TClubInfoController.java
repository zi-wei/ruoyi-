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
import com.health.shetuan.domain.TClubInfo;
import com.health.shetuan.service.ITClubInfoService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 社团信息Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/shetuan/info")
public class TClubInfoController extends BaseController
{
    @Autowired
    private ITClubInfoService tClubInfoService;

    /**
     * 查询社团信息列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(TClubInfo tClubInfo)
    {
        startPage();
        List<TClubInfo> list = tClubInfoService.selectTClubInfoList(tClubInfo);
        return getDataTable(list);
    }

    /**
     * 导出社团信息列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:info:export')")
    @Log(title = "社团信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TClubInfo tClubInfo)
    {
        List<TClubInfo> list = tClubInfoService.selectTClubInfoList(tClubInfo);
        ExcelUtil<TClubInfo> util = new ExcelUtil<TClubInfo>(TClubInfo.class);
        util.exportExcel(response, list, "社团信息数据");
    }

    /**
     * 获取社团信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:info:query')")
    @GetMapping(value = "/{clubId}")
    public AjaxResult getInfo(@PathVariable("clubId") Long clubId)
    {
        return success(tClubInfoService.selectTClubInfoByClubId(clubId));
    }

    /**
     * 新增社团信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:info:add')")
    @Log(title = "社团信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TClubInfo tClubInfo)
    {
        return toAjax(tClubInfoService.insertTClubInfo(tClubInfo));
    }

    /**
     * 修改社团信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:info:edit')")
    @Log(title = "社团信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TClubInfo tClubInfo)
    {
        return toAjax(tClubInfoService.updateTClubInfo(tClubInfo));
    }

    /**
     * 删除社团信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:info:remove')")
    @Log(title = "社团信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{clubIds}")
    public AjaxResult remove(@PathVariable Long[] clubIds)
    {
        return toAjax(tClubInfoService.deleteTClubInfoByClubIds(clubIds));
    }
}
