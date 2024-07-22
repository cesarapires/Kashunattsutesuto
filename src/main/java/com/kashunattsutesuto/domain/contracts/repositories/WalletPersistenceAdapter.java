package com.kashunattsutesuto.domain.contracts.repositories;

import com.kashunattsutesuto.domain.model.Wallet;

public interface WalletPersistenceAdapter {
    Wallet getWalletByAccountId(String accountId);

    Wallet save(Wallet wallet);
}