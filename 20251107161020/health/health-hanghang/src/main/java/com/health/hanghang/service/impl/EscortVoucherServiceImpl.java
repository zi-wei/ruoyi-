package com.health.hanghang.service.impl;

import java.util.List;
import com.health.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.health.hanghang.mapper.EscortVoucherMapper;
import com.health.hanghang.domain.EscortVoucher;
import com.health.hanghang.service.IEscortVoucherService;

/**
 * 护航专区-任务凭证Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-10
 */
@Service
public class EscortVoucherServiceImpl implements IEscortVoucherService 
{
    @Autowired
    private EscortVoucherMapper escortVoucherMapper;

    /**
     * 查询护航专区-任务凭证
     * 
     * @param voucherId 护航专区-任务凭证主键
     * @return 护航专区-任务凭证
     */
    @Override
    public EscortVoucher selectEscortVoucherByVoucherId(Long voucherId)
    {
        return escortVoucherMapper.selectEscortVoucherByVoucherId(voucherId);
    }

    /**
     * 查询护航专区-任务凭证列表
     * 
     * @param escortVoucher 护航专区-任务凭证
     * @return 护航专区-任务凭证
     */
    @Override
    public List<EscortVoucher> selectEscortVoucherList(EscortVoucher escortVoucher)
    {
        return escortVoucherMapper.selectEscortVoucherList(escortVoucher);
    }

    /**
     * 新增护航专区-任务凭证
     * 
     * @param escortVoucher 护航专区-任务凭证
     * @return 结果
     */
    @Override
    public int insertEscortVoucher(EscortVoucher escortVoucher)
    {
        escortVoucher.setCreateTime(DateUtils.getNowDate());
        return escortVoucherMapper.insertEscortVoucher(escortVoucher);
    }

    /**
     * 修改护航专区-任务凭证
     * 
     * @param escortVoucher 护航专区-任务凭证
     * @return 结果
     */
    @Override
    public int updateEscortVoucher(EscortVoucher escortVoucher)
    {
        escortVoucher.setUpdateTime(DateUtils.getNowDate());
        return escortVoucherMapper.updateEscortVoucher(escortVoucher);
    }

    /**
     * 批量删除护航专区-任务凭证
     * 
     * @param voucherIds 需要删除的护航专区-任务凭证主键
     * @return 结果
     */
    @Override
    public int deleteEscortVoucherByVoucherIds(Long[] voucherIds)
    {
        return escortVoucherMapper.deleteEscortVoucherByVoucherIds(voucherIds);
    }

    /**
     * 删除护航专区-任务凭证信息
     * 
     * @param voucherId 护航专区-任务凭证主键
     * @return 结果
     */
    @Override
    public int deleteEscortVoucherByVoucherId(Long voucherId)
    {
        return escortVoucherMapper.deleteEscortVoucherByVoucherId(voucherId);
    }
}
