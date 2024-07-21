package com.kashunattsutesuto.infraestructure.repositories.postgres.entities;

import com.kashunattsutesuto.domain.model.Wallet;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallets")
public class WalletEntity {
    @Id
    @Column(unique = true)
    private String id;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "food_balance")
    private BigDecimal foodBalance;

    @Column(name = "meal_balance")
    private BigDecimal mealBalance;

    @Column(name = "cash_balance")
    private BigDecimal cashBalance;

    public Wallet toDomain() {
        return Wallet.builder()
                .id(this.id)
                .accountId(this.accountId)
                .cashBalance(this.cashBalance)
                .mealBalance(this.mealBalance)
                .build();
    }
}
