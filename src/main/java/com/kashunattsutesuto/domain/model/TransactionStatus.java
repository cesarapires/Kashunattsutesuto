package com.kashunattsutesuto.domain.model;

public enum TransactionStatus {
    GENERIC_ERROR("07"),
    REJECTED_INSUFFICIENT_BALANCE("51"),
    SUCCESS("00");

    private String code;

    TransactionStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
