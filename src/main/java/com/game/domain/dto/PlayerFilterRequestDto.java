package com.game.domain.dto;

import com.game.controller.enums.PlayerOrder;
import com.game.domain.dto.abstracts.AbstractPlayerRequestDto;

public class PlayerFilterRequestDto extends AbstractPlayerRequestDto {

    private PlayerOrder order = PlayerOrder.ID;
    private Integer pageNumber = 0;
    private Integer pageSize = 3;

    public void setOrder(PlayerOrder order) {
        this.order = order;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public PlayerOrder getOrder() {
        return order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
