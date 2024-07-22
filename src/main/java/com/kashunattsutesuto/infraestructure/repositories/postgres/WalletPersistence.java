package com.kashunattsutesuto.infraestructure.repositories.postgres;

import com.kashunattsutesuto.domain.contracts.repositories.WalletPersistenceAdapter;
import com.kashunattsutesuto.domain.model.Wallet;
import com.kashunattsutesuto.infraestructure.repositories.postgres.entities.WalletEntity;
import com.kashunattsutesuto.infraestructure.repositories.postgres.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class WalletPersistence implements WalletPersistenceAdapter {

    private final WalletRepository walletRepository;

    @Override
    public Wallet getWalletByAccountId(String accountId) {
        Optional<WalletEntity> walletEntity = walletRepository.findByAccountId(accountId);
        if (!walletEntity.isPresent()) {
            return null;
        }
        return walletEntity.get().toDomain();
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity walletEntity = new WalletEntity(wallet);
        walletEntity = walletRepository.save(walletEntity);
        return walletEntity.toDomain();
    }
}
