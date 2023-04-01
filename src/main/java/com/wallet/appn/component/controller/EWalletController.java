package com.wallet.appn.component.controller;

import com.wallet.appn.component.model.WalletRequest;
import com.wallet.appn.component.service.EWalletService;
import jakarta.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EWalletController {

//    EWalletService eWalletService;

    private static final Logger logger = LoggerFactory.getLogger(EWalletController.class);

    @Autowired
    EWalletService eWalletService;

//    public EWalletController(EWalletService eWalletService){
//        this.eWalletService = eWalletService;
//    }

    @PostMapping(value = "/wallet/create")
    @Produces("application/json")
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

    @GetMapping(value = "/wallet/balance/{id}")
    @Produces("application/json")
    public ResponseEntity<String> getBalance(@PathVariable("id") String id) throws Exception {
        try{
            String response = eWalletService.getWalletBalance(id);

            if (response != null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("Balance not found for : " + id);
            }
        } catch(Exception e){
            throw new Exception("Exception occurred while fetching wallet balance for : " + id);
        }
    }

    @PutMapping(value = "/wallet/add/{id}/{amount}")
    @Produces("application/json")
    public ResponseEntity<String> updateWallet(@PathVariable("id") String id, @PathVariable("amount") String amount) throws Exception {
        try{
            String response = eWalletService.updateWallet(id, amount);

            if (response != null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("Balance not updated for : " + id);
            }
        } catch(Exception e){
            throw new Exception("Exception occurred while updating wallet balance for : " + id);
        }
    }

    @PutMapping(value = "/wallet/withdraw/{id}/{amount}")
    @Produces("application/json")
    public ResponseEntity<String> withdrawAmount(@PathVariable("id") String id, @PathVariable("amount") String amount) throws Exception {
        try{
            String response = eWalletService.updateWallet(id, amount);

            if (response != null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception("Amount not withdrawn for : " + id);
            }
        } catch(Exception e){
            throw new Exception("Exception occurred while withdrawing amount for : " + id);
        }
    }

}
