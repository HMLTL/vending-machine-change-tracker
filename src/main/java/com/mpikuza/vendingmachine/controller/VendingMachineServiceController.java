package com.mpikuza.vendingmachine.controller;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.service.InventoryService;
import com.mpikuza.vendingmachine.service.maintenance.VendingMachineInitializationFactory;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

import static java.util.Objects.isNull;

@ShellComponent
@AllArgsConstructor
@ShellCommandGroup("Vending Machine Service Commands")
public class VendingMachineServiceController {

    private VendingMachineInitializationFactory vendingMachineInitializationFactory;
    private InventoryService inventoryService;

    @ShellMethod(value = "Initialize the vending machine with coins (ic -v coins.csv or ic -v or ic -v default)", key = "ic")
    public void initCoins(@ShellOption("-v") String path) {
        if (isNull(path)) {
            vendingMachineInitializationFactory.getDefaultInitializationService().initCoins(path);
        } else {
            vendingMachineInitializationFactory.getFileInitializationService().initCoins(path);
        }
    }

    @ShellMethod(value = "Lookup Vending Machine inventory coins (lk)", key = "lk")
    public List<Coin> lookupCoins() {
        return inventoryService.getVendingMachineBalance();
    }

    @ShellMethod(value = "Initialize the vending machine with items (ii -v items.csv or ii -v or ii -v default)", key = "ii")
    public void initItems(@ShellOption("-v") String path) {
        if (isNull(path)) {
            vendingMachineInitializationFactory.getDefaultInitializationService().initItems(path);
        } else {
            vendingMachineInitializationFactory.getFileInitializationService().initItems(path);
        }
    }

}

