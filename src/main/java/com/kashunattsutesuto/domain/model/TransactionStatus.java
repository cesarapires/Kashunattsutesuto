package com.kashunattsutesuto.domain.model;

public enum TransactionStatus {
    GENERIC_ERROR("07"),
    SUCCESS("00");

    private String code;

    TransactionStatus(String code) {
        this.code = code;
    }
}
