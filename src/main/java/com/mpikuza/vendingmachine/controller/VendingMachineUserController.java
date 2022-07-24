package com.mpikuza.vendingmachine.controller;

import com.mpikuza.vendingmachine.service.user.DepositService;
import com.mpikuza.vendingmachine.service.user.OrderService;
import com.mpikuza.vendingmachine.service.user.ReturnCoinService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Size;

@ShellComponent
@AllArgsConstructor
@ShellCommandGroup("Vending Machine User Commands")
public class VendingMachineUserController {

    private DepositService depositService;
    private ReturnCoinService returnCoinService;
    private OrderService orderService;

    @ShellMethod(value = "Top up user balance by coin (add -v 50) [5,10,20,50,100,200]", key = "add")
    public void depositCoin(@ShellOption("-v") Integer denomination) {
        depositService.add(denomination);
    }

    @ShellMethod(value = "Make order (get -v cola) [cola,crackers,snack,beer,crisp,cider] ", key = "get")
    public void makeOrder(@ShellOption("-v") @Size String name) {
        orderService.makeOrder(name);
    }

    @ShellMethod(value = "Return deposited coins (return)", key = "return")
    public void returnCoins() {
        returnCoinService.returnDepositedCoins();
    }
}

