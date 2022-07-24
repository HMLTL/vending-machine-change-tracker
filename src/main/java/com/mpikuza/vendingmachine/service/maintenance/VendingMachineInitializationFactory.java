package com.mpikuza.vendingmachine.service.maintenance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
public class VendingMachineInitializationFactory {
    private FileInitializationService fileInitializationService;
    private DefaultInitializationService defaultInitializationService;
}
