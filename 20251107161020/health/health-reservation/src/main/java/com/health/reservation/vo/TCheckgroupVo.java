package com.health.reservation.vo;

import com.health.reservation.domain.TCheckgroup;

import java.util.List;

/**
 * 检查组响应 VO，包含检查组基础信息和关联的检查项 id 列表
 */
public class TCheckgroupVo extends TCheckgroup {

    private List<Long> checkItemIds;

    public List<Long> getCheckItemIds() {
        return checkItemIds;
    }

    public void setCheckItemIds(List<Long> checkItemIds) {
        this.checkItemIds = checkItemIds;
    }

    @Override
    public String toString() {
        return "TCheckgroupVo{" +
                "checkItemIds=" + checkItemIds +
                '}';
    }
}
