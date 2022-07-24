package com.mpikuza.vendingmachine.service.maintenance;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Denomination;
import com.mpikuza.vendingmachine.model.Item;
import com.mpikuza.vendingmachine.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class DefaultInitializationService implements InitializationService {

    private static final Integer COIN_COUNT = 50;
    private static final Integer ITEM_COUNT = 4;

    private InventoryService inventoryService;

    @Override
    public void initCoins(String input) {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin(Denomination.TWO_POUND, COIN_COUNT));
        coins.add(new Coin(Denomination.ONE_POUND, COIN_COUNT));
        coins.add(new Coin(Denomination.FIFTY_PENCE, COIN_COUNT));
        coins.add(new Coin(Denomination.TWENTY_PENCE, COIN_COUNT));
        coins.add(new Coin(Denomination.TEN_PENCE, COIN_COUNT));
        coins.add(new Coin(Denomination.FIVE_PENCE, COIN_COUNT));
        inventoryService.setVendingMachineBalance(coins);
        System.out.println("Initialized coins: " + coins);
    }

    @Override
    public void initItems(String filePath) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            items.add(new Item("cola", 50));
            items.add(new Item("crackers", 140));
            items.add(new Item("snack", 145));
            items.add(new Item("beer", 190));
            items.add(new Item("crisp", 200));
            items.add(new Item("cider", 220));
        }
        inventoryService.setItems(items);
        System.out.println("Initialized items: " + items);
    }

}




