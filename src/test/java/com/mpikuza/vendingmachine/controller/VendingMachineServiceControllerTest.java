package com.mpikuza.vendingmachine.controller;

import com.mpikuza.vendingmachine.service.maintenance.FileInitializationService;
import com.mpikuza.vendingmachine.service.maintenance.VendingMachineInitializationFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class VendingMachineServiceControllerTest {

    private final static String PATH = "path";

    @Mock
    private VendingMachineInitializationFactory vendingMachineInitializationFactory;
    @Mock
    private FileInitializationService fileInitializationService;

    @InjectMocks
    private VendingMachineServiceController underTest;

    @Test
    void initCoins() {
        when(vendingMachineInitializationFactory.getFileInitializationService()).thenReturn(fileInitializationService);
        doNothing().when(fileInitializationService).initCoins(eq(PATH));

        underTest.initCoins(PATH);

        verify(fileInitializationService).initCoins(eq(PATH));
    }
}