package com.health.hanghang.service;

import java.util.List;
import com.health.hanghang.domain.EscortVoucher;

/**
 * 护航专区-任务凭证Service接口
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
public interface IEscortVoucherService 
{
    /**
     * 查询护航专区-任务凭证
     * 
     * @param voucherId 护航专区-任务凭证主键
     * @return 护航专区-任务凭证
     */
    public EscortVoucher selectEscortVoucherByVoucherId(Long voucherId);

    /**
     * 查询护航专区-任务凭证列表
     * 
     * @param escortVoucher 护航专区-任务凭证
     * @return 护航专区-任务凭证集合
     */
    public List<EscortVoucher> selectEscortVoucherList(EscortVoucher escortVoucher);

    /**
     * 新增护航专区-任务凭证
     * 
     * @param escortVoucher 护航专区-任务凭证
     * @return 结果
     */
    public int insertEscortVoucher(EscortVoucher escortVoucher);

    /**
     * 修改护航专区-任务凭证
     * 
     * @param escortVoucher 护航专区-任务凭证
     * @return 结果
     */
    public int updateEscortVoucher(EscortVoucher escortVoucher);

    /**
     * 批量删除护航专区-任务凭证
     * 
     * @param voucherIds 需要删除的护航专区-任务凭证主键集合
     * @return 结果
     */
    public int deleteEscortVoucherByVoucherIds(Long[] voucherIds);

    /**
     * 删除护航专区-任务凭证信息
     * 
     * @param voucherId 护航专区-任务凭证主键
     * @return 结果
     */
    public int deleteEscortVoucherByVoucherId(Long voucherId);
}
