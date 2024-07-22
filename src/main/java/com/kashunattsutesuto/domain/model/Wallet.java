package com.kashunattsutesuto.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private String id;

    private String accountId;

    private BigDecimal foodBalance;

    private BigDecimal mealBalance;

    private BigDecimal cashBalance;

    private Integer version;
}
