package com.kashunattsutesuto.infraestructure.repositories.postgres.entities;

import com.kashunattsutesuto.domain.model.MCCCategory;
import com.kashunattsutesuto.domain.model.Transaction;
import com.kashunattsutesuto.domain.model.Wallet;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
@Transactional
public class TransactionEntity {
    @Id
    @Column(unique = true)
    private String id;

    @Column(name = "account_id")
    private String accountId;

    private BigDecimal amount;

    private String merchant;

    @Version
    private Integer version;

    @Enumerated(EnumType.STRING)
    @Column(name = "mcc_category")
    private MCCCategory mcc;

    public Transaction toDomain() {
        return Transaction.builder()
                .id(this.id)
                .accountId(this.accountId)
                .amount(this.amount)
                .merchant(this.merchant)
                .mccCategory(this.mcc)
                .version(this.version)
                .build();
    }

    public TransactionEntity (Transaction transaction) {
        this.id = transaction.getId();
        this.accountId = transaction.getAccountId();
        this.amount = transaction.getAmount();
        this.merchant = transaction.getMerchant();
        this.mcc = transaction.getMccCategory();
        this.version = transaction.getVersion();
    }
}
