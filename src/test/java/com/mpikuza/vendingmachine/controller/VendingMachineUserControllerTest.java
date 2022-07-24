package com.mpikuza.vendingmachine.controller;

import com.mpikuza.vendingmachine.service.user.DepositService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
class VendingMachineUserControllerTest {

    private static final int DENOMINATION = 50;

    @Mock
    private DepositService depositService;

    @InjectMocks
    private VendingMachineUserController underTest;

    @Test
    void depositCoin() {
        underTest.depositCoin(DENOMINATION);

        verify(depositService).add(eq(DENOMINATION));
    }
}