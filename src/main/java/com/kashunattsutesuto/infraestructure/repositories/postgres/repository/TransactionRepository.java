package com.kashunattsutesuto.infraestructure.repositories.postgres.repository;

import com.kashunattsutesuto.infraestructure.repositories.postgres.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
}
