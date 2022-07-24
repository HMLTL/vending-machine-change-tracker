package com.mpikuza.vendingmachine.service.user;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Item;
import com.mpikuza.vendingmachine.service.InventoryService;
import com.mpikuza.vendingmachine.util.ChangeFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private InventoryService inventoryService;
    private ChangeFinder changeFinder;

    @Override
    public void makeOrder(String name) {
        Optional<Item> itemOpt = inventoryService.findItemByName(name);
        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            System.out.println("Submitted coins: " + inventoryService.getUserDepositedCoins());
            Integer userDeposit = inventoryService.getDepositedBalance();
            System.out.println("Total deposited: " + userDeposit);
            if (isEnoughBalance(item, userDeposit)) {
                List<Coin> change = getChange(item.getPrice(), userDeposit);
                inventoryService.removeItem(item);
                inventoryService.clearUserDepositedCoins();
//                inventoryService.getUserDepositedCoins().clear();
                if (!change.isEmpty()) {
                    System.out.println("User change: " + change);
                }
                System.out.println("User order: " + item);
            } else {
                throw new UnsupportedOperationException(format("Not enough deposited coins. Item price is: %s. Need to add: %s", item.getPrice(), priceDifference(userDeposit, item.getPrice())));
            }
        } else {
            throw new UnsupportedOperationException(format("Item %s is not available.", name));
        }
    }

    private Integer priceDifference(Integer userDeposit, Integer itemPrice) {
        return itemPrice - userDeposit;
    }

    private List<Coin> getChange(Integer price, Integer userDeposit) {
        int changeValue = userDeposit - price;
        if (changeValue > 0) {
            return changeFinder.find(inventoryService.getVendingMachineBalance(), changeValue);
        } else {
            return Collections.emptyList();
        }
    }

    private boolean isEnoughBalance(Item item, Integer userDeposit) {
        return userDeposit >= item.getPrice();
    }

}
