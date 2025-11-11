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
import com.health.wanwan.domain.PlayReview;
import com.health.wanwan.service.IPlayReviewService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 陪玩专区-评价Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/wanwan/review")
public class PlayReviewController extends BaseController
{
    @Autowired
    private IPlayReviewService playReviewService;

    /**
     * 查询陪玩专区-评价列表
     */
    @PreAuthorize("@ss.hasPermi('wanwan:review:list')")
    @GetMapping("/list")
    public TableDataInfo list(PlayReview playReview)
    {
        startPage();
        List<PlayReview> list = playReviewService.selectPlayReviewList(playReview);
        return getDataTable(list);
    }

    /**
     * 导出陪玩专区-评价列表
     */
    @PreAuthorize("@ss.hasPermi('wanwan:review:export')")
    @Log(title = "陪玩专区-评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PlayReview playReview)
    {
        List<PlayReview> list = playReviewService.selectPlayReviewList(playReview);
        ExcelUtil<PlayReview> util = new ExcelUtil<PlayReview>(PlayReview.class);
        util.exportExcel(response, list, "陪玩专区-评价数据");
    }

    /**
     * 获取陪玩专区-评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('wanwan:review:query')")
    @GetMapping(value = "/{reviewId}")
    public AjaxResult getInfo(@PathVariable("reviewId") Long reviewId)
    {
        return success(playReviewService.selectPlayReviewByReviewId(reviewId));
    }

    /**
     * 新增陪玩专区-评价
     */
    @PreAuthorize("@ss.hasPermi('wanwan:review:add')")
    @Log(title = "陪玩专区-评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlayReview playReview)
    {
        return toAjax(playReviewService.insertPlayReview(playReview));
    }

    /**
     * 修改陪玩专区-评价
     */
    @PreAuthorize("@ss.hasPermi('wanwan:review:edit')")
    @Log(title = "陪玩专区-评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlayReview playReview)
    {
        return toAjax(playReviewService.updatePlayReview(playReview));
    }

    /**
     * 删除陪玩专区-评价
     */
    @PreAuthorize("@ss.hasPermi('wanwan:review:remove')")
    @Log(title = "陪玩专区-评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reviewIds}")
    public AjaxResult remove(@PathVariable Long[] reviewIds)
    {
        return toAjax(playReviewService.deletePlayReviewByReviewIds(reviewIds));
    }
}
