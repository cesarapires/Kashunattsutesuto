package com.kashunattsutesuto.domain.usecase;

import com.kashunattsutesuto.domain.contracts.repositories.TransactionPersistenceAdapter;
import com.kashunattsutesuto.domain.contracts.repositories.WalletPersistenceAdapter;
import com.kashunattsutesuto.domain.feature.CreateTransaction;
import com.kashunattsutesuto.domain.model.Transaction;
import com.kashunattsutesuto.domain.model.TransactionStatus;
import com.kashunattsutesuto.domain.model.Wallet;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional
public class CreateTransactionUseCase implements CreateTransaction {

    private final WalletPersistenceAdapter walletPersistenceAdapter;

    private final TransactionPersistenceAdapter transactionPersistenceAdapter;

    @Override
    public TransactionStatus create(Transaction transaction) {
       Wallet wallet = walletPersistenceAdapter.getWalletByAccountId(transaction.getAccountId());
       if (wallet == null) return TransactionStatus.GENERIC_ERROR;

       BigDecimal amount = transaction.getAmount();

       switch (transaction.getMccCategory()) {
           case MEAL:
               BigDecimal mealBalance = wallet.getMealBalance();
               if (!validatesBalanceIsSufficient(mealBalance, amount)) {
                   amount = adjustAmountFromCashBalance(wallet, amount, mealBalance);
                   if (amount == null) return TransactionStatus.REJECTED_INSUFFICIENT_BALANCE;
               }
               wallet.setMealBalance(mealBalance.subtract(amount));
               break;
           case FOOD:
               BigDecimal foodBalance = wallet.getFoodBalance();
               if (!validatesBalanceIsSufficient(foodBalance, amount)) {
                   amount = adjustAmountFromCashBalance(wallet, amount, foodBalance);
                   if (amount == null) return TransactionStatus.REJECTED_INSUFFICIENT_BALANCE;
               }
               wallet.setFoodBalance(foodBalance.subtract(amount));
               break;
           default:
               BigDecimal cashBalance = wallet.getCashBalance();
               if (!validatesBalanceIsSufficient(cashBalance, amount)) return TransactionStatus.REJECTED_INSUFFICIENT_BALANCE;
               wallet.setCashBalance(cashBalance.subtract(amount));
               break;
       }


       walletPersistenceAdapter.save(wallet);
       transactionPersistenceAdapter.save(transaction);

       return TransactionStatus.SUCCESS;
    }

    private BigDecimal adjustAmountFromCashBalance(Wallet wallet, BigDecimal amount, BigDecimal foodBalance) {
        BigDecimal cashBalance = wallet.getCashBalance();
        BigDecimal balanceRequired = amount.subtract(foodBalance);

        if (!validatesBalanceIsSufficient(cashBalance, balanceRequired)) return null;

        wallet.setCashBalance(cashBalance.subtract(balanceRequired));

        return amount.subtract(balanceRequired);
    }

    private Boolean validatesBalanceIsSufficient(BigDecimal walletBalance, BigDecimal amount) {
        return walletBalance.compareTo(amount) >= 0;
    }
}
