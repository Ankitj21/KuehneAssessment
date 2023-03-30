package com.wallet.appn.component.controller;

import com.wallet.appn.component.model.WalletRequest;
import com.wallet.appn.component.service.EWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EWalletController {

//    EWalletService eWalletService;

    @Autowired
    EWalletService eWalletService;

//    public EWalletController(EWalletService eWalletService){
//        this.eWalletService = eWalletService;
//    }

    @PostMapping(value = "wallet/create")
    public ResponseEntity<String> createWallet(@RequestBody WalletRequest walletRequest) throws Exception {
        try{
            String response = eWalletService.createWallet(walletRequest);
            if(response != null){
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                throw new Exception("Wallet not created !!");
            }
        } catch(Exception e){
            throw new Exception("Exception occurred while creating new wallet !!");
        }
    }
}
