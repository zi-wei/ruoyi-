package com.health.hanghang.controller;

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
import com.health.common.core.page.TableDataInfo;
import com.health.common.enums.BusinessType;
import com.health.hanghang.domain.EscortSpecialist;
import com.health.hanghang.service.IEscortSpecialistService;
import com.health.common.utils.SecurityUtils;
import com.health.common.utils.poi.ExcelUtil;

/**
 * 护航专区-打手Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/hanghang/specialist")
public class EscortSpecialistController extends BaseController
{
    @Autowired
    private IEscortSpecialistService escortSpecialistService;

    /**
     * 查询护航专区-打手列表
     */
    @PreAuthorize("@ss.hasPermi('hanghang:specialist:list')")
    @GetMapping("/list")
    public TableDataInfo list(EscortSpecialist escortSpecialist)
    {
        startPage();
        List<EscortSpecialist> list = escortSpecialistService.selectEscortSpecialistList(escortSpecialist);
        return getDataTable(list);
    }

    /**
     * 导出护航专区-打手列表
     */
    @PreAuthorize("@ss.hasPermi('hanghang:specialist:export')")
    @Log(title = "护航专区-打手", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EscortSpecialist escortSpecialist)
    {
        List<EscortSpecialist> list = escortSpecialistService.selectEscortSpecialistList(escortSpecialist);
        ExcelUtil<EscortSpecialist> util = new ExcelUtil<EscortSpecialist>(EscortSpecialist.class);
        util.exportExcel(response, list, "护航专区-打手数据");
    }

    /**
     * 获取护航专区-打手详细信息
     */
    @PreAuthorize("@ss.hasPermi('hanghang:specialist:query')")
    @GetMapping(value = "/{specialistId}")
    public AjaxResult getInfo(@PathVariable("specialistId") Long specialistId)
    {
        return success(escortSpecialistService.selectEscortSpecialistBySpecialistId(specialistId));
    }

    /**
     * 新增护航专区-打手
     */
    @PreAuthorize("@ss.hasPermi('hanghang:specialist:add')")
    @Log(title = "护航专区-打手", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EscortSpecialist escortSpecialist)
    {
        escortSpecialist.setUserId(SecurityUtils.getUserId());
        return toAjax(escortSpecialistService.insertEscortSpecialist(escortSpecialist));
    }

    /**
     * 修改护航专区-打手
     */
    @PreAuthorize("@ss.hasPermi('hanghang:specialist:edit')")
    @Log(title = "护航专区-打手", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EscortSpecialist escortSpecialist)
    {
        return toAjax(escortSpecialistService.updateEscortSpecialist(escortSpecialist));
    }

    /**
     * 删除护航专区-打手
     */
    @PreAuthorize("@ss.hasPermi('hanghang:specialist:remove')")
    @Log(title = "护航专区-打手", businessType = BusinessType.DELETE)
	@DeleteMapping("/{specialistIds}")
    public AjaxResult remove(@PathVariable Long[] specialistIds)
    {
        return toAjax(escortSpecialistService.deleteEscortSpecialistBySpecialistIds(specialistIds));
    }
}
