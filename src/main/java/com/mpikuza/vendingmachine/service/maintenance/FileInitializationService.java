package com.mpikuza.vendingmachine.service.maintenance;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Denomination;
import com.mpikuza.vendingmachine.model.Item;
import com.mpikuza.vendingmachine.service.InventoryService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

@Service
@Getter
@Setter
@AllArgsConstructor
public class FileInitializationService implements InitializationService {

    private InventoryService inventoryService;

    @Override
    public void initCoins(String filePath) {
        List<Coin> coins;
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            coins = reader.readAll().stream().map(e -> new Coin(Denomination.valueOf(e[0]), parseInt(e[1]))).toList();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(format("Wrong coin initialization path: %s", filePath), e);
        }
        inventoryService.setVendingMachineBalance(coins);
        System.out.println("Initialized coins: " + coins);
    }

    @Override
    public void initItems(String filePath) {
        List<Item> items = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            reader.readAll().forEach(e -> {
                for (int i = parseInt(e[2]); i >= 0; i--) {
                    items.add(new Item((e[0]), parseInt(e[1])));
                }
            });
        } catch (IOException | CsvException e) {
            throw new RuntimeException(format("Wrong item initialization path:  %s", filePath), e);
        }
        inventoryService.setItems(items);
        System.out.println("Initialized items: " + items);
    }

}




