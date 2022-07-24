package com.mpikuza.vendingmachine.util;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Inventory {

    private List<Coin> changeCoins = new ArrayList<>();
    private List<Coin> collectedCoins = new ArrayList<>();
    private List<Coin> userDepositedCoins = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

}
