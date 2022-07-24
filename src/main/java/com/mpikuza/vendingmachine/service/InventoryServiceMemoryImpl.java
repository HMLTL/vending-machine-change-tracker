package com.mpikuza.vendingmachine.service;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Item;
import com.mpikuza.vendingmachine.util.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryServiceMemoryImpl implements InventoryService {

    Inventory inventory;

    @Override
    public Integer getDepositedBalance() {
        return inventory.getUserDepositedCoins().stream().mapToInt(Coin::getValue).sum();
    }
    @Override
    public List<Coin> getUserDepositedCoins(){
        return inventory.getUserDepositedCoins();
    }

    @Override
    public void clearUserDepositedCoins(){
        inventory.getUserDepositedCoins().clear();
    }

    @Override
    public Optional<Item> findItemByName(String name) {
        return inventory.getItems().stream().filter(item -> item.getName().equals(name)).findAny();
    }

    @Override
    public void removeItem(Item item) {
        inventory.getItems().remove(item);
    }

    @Override
    public List<Coin> getVendingMachineBalance() {
        return inventory.getChangeCoins();
    }

    @Override
    public void setVendingMachineBalance(List<Coin> coins) {
        inventory.setChangeCoins(coins);
    }

    @Override
    public void setItems(List<Item> items) {
        inventory.setItems(items);
    }
}
