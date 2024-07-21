package com.kashunattsutesuto.domain.contracts.repositories;

import com.kashunattsutesuto.domain.model.Transaction;

public interface TransactionPersistenceAdapter {
    Transaction save(Transaction transaction);
}
