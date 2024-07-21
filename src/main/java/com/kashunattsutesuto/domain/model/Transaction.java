package com.kashunattsutesuto.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String id;

    private String accountId;

    private BigDecimal amount;

    private String merchant;

    private MCCCategory mccCategory;
}
