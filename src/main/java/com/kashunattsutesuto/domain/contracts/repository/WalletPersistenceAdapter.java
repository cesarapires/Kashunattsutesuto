package com.kashunattsutesuto.domain.contracts.repository;

import com.kashunattsutesuto.domain.model.Wallet;

public interface WalletPersistenceAdapter {
    Wallet getWalletByAccountId(String accountId);
}