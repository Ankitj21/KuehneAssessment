package com.wallet.appn.component.service;

import com.wallet.appn.component.entity.WalletEntity;
import com.wallet.appn.component.model.WalletRequest;
import com.wallet.appn.component.repository.WalletRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EWalletServiceTest {

    @Mock
    WalletRepository walletRepository;
    @InjectMocks
    EWalletService eWalletService;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new EWalletService()).build();
    }

    @Test
    public void createWalletTest() {
        WalletRequest wr = new WalletRequest();
        wr.setAmount("1000");
        assertEquals("created", eWalletService.createWallet(wr));
    }

    @Test
    public void getWalletBalanceTest() {
        WalletEntity we = new WalletEntity();
        we.setId("1");
        we.setAmount("1000");
        we.setName("Personal");
        Optional<WalletEntity> optionalEntity = Optional.of(we);

        when(walletRepository.findById("1")).thenReturn(optionalEntity);
        assertEquals("1000", eWalletService.getWalletBalance("1"));
    }

    @Test
    public void updateWalletTest() {
        WalletEntity we = new WalletEntity();
        we.setId("1");
        we.setAmount("1000");
        we.setName("Personal");
        Optional<WalletEntity> optionalEntity = Optional.of(we);

        when(walletRepository.findById("1")).thenReturn(optionalEntity);
        assertEquals("2000", eWalletService.updateWallet("1", "1000"));
    }

    @Test
    public void withdrawAmountTest() {
        WalletEntity we = new WalletEntity();
        we.setId("1");
        we.setAmount("5000");
        we.setName("Personal");
        Optional<WalletEntity> optionalEntity = Optional.of(we);

        when(walletRepository.findById("1")).thenReturn(optionalEntity);
        assertEquals("4000", eWalletService.withdrawAmount("1", "1000"));
    }
}
