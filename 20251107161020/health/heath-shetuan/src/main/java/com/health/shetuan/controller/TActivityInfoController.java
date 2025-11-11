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
import com.health.shetuan.domain.TActivityInfo;
import com.health.shetuan.service.ITActivityInfoService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 活动信息Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/shetuan/huodong")
public class TActivityInfoController extends BaseController
{
    @Autowired
    private ITActivityInfoService tActivityInfoService;

    /**
     * 查询活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:huodong:list')")
    @GetMapping("/list")
    public TableDataInfo list(TActivityInfo tActivityInfo)
    {
        startPage();
        List<TActivityInfo> list = tActivityInfoService.selectTActivityInfoList(tActivityInfo);
        return getDataTable(list);
    }

    /**
     * 导出活动信息列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:huodong:export')")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TActivityInfo tActivityInfo)
    {
        List<TActivityInfo> list = tActivityInfoService.selectTActivityInfoList(tActivityInfo);
        ExcelUtil<TActivityInfo> util = new ExcelUtil<TActivityInfo>(TActivityInfo.class);
        util.exportExcel(response, list, "活动信息数据");
    }

    /**
     * 获取活动信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:huodong:query')")
    @GetMapping(value = "/{activityId}")
    public AjaxResult getInfo(@PathVariable("activityId") Long activityId)
    {
        return success(tActivityInfoService.selectTActivityInfoByActivityId(activityId));
    }

    /**
     * 新增活动信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:huodong:add')")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TActivityInfo tActivityInfo)
    {
        return toAjax(tActivityInfoService.insertTActivityInfo(tActivityInfo));
    }

    /**
     * 修改活动信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:huodong:edit')")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TActivityInfo tActivityInfo)
    {
        return toAjax(tActivityInfoService.updateTActivityInfo(tActivityInfo));
    }

    /**
     * 删除活动信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:huodong:remove')")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{activityIds}")
    public AjaxResult remove(@PathVariable Long[] activityIds)
    {
        return toAjax(tActivityInfoService.deleteTActivityInfoByActivityIds(activityIds));
    }
}
