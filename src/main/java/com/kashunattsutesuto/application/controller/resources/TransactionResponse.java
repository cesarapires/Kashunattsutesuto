package com.kashunattsutesuto.application.controller.resources;

import com.kashunattsutesuto.domain.model.TransactionStatus;
import lombok.Data;

@Data
public class TransactionResponse {
    private String code;

    public TransactionResponse(TransactionStatus transactionStatus) {
        this.code = transactionStatus.getCode();
    }
}
