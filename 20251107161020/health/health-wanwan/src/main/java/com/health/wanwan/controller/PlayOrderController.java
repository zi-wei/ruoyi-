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
import com.health.wanwan.domain.PlayOrder;
import com.health.wanwan.service.IPlayOrderService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 陪玩专区-订单Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/wanwan/order")
public class PlayOrderController extends BaseController
{
    @Autowired
    private IPlayOrderService playOrderService;

    /**
     * 查询陪玩专区-订单列表
     */
    @PreAuthorize("@ss.hasPermi('wanwan:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(PlayOrder playOrder)
    {
        startPage();
        List<PlayOrder> list = playOrderService.selectPlayOrderList(playOrder);
        return getDataTable(list);
    }

    /**
     * 导出陪玩专区-订单列表
     */
    @PreAuthorize("@ss.hasPermi('wanwan:order:export')")
    @Log(title = "陪玩专区-订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PlayOrder playOrder)
    {
        List<PlayOrder> list = playOrderService.selectPlayOrderList(playOrder);
        ExcelUtil<PlayOrder> util = new ExcelUtil<PlayOrder>(PlayOrder.class);
        util.exportExcel(response, list, "陪玩专区-订单数据");
    }

    /**
     * 获取陪玩专区-订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('wanwan:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(playOrderService.selectPlayOrderByOrderId(orderId));
    }

    /**
     * 新增陪玩专区-订单
     */
    @PreAuthorize("@ss.hasPermi('wanwan:order:add')")
    @Log(title = "陪玩专区-订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlayOrder playOrder)
    {
        return toAjax(playOrderService.insertPlayOrder(playOrder));
    }

    /**
     * 修改陪玩专区-订单
     */
    @PreAuthorize("@ss.hasPermi('wanwan:order:edit')")
    @Log(title = "陪玩专区-订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlayOrder playOrder)
    {
        return toAjax(playOrderService.updatePlayOrder(playOrder));
    }

    /**
     * 删除陪玩专区-订单
     */
    @PreAuthorize("@ss.hasPermi('wanwan:order:remove')")
    @Log(title = "陪玩专区-订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(playOrderService.deletePlayOrderByOrderIds(orderIds));
    }
}
