package com.health.wanwan.controller;

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
import com.health.wanwan.domain.PlayTalent;
import com.health.wanwan.service.IPlayTalentService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 陪玩专区-人才库Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/wanwan/talent")
public class PlayTalentController extends BaseController
{
    @Autowired
    private IPlayTalentService playTalentService;

    /**
     * 查询陪玩专区-人才库列表
     */
    @PreAuthorize("@ss.hasPermi('wanwan:talent:list')")
    @GetMapping("/list")
    public TableDataInfo list(PlayTalent playTalent)
    {
        startPage();
        List<PlayTalent> list = playTalentService.selectPlayTalentList(playTalent);
        return getDataTable(list);
    }

    /**
     * 导出陪玩专区-人才库列表
     */
    @PreAuthorize("@ss.hasPermi('wanwan:talent:export')")
    @Log(title = "陪玩专区-人才库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PlayTalent playTalent)
    {
        List<PlayTalent> list = playTalentService.selectPlayTalentList(playTalent);
        ExcelUtil<PlayTalent> util = new ExcelUtil<PlayTalent>(PlayTalent.class);
        util.exportExcel(response, list, "陪玩专区-人才库数据");
    }

    /**
     * 获取陪玩专区-人才库详细信息
     */
    @PreAuthorize("@ss.hasPermi('wanwan:talent:query')")
    @GetMapping(value = "/{talentId}")
    public AjaxResult getInfo(@PathVariable("talentId") Long talentId)
    {
        return success(playTalentService.selectPlayTalentByTalentId(talentId));
    }

    /**
     * 新增陪玩专区-人才库
     */
    @PreAuthorize("@ss.hasPermi('wanwan:talent:add')")
    @Log(title = "陪玩专区-人才库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlayTalent playTalent)
    {
        return toAjax(playTalentService.insertPlayTalent(playTalent));
    }

    /**
     * 修改陪玩专区-人才库
     */
    @PreAuthorize("@ss.hasPermi('wanwan:talent:edit')")
    @Log(title = "陪玩专区-人才库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlayTalent playTalent)
    {
        return toAjax(playTalentService.updatePlayTalent(playTalent));
    }

    /**
     * 删除陪玩专区-人才库
     */
    @PreAuthorize("@ss.hasPermi('wanwan:talent:remove')")
    @Log(title = "陪玩专区-人才库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{talentIds}")
    public AjaxResult remove(@PathVariable Long[] talentIds)
    {
        return toAjax(playTalentService.deletePlayTalentByTalentIds(talentIds));
    }
}
