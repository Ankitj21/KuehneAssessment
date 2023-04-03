package com.wallet.appn.component.controller;

import com.wallet.appn.component.entity.WalletEntity;
import com.wallet.appn.component.model.WalletRequest;
import com.wallet.appn.component.service.EWalletService;
import jakarta.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EWalletController {

    private static final Logger logger = LoggerFactory.getLogger(EWalletController.class);

    @Autowired
    EWalletService eWalletService;

    @PostMapping(value = "/wallet/create")
    @Produces("application/json")
    public ResponseEntity<String> createWallet(@RequestBody WalletRequest walletRequest) throws Exception {
        try{
            String response = eWalletService.createWallet(walletRequest);
            logger.info("EWalletController -> createWallet : " + response);
            if(response != null){
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                throw new Exception("Wallet not created !!");
            }
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping(value = "/wallet/balance/{id}")
    @Produces("application/json")
    public ResponseEntity<String> getBalance(@PathVariable("id") String id) throws Exception {
        try{
            String response = eWalletService.getWalletBalance(id);
            logger.info("EWalletController -> getBalance : " + response);
            if (response != null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("Balance not found for : " + id);
            }
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping(value = "/wallet/add/{id}/{amount}")
    @Produces("application/json")
    public ResponseEntity<String> updateWallet(@PathVariable("id") String id, @PathVariable("amount") String amount) throws Exception {
        try{
            String response = eWalletService.updateWallet(id, amount);
            logger.info("EWalletController -> updateWallet : " + response);
            if (response != null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("Balance not updated for : " + id);
            }
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PutMapping(value = "/wallet/withdraw/{id}/{amount}")
    @Produces("application/json")
    public ResponseEntity<String> withdrawAmount(@PathVariable("id") String id, @PathVariable("amount") String amount) throws Exception {
        try{
            String response = eWalletService.withdrawAmount(id, amount);
            logger.info("EWalletController -> withdrawAmount : " + response);
            if (response != null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("Amount not withdrawn for : " + id);
            }
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping(value = "/wallet/details")
    @Produces("application/json")
    public ResponseEntity<List<WalletEntity>> getAllWallets() throws Exception {
        try{
            List<WalletEntity> response = eWalletService.getAllWallets();
            logger.info("EWalletController -> getAllWallets : " + response);
            if (response != null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("List of Wallets not found : " + response);
            }
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
