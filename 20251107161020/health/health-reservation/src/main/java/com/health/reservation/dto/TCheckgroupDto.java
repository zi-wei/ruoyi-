package com.health.reservation.dto;

import com.health.reservation.domain.TCheckgroup;

import java.util.List;

public class TCheckgroupDto extends TCheckgroup {
    private List<Long> checkItemIds;

    public List<Long> getCheckItemIds() {
        return checkItemIds;
    }

    public void setCheckItemIds(List<Long> checkItemIds) {
        this.checkItemIds = checkItemIds;
    }

    @Override
    public String toString() {
        return "TCheckgroupDto{" +
                "checkItemIds=" + checkItemIds +
                '}';
    }
}