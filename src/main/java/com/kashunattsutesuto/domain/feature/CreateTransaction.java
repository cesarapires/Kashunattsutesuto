package com.kashunattsutesuto.domain.feature;

import com.kashunattsutesuto.domain.model.Transaction;

public interface CreateTransaction {
    String create(Transaction transaction);
}
