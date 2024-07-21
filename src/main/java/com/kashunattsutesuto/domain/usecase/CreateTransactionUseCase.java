package com.kashunattsutesuto.domain.usecase;

import com.kashunattsutesuto.domain.contracts.repository.WalletPersistenceAdapter;
import com.kashunattsutesuto.domain.feature.CreateTransaction;
import com.kashunattsutesuto.domain.model.Transaction;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTransactionUseCase implements CreateTransaction {

    private final WalletPersistenceAdapter walletPersistenceAdapter;

    @Override
    public String create(Transaction transaction) {
       walletPersistenceAdapter.getWalletByAccountId(transaction.getAccountId());
        return "";
    }
}
