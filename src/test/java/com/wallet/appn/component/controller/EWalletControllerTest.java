package com.wallet.appn.component.controller;

import com.wallet.appn.component.entity.WalletEntity;
import com.wallet.appn.component.model.WalletRequest;
import com.wallet.appn.component.service.EWalletService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EWalletControllerTest {

    @Mock
    EWalletService eWalletService;
    @InjectMocks
    EWalletController eWalletController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new EWalletController()).build();
    }

    @Test
    public void createWalletTest() throws Exception {
        WalletRequest wr = new WalletRequest();
        wr.setAmount("1000");
        wr.setName("Personal");

        when(eWalletService.createWallet(any(WalletRequest.class))).thenReturn("created");
        assertEquals("created", eWalletController.createWallet(wr).getBody());
    }

    @Test
    public void getBalanceTest() throws Exception {
        when(eWalletService.getWalletBalance(anyString())).thenReturn("1000");
        assertEquals("1000", eWalletController.getBalance(anyString()).getBody());
    }

    @Test
    public void updateWalletTest() throws Exception {
        when(eWalletService.updateWallet(anyString(), anyString())).thenReturn("11000");
        assertEquals("11000", eWalletController.updateWallet(anyString(), anyString()).getBody());
    }

    @Test
    public void withdrawAmountTest() throws Exception {
        when(eWalletService.withdrawAmount(anyString(), anyString())).thenReturn("1000");
        assertEquals("1000", eWalletController.withdrawAmount(anyString(), anyString()).getBody());
    }

    @Test
    public void getAllWalletsTest() throws Exception {
        WalletEntity we = new WalletEntity();
        we.setId("1");
        we.setAmount("1000");
        we.setName("Personal");

        WalletEntity we1 = new WalletEntity();
        we1.setId("2");
        we1.setAmount("11000");
        we1.setName("Work");

        List<WalletEntity> ll = new ArrayList<>();
        ll.add(we);
        ll.add(we1);

        when(eWalletService.getAllWallets()).thenReturn(ll);
        ResponseEntity<List<WalletEntity>> response = eWalletController.getAllWallets();
        assertEquals(ll.size(), response.getBody().size());
    }
}
