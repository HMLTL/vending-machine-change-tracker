package com.mpikuza.vendingmachine.service.maintenance;

public interface InitializationService {
    void initCoins(String filePath);
    void initItems(String filePath);
}
