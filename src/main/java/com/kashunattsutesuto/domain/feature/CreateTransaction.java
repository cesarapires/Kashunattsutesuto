package com.kashunattsutesuto.domain.feature;

import com.kashunattsutesuto.domain.model.Transaction;
import com.kashunattsutesuto.domain.model.TransactionStatus;

public interface CreateTransaction {
    TransactionStatus create(Transaction transaction);
}
