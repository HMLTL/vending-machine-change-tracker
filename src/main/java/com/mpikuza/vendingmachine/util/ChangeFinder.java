package com.mpikuza.vendingmachine.util;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Denomination;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeFinder {
    public List<Coin> find(List<Coin> coins, int changeToReturn) {

        int[][] dp = new int[coins.size()][changeToReturn + 1];
        int[][] countTable = new int[coins.size()][changeToReturn + 1];

        int idx = 0;

        while (coins.get(idx).getDenomination().getValue() > changeToReturn) {
            idx++;
        }

        for (int currencyIndex = idx; currencyIndex < dp.length; currencyIndex++) {
            for (int change = 1; change < dp[0].length; change++) {
                fillTables(dp, coins, change, currencyIndex, countTable);
            }
        }
        return getCoins(coins, changeToReturn, dp, countTable);
    }

    private List<Coin> getCoins(List<Coin> machineCoins, Integer changeToReturn, int[][] dp, int[][] countTable) {
        List<Coin> changeResult = new ArrayList<>();
        int tempChangeToReturn = changeToReturn;
        if (dp[machineCoins.size() - 1][changeToReturn] == changeToReturn) {
            for (int i = machineCoins.size() - 1; i >= 0; i--) {
                if (tempChangeToReturn > 0) {
                    Denomination denomination = machineCoins.get(i).getDenomination();
                    int count = countTable[i][tempChangeToReturn];
                    if (count > 0) {
                        changeResult.add(new Coin(denomination, count));
                    }
                    machineCoins.get(i).setCount(machineCoins.get(i).getCount() - count);
                    tempChangeToReturn -= count * denomination.getValue();
                } else {
                    break;
                }
            }
        } else {
            throw new UnsupportedOperationException("We do not have enough coins to provide change. Sorry.");
        }
        return changeResult;
    }

    private void fillTables(int[][] dp, List<Coin> coins, int change, int idx, int[][] countTable) {
        int numberOfCoins = change / coins.get(idx).getDenomination().getValue();
        if (numberOfCoins > coins.get(idx).getCount()) {
            numberOfCoins = coins.get(idx).getCount();
        }
        if (idx - 1 >= 0) {
            int i = 0;
            int tempChange = change;
            while (i <= numberOfCoins) {
                if (dp[idx - 1][tempChange] + i * coins.get(idx).getDenomination().getValue() == change) {
                    countTable[idx][change] = i;
                    dp[idx][change] = dp[idx - 1][tempChange] + i * coins.get(idx).getDenomination().getValue();
                    break;
                }
                i++;
                tempChange -= coins.get(idx).getDenomination().getValue();
            }
        } else {
            if (numberOfCoins * coins.get(idx).getDenomination().getValue() == change) {
                countTable[idx][change] = numberOfCoins;
                dp[idx][change] = numberOfCoins * coins.get(idx).getDenomination().getValue();

            } else {
                dp[idx][change] = 0;
            }
        }
    }
}
