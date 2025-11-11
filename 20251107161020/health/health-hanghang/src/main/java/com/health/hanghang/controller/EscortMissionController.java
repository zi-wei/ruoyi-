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
import com.health.common.enums.BusinessType;
import com.health.hanghang.domain.EscortMission;
import com.health.hanghang.service.IEscortMissionService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 护航专区-任务Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/hanghang/mission")
public class EscortMissionController extends BaseController
{
    @Autowired
    private IEscortMissionService escortMissionService;

    /**
     * 查询护航专区-任务列表
     */
    @PreAuthorize("@ss.hasPermi('hanghang:mission:list')")
    @GetMapping("/list")
    public TableDataInfo list(EscortMission escortMission)
    {
        startPage();
        List<EscortMission> list = escortMissionService.selectEscortMissionList(escortMission);
        return getDataTable(list);
    }

    /**
     * 导出护航专区-任务列表
     */
    @PreAuthorize("@ss.hasPermi('hanghang:mission:export')")
    @Log(title = "护航专区-任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EscortMission escortMission)
    {
        List<EscortMission> list = escortMissionService.selectEscortMissionList(escortMission);
        ExcelUtil<EscortMission> util = new ExcelUtil<EscortMission>(EscortMission.class);
        util.exportExcel(response, list, "护航专区-任务数据");
    }

    /**
     * 获取护航专区-任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('hanghang:mission:query')")
    @GetMapping(value = "/{missionId}")
    public AjaxResult getInfo(@PathVariable("missionId") Long missionId)
    {
        return success(escortMissionService.selectEscortMissionByMissionId(missionId));
    }

    /**
     * 新增护航专区-任务
     */
    @PreAuthorize("@ss.hasPermi('hanghang:mission:add')")
    @Log(title = "护航专区-任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EscortMission escortMission)
    {
        return toAjax(escortMissionService.insertEscortMission(escortMission));
    }

    /**
     * 修改护航专区-任务
     */
    @PreAuthorize("@ss.hasPermi('hanghang:mission:edit')")
    @Log(title = "护航专区-任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EscortMission escortMission)
    {
        return toAjax(escortMissionService.updateEscortMission(escortMission));
    }

    /**
     * 删除护航专区-任务
     */
    @PreAuthorize("@ss.hasPermi('hanghang:mission:remove')")
    @Log(title = "护航专区-任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{missionIds}")
    public AjaxResult remove(@PathVariable Long[] missionIds)
    {
        return toAjax(escortMissionService.deleteEscortMissionByMissionIds(missionIds));
    }
}
