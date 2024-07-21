package com.kashunattsutesuto.domain.usecase;

import com.kashunattsutesuto.domain.contracts.repository.WalletPersistenceAdapter;
import com.kashunattsutesuto.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

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
                "5811"
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
}