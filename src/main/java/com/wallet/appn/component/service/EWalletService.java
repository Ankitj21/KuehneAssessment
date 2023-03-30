package com.wallet.appn.component.service;

import com.wallet.appn.component.entity.WalletEntity;
import com.wallet.appn.component.model.WalletRequest;
import com.wallet.appn.component.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class EWalletService {

    @Autowired
    WalletRepository walletRepository;

    public String createWallet(WalletRequest walletRequest) throws Exception {

        try{
            if(Objects.nonNull(walletRequest)){
                WalletEntity walletEntity = new WalletEntity();
                walletEntity.setId(walletRequest.getId());
                walletEntity.setName(walletRequest.getName());
                walletEntity.setAmount(walletRequest.getAmount());
                walletRepository.save(walletEntity);
                return "created";
            } else {
                return null;
            }
        } catch (Exception e){
            throw new Exception("Creation of new wallet failed !!");
        }
    }

}
