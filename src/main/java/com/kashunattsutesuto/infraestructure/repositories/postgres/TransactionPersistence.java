package com.kashunattsutesuto.infraestructure.repositories.postgres;

import com.kashunattsutesuto.domain.contracts.repositories.TransactionPersistenceAdapter;
import com.kashunattsutesuto.domain.model.Transaction;
import com.kashunattsutesuto.infraestructure.repositories.postgres.entities.TransactionEntity;
import com.kashunattsutesuto.infraestructure.repositories.postgres.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TransactionPersistence implements TransactionPersistenceAdapter {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity(transaction);
        transactionEntity = transactionRepository.save(transactionEntity);
        return transactionEntity.toDomain();
    }
}
