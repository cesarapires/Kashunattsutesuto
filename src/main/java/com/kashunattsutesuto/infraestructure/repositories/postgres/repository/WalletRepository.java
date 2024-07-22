package com.kashunattsutesuto.infraestructure.repositories.postgres.repository;

import com.kashunattsutesuto.infraestructure.repositories.postgres.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository <WalletEntity, String> {

    Optional<WalletEntity> findByAccountId(@Param("account_id") String accountId);

}
