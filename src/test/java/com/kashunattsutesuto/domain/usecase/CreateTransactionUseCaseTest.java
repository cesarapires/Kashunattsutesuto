package com.kashunattsutesuto.domain.usecase;

import com.kashunattsutesuto.domain.contracts.repositories.WalletPersistenceAdapter;
import com.kashunattsutesuto.domain.model.MCCCategory;
import com.kashunattsutesuto.domain.model.Transaction;
import com.kashunattsutesuto.domain.model.TransactionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTransactionUseCaseTest {

    @Mock WalletPersistenceAdapter walletPersistenceAdapter;

    CreateTransactionUseCase createTransactionUseCase;

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        createTransactionUseCase = new CreateTransactionUseCase(walletPersistenceAdapter);
        transaction = new Transaction(
                "123456",
                "123",
                new BigDecimal(45.00),
                "PADARIA DO ZE SAO PAULO BR",
                MCCCategory.MEAL
        );
    }

    @Test
    public void shouldCallWalletPersistenceWithCorrectParams() {
        // Arrange
        String accountId = "123";

        // Act
        createTransactionUseCase.create(transaction);

        // Assert
        verify(walletPersistenceAdapter).getWalletByAccountId(accountId);
    }

    @Test
    public void shouldReturnGenericErrorWhenDontFoundWallet() {
        // Act
        TransactionStatus result = createTransactionUseCase.create(transaction);

        // Assert
        assertEquals(result, TransactionStatus.GENERIC_ERROR);
    }
}