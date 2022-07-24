package com.mpikuza.vendingmachine.service.user;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReturnCoinServiceImpl implements ReturnCoinService {

    private InventoryService inventoryService;

    @Override
    public void returnDepositedCoins() {
        List<Coin> userDepositedCoins = inventoryService.getUserDepositedCoins();
        System.out.println("Return coins: " + userDepositedCoins);
        userDepositedCoins.clear();
    }
}
