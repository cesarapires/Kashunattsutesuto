package com.kashunattsutesuto.domain.usecase;

import com.kashunattsutesuto.domain.contracts.repositories.TransactionPersistenceAdapter;
import com.kashunattsutesuto.domain.contracts.repositories.WalletPersistenceAdapter;
import com.kashunattsutesuto.domain.model.MCCCategory;
import com.kashunattsutesuto.domain.model.Transaction;
import com.kashunattsutesuto.domain.model.TransactionStatus;
import com.kashunattsutesuto.domain.model.Wallet;
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

    @Mock TransactionPersistenceAdapter transactionPersistenceAdapter;

    CreateTransactionUseCase createTransactionUseCase;

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        createTransactionUseCase = new CreateTransactionUseCase(walletPersistenceAdapter, transactionPersistenceAdapter);
        transaction = new Transaction(
                "123456",
                "123",
                new BigDecimal(45.00),
                "PADARIA DO ZE SAO PAULO BR",
                MCCCategory.MEAL,
                1
        );
    }

    @Test
    public void shouldCallTransactionPersistenceWithCorrectParams() {
        // Arrange
        String accountId = "123";
        Wallet wallet = new Wallet("12345", accountId, new BigDecimal(0), new BigDecimal(45), new BigDecimal(0), 1);
        when(walletPersistenceAdapter.getWalletByAccountId(accountId)).thenReturn(wallet);

        // Act
        createTransactionUseCase.create(transaction);

        // Assert
        verify(transactionPersistenceAdapter).save(transaction);
    }

    @Test
    public void shouldCallWalletPersistenceWithCorrectParams() {
        // Arrange
        String accountId = "123";
        Wallet wallet = new Wallet("12345", accountId, new BigDecimal(0), new BigDecimal(45), new BigDecimal(0), 1);
        when(walletPersistenceAdapter.getWalletByAccountId(accountId)).thenReturn(wallet);

        // Act
        createTransactionUseCase.create(transaction);

        // Assert
        wallet.setMealBalance(new BigDecimal(0));
        verify(walletPersistenceAdapter).save(wallet);
    }

    @Test
    public void shouldRejectTransactionDueToInsufficientMealBalance() {
        // Arrange
        String accountId = "123";
        Wallet wallet = new Wallet("12345", accountId, new BigDecimal(100), new BigDecimal(44.99), new BigDecimal(0), 1);
        when(walletPersistenceAdapter.getWalletByAccountId(accountId)).thenReturn(wallet);

        // Act
        TransactionStatus result = createTransactionUseCase.create(transaction);

        // Assert
        assertEquals(result, TransactionStatus.REJECTED_INSUFFICIENT_BALANCE);
    }

    @Test
    public void shouldRejectTransactionDueToInsufficientFoodBalance() {
        // Arrange
        String accountId = "123";
        transaction.setMccCategory(MCCCategory.FOOD);
        Wallet wallet = new Wallet("12345", accountId, new BigDecimal(44.99), new BigDecimal(100), new BigDecimal(0), 1);
        when(walletPersistenceAdapter.getWalletByAccountId(accountId)).thenReturn(wallet);

        // Act
        TransactionStatus result = createTransactionUseCase.create(transaction);

        // Assert
        assertEquals(result, TransactionStatus.REJECTED_INSUFFICIENT_BALANCE);
    }

    @Test
    public void shouldCreateTransactionWithCashAndFoodBalance() {
        // Arrange
        String accountId = "123";
        transaction.setMccCategory(MCCCategory.FOOD);
        Wallet wallet = new Wallet("12345", accountId, new BigDecimal(20), new BigDecimal(100), new BigDecimal(25), 1);
        when(walletPersistenceAdapter.getWalletByAccountId(accountId)).thenReturn(wallet);

        // Act
        TransactionStatus result = createTransactionUseCase.create(transaction);

        // Assert
        assertEquals(result, TransactionStatus.SUCCESS);
    }

    @Test
    public void shouldCreateTransactionWithCashAndMealBalance() {
        // Arrange
        String accountId = "123";
        transaction.setMccCategory(MCCCategory.MEAL);
        Wallet wallet = new Wallet("12345", accountId, new BigDecimal(100), new BigDecimal(20), new BigDecimal(25), 1);
        when(walletPersistenceAdapter.getWalletByAccountId(accountId)).thenReturn(wallet);

        // Act
        TransactionStatus result = createTransactionUseCase.create(transaction);

        // Assert
        assertEquals(result, TransactionStatus.SUCCESS);
    }

    @Test
    public void shouldRejectTransactionDueToInsufficientCashBalance() {
        // Arrange
        String accountId = "123";
        transaction.setMccCategory(MCCCategory.OTHERS);
        Wallet wallet = new Wallet("12345", accountId, new BigDecimal(0), new BigDecimal(0), new BigDecimal(44.99), 1);
        when(walletPersistenceAdapter.getWalletByAccountId(accountId)).thenReturn(wallet);

        // Act
        TransactionStatus result = createTransactionUseCase.create(transaction);

        // Assert
        assertEquals(result, TransactionStatus.REJECTED_INSUFFICIENT_BALANCE);
    }

    @Test
    public void shouldReturnGenericErrorWhenDontFoundWallet() {
        // Act
        TransactionStatus result = createTransactionUseCase.create(transaction);

        // Assert
        assertEquals(result, TransactionStatus.GENERIC_ERROR);
    }
}