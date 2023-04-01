package com.wallet.appn.component.service;

import com.wallet.appn.component.controller.EWalletController;
import com.wallet.appn.component.entity.WalletEntity;
import com.wallet.appn.component.model.WalletRequest;
import com.wallet.appn.component.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class EWalletService {

    private static final Logger logger = LoggerFactory.getLogger(EWalletController.class);
    @Autowired
    WalletRepository walletRepository;

    public String createWallet(WalletRequest walletRequest) throws Exception {

        try{
            if(Objects.nonNull(walletRequest)){
                WalletEntity walletEntity = new WalletEntity();
                walletEntity.setName(walletRequest.getName());
                walletEntity.setAmount(walletRequest.getAmount());
                walletRepository.save(walletEntity);
                logger.info("New wallet created !!");
                return "created";
            } else {
                logger.info("Input request is null");
                return null;
            }
        } catch (Exception e){
            throw new Exception("Creation of new wallet failed !!");
        }
    }

    public String getWalletBalance(String id) {
        Optional<WalletEntity> optionalEntity = walletRepository.findById(id);
        WalletEntity walletEntity = optionalEntity.get();
        return walletEntity.getAmount();
    }

    public String updateWallet(String id, String amount) throws Exception {

        try{
            Optional<WalletEntity> optionalEntity = walletRepository.findById(id);
            WalletEntity walletEntity = optionalEntity.get();
            String balance = walletEntity.getAmount();
            balance = String.valueOf(Integer.valueOf(balance) + Integer.valueOf(amount));

            WalletEntity newEntity = new WalletEntity();
            newEntity.setId(walletEntity.getId());
            newEntity.setName(walletEntity.getName());
            newEntity.setAmount(balance);
            walletRepository.save(newEntity);
            return balance;
        } catch (Exception e){
            throw new Exception("Top up of wallet balance failed !!");
        }
    }

    public String withdrawAmount(String id, String amount) throws Exception {

        try{
            Optional<WalletEntity> optionalEntity = walletRepository.findById(id);
            WalletEntity walletEntity = optionalEntity.get();
            String balance = walletEntity.getAmount();

            if((Integer.valueOf(balance) - Integer.valueOf(amount)) >= 0){
                balance = String.valueOf(Integer.valueOf(balance) - Integer.valueOf(amount));
            } else {
                logger.info("Withdrawn amount requested is more than the account balance !!" + balance);
                throw new Exception("Withdrawn amount requested is more than the account balance !!" + balance);
            }

            WalletEntity newEntity = new WalletEntity();
            newEntity.setId(walletEntity.getId());
            newEntity.setName(walletEntity.getName());
            newEntity.setAmount(balance);
            walletRepository.save(newEntity);
            return balance;
        } catch (Exception e){
            throw new Exception("Top up of wallet balance failed !!");
        }
    }
}
