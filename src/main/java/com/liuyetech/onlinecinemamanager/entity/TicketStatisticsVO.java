package com.liuyetech.onlinecinemamanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketStatisticsVO {
    private Long totalCount;
    private String movieName;
    private Double totalPrice;
}
