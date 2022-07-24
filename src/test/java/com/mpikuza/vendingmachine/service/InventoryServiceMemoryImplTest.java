package com.mpikuza.vendingmachine.service;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Denomination;
import com.mpikuza.vendingmachine.util.Inventory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
class InventoryServiceMemoryImplTest {

    @Mock
    Inventory inventory;

    @InjectMocks
    private InventoryServiceMemoryImpl underTest;

    @Test
    void getDepositedBalance(){
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin(Denomination.TWO_POUND, 1));
        coins.add(new Coin(Denomination.FIVE_PENCE, 2));
        when(inventory.getUserDepositedCoins()).thenReturn(coins);
        Integer depositedBalance = underTest.getDepositedBalance();
        assertThat(depositedBalance).isEqualTo(210);
    }
}