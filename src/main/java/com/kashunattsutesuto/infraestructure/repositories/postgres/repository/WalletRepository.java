package com.kashunattsutesuto.infraestructure.repositories.postgres.repository;

import com.kashunattsutesuto.infraestructure.repositories.postgres.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository <WalletEntity, String> {
}
