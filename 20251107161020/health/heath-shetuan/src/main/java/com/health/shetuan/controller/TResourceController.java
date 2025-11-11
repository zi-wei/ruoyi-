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
import com.health.shetuan.domain.TResource;
import com.health.shetuan.service.ITResourceService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 资源信息Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/shetuan/resource")
public class TResourceController extends BaseController
{
    @Autowired
    private ITResourceService tResourceService;

    /**
     * 查询资源信息列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(TResource tResource)
    {
        startPage();
        List<TResource> list = tResourceService.selectTResourceList(tResource);
        return getDataTable(list);
    }

    /**
     * 导出资源信息列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:resource:export')")
    @Log(title = "资源信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TResource tResource)
    {
        List<TResource> list = tResourceService.selectTResourceList(tResource);
        ExcelUtil<TResource> util = new ExcelUtil<TResource>(TResource.class);
        util.exportExcel(response, list, "资源信息数据");
    }

    /**
     * 获取资源信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:resource:query')")
    @GetMapping(value = "/{resourceId}")
    public AjaxResult getInfo(@PathVariable("resourceId") Long resourceId)
    {
        return success(tResourceService.selectTResourceByResourceId(resourceId));
    }

    /**
     * 新增资源信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:resource:add')")
    @Log(title = "资源信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TResource tResource)
    {
        return toAjax(tResourceService.insertTResource(tResource));
    }

    /**
     * 修改资源信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:resource:edit')")
    @Log(title = "资源信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TResource tResource)
    {
        return toAjax(tResourceService.updateTResource(tResource));
    }

    /**
     * 删除资源信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:resource:remove')")
    @Log(title = "资源信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{resourceIds}")
    public AjaxResult remove(@PathVariable Long[] resourceIds)
    {
        return toAjax(tResourceService.deleteTResourceByResourceIds(resourceIds));
    }
}
