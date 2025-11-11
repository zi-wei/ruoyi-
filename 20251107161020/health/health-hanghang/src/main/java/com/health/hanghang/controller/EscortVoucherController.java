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
import com.health.hanghang.domain.EscortVoucher;
import com.health.hanghang.service.IEscortVoucherService;
import com.health.common.utils.poi.ExcelUtil;
import com.health.common.core.page.TableDataInfo;

/**
 * 护航专区-任务凭证Controller
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@RestController
@RequestMapping("/hanghang/voucher")
public class EscortVoucherController extends BaseController
{
    @Autowired
    private IEscortVoucherService escortVoucherService;

    /**
     * 查询护航专区-任务凭证列表
     */
    @PreAuthorize("@ss.hasPermi('hanghang:voucher:list')")
    @GetMapping("/list")
    public TableDataInfo list(EscortVoucher escortVoucher)
    {
        startPage();
        List<EscortVoucher> list = escortVoucherService.selectEscortVoucherList(escortVoucher);
        return getDataTable(list);
    }

    /**
     * 导出护航专区-任务凭证列表
     */
    @PreAuthorize("@ss.hasPermi('hanghang:voucher:export')")
    @Log(title = "护航专区-任务凭证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EscortVoucher escortVoucher)
    {
        List<EscortVoucher> list = escortVoucherService.selectEscortVoucherList(escortVoucher);
        ExcelUtil<EscortVoucher> util = new ExcelUtil<EscortVoucher>(EscortVoucher.class);
        util.exportExcel(response, list, "护航专区-任务凭证数据");
    }

    /**
     * 获取护航专区-任务凭证详细信息
     */
    @PreAuthorize("@ss.hasPermi('hanghang:voucher:query')")
    @GetMapping(value = "/{voucherId}")
    public AjaxResult getInfo(@PathVariable("voucherId") Long voucherId)
    {
        return success(escortVoucherService.selectEscortVoucherByVoucherId(voucherId));
    }

    /**
     * 新增护航专区-任务凭证
     */
    @PreAuthorize("@ss.hasPermi('hanghang:voucher:add')")
    @Log(title = "护航专区-任务凭证", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EscortVoucher escortVoucher)
    {
        return toAjax(escortVoucherService.insertEscortVoucher(escortVoucher));
    }

    /**
     * 修改护航专区-任务凭证
     */
    @PreAuthorize("@ss.hasPermi('hanghang:voucher:edit')")
    @Log(title = "护航专区-任务凭证", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EscortVoucher escortVoucher)
    {
        return toAjax(escortVoucherService.updateEscortVoucher(escortVoucher));
    }

    /**
     * 删除护航专区-任务凭证
     */
    @PreAuthorize("@ss.hasPermi('hanghang:voucher:remove')")
    @Log(title = "护航专区-任务凭证", businessType = BusinessType.DELETE)
	@DeleteMapping("/{voucherIds}")
    public AjaxResult remove(@PathVariable Long[] voucherIds)
    {
        return toAjax(escortVoucherService.deleteEscortVoucherByVoucherIds(voucherIds));
    }
}
