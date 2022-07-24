package com.mpikuza.vendingmachine.service;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Item;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    Integer getDepositedBalance();
    List<Coin> getUserDepositedCoins();
    void clearUserDepositedCoins();
    Optional<Item> findItemByName(String name);
    void removeItem(Item item);
    List<Coin> getVendingMachineBalance();
    void setVendingMachineBalance(List<Coin> coins);
    void setItems(List<Item> items);
}
