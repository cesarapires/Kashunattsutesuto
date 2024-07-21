package com.kashunattsutesuto.domain.usecase;

import com.kashunattsutesuto.domain.contracts.repositories.WalletPersistenceAdapter;
import com.kashunattsutesuto.domain.feature.CreateTransaction;
import com.kashunattsutesuto.domain.model.Transaction;
import com.kashunattsutesuto.domain.model.TransactionStatus;
import com.kashunattsutesuto.domain.model.Wallet;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTransactionUseCase implements CreateTransaction {

    private final WalletPersistenceAdapter walletPersistenceAdapter;

    @Override
    public TransactionStatus create(Transaction transaction) {
       Wallet wallet = walletPersistenceAdapter.getWalletByAccountId(transaction.getAccountId());
       if (wallet == null) return TransactionStatus.GENERIC_ERROR;

       return TransactionStatus.SUCCESS;
    }
}
