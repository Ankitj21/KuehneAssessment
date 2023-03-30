package com.wallet.appn.component.repository;

import com.wallet.appn.component.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletEntity, String> {
}
