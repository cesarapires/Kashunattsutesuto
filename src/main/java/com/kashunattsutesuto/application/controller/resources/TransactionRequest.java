package com.kashunattsutesuto.application.controller.resources;

import com.kashunattsutesuto.domain.model.MCCCategory;
import com.kashunattsutesuto.domain.model.Transaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @NotNull
    private String id;

    @NotNull
    private String accountId;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private String merchant;

    @NotNull
    private String mccCode;

    public Transaction toDomain() {
        return Transaction.builder()
                .id(this.id)
                .accountId(this.accountId)
                .amount(this.amount)
                .merchant(this.merchant)
                .mccCategory(MCCCategory.fromCode(mccCode))
                .build();
    }
}
