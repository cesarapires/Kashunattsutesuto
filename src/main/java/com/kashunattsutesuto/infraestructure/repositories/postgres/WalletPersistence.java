package com.kashunattsutesuto.infraestructure.repositories.postgres;

import com.kashunattsutesuto.domain.contracts.repositories.WalletPersistenceAdapter;
import com.kashunattsutesuto.domain.model.Wallet;
import com.kashunattsutesuto.infraestructure.repositories.postgres.entities.WalletEntity;
import com.kashunattsutesuto.infraestructure.repositories.postgres.repository.WalletRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WalletPersistence implements WalletPersistenceAdapter {

    private final WalletRepository walletRepository;

    @Override
    public Wallet getWalletByAccountId(String accountId) {
        WalletEntity walletEntity = walletRepository.findById(accountId).get();
        if (walletEntity == null) {
            return null;
        }
        return walletEntity.toDomain();
    }
}
