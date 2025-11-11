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
import com.health.shetuan.domain.TResourceBooking;
import com.health.shetuan.service.ITResourceBookingService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 资源预约Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/shetuan/booking")
public class TResourceBookingController extends BaseController
{
    @Autowired
    private ITResourceBookingService tResourceBookingService;

    /**
     * 查询资源预约列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:booking:list')")
    @GetMapping("/list")
    public TableDataInfo list(TResourceBooking tResourceBooking)
    {
        startPage();
        List<TResourceBooking> list = tResourceBookingService.selectTResourceBookingList(tResourceBooking);
        return getDataTable(list);
    }

    /**
     * 导出资源预约列表
     */
    @PreAuthorize("@ss.hasPermi('shetuan:booking:export')")
    @Log(title = "资源预约", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TResourceBooking tResourceBooking)
    {
        List<TResourceBooking> list = tResourceBookingService.selectTResourceBookingList(tResourceBooking);
        ExcelUtil<TResourceBooking> util = new ExcelUtil<TResourceBooking>(TResourceBooking.class);
        util.exportExcel(response, list, "资源预约数据");
    }

    /**
     * 获取资源预约详细信息
     */
    @PreAuthorize("@ss.hasPermi('shetuan:booking:query')")
    @GetMapping(value = "/{bookingId}")
    public AjaxResult getInfo(@PathVariable("bookingId") Long bookingId)
    {
        return success(tResourceBookingService.selectTResourceBookingByBookingId(bookingId));
    }

    /**
     * 新增资源预约
     */
    @PreAuthorize("@ss.hasPermi('shetuan:booking:add')")
    @Log(title = "资源预约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TResourceBooking tResourceBooking)
    {
        return toAjax(tResourceBookingService.insertTResourceBooking(tResourceBooking));
    }

    /**
     * 修改资源预约
     */
    @PreAuthorize("@ss.hasPermi('shetuan:booking:edit')")
    @Log(title = "资源预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TResourceBooking tResourceBooking)
    {
        return toAjax(tResourceBookingService.updateTResourceBooking(tResourceBooking));
    }

    /**
     * 删除资源预约
     */
    @PreAuthorize("@ss.hasPermi('shetuan:booking:remove')")
    @Log(title = "资源预约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookingIds}")
    public AjaxResult remove(@PathVariable Long[] bookingIds)
    {
        return toAjax(tResourceBookingService.deleteTResourceBookingByBookingIds(bookingIds));
    }
}
