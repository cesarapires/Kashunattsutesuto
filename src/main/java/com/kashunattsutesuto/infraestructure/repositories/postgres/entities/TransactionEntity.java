package com.kashunattsutesuto.infraestructure.repositories.postgres.entities;

import com.kashunattsutesuto.domain.model.MCCCategory;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "mcc_category")
    private MCCCategory mcc;
}
