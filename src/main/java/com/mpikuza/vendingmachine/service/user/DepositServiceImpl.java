package com.mpikuza.vendingmachine.service.user;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Denomination;
import com.mpikuza.vendingmachine.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class DepositServiceImpl implements DepositService {

    private InventoryService inventoryService;

    @Override
    public void add(Integer denomination) {
        Denomination den = Denomination.fromValue(denomination);
        List<Coin> deposit = inventoryService.getUserDepositedCoins();

        boolean isUpdated = false;
        for (int i = 0; i < deposit.size(); i++) {
            if (deposit.get(0).getDenomination() == den) {
                deposit.get(0).setCount(deposit.get(0).getCount() + 1);
                isUpdated = true;
                break;
            }
        }

        if (!isUpdated) {
            deposit.add(new Coin(den, 1));
        }

        System.out.println("Submitted coin: " + new Coin(den, 1));
        System.out.println("Total submitted: " + inventoryService.getDepositedBalance());
    }
}
