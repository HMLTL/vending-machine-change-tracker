package com.mpikuza.vendingmachine.util;

import com.mpikuza.vendingmachine.model.Coin;
import com.mpikuza.vendingmachine.model.Denomination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ChangeFinderTest {

    private static final int COIN_COUNT = 5;

    List<Coin> coins;
    @InjectMocks
    ChangeFinder underTest;

    @BeforeEach
    void beforeTest(){
        coins = new ArrayList<>();
        coins.add(new Coin(Denomination.TWO_POUND, COIN_COUNT));
        coins.add(new Coin(Denomination.ONE_POUND, COIN_COUNT));
        coins.add(new Coin(Denomination.FIFTY_PENCE, COIN_COUNT));
        coins.add(new Coin(Denomination.TWENTY_PENCE, COIN_COUNT));
        coins.add(new Coin(Denomination.TEN_PENCE, COIN_COUNT));
        coins.add(new Coin(Denomination.FIVE_PENCE, COIN_COUNT));
    }

    @Test
    void testCase1() {
        List<Coin> change = underTest.find(coins, 45);
        assertThat(change).asList()
                .contains(new Coin(Denomination.FIVE_PENCE, 1),
                        new Coin(Denomination.TWENTY_PENCE, 2));
        assertThat(change).hasSize(2);
    }

    @Test
    void testCase2() {
        List<Coin> change = underTest.find(coins, 120);
        assertThat(change).asList()
                .contains(new Coin(Denomination.TWENTY_PENCE, 1),
                        new Coin(Denomination.ONE_POUND, 1));
        assertThat(change).hasSize(2);
    }

    @Test
    void testCase3() {
        List<Coin> change = underTest.find(coins, 500);
        assertThat(change).asList()
                .contains(new Coin(Denomination.ONE_POUND, 1),
                        new Coin(Denomination.TWO_POUND, 2));
        assertThat(change).hasSize(2);
    }

    @Test
    void testCase4() {
        List<Coin> change = underTest.find(coins, 1400);
        assertThat(change).asList()
                .contains(new Coin(Denomination.ONE_POUND, 4),
                        new Coin(Denomination.TWO_POUND, 5));
        assertThat(change).hasSize(2);
    }

    @Test
    void testCase5() {
        List<Coin> change = underTest.find(coins, 35);
        assertThat(change).asList()
                .contains(new Coin(Denomination.FIVE_PENCE, 1),
                        new Coin(Denomination.TEN_PENCE, 1),
                        new Coin(Denomination.TWENTY_PENCE, 1));
        assertThat(change).hasSize(3);
    }

    @Test
    void testCase6() {
        List<Coin> change = underTest.find(coins, 75);
        assertThat(change).asList()
                .contains(new Coin(Denomination.FIVE_PENCE, 1),
                        new Coin(Denomination.TWENTY_PENCE, 1),
                        new Coin(Denomination.FIVE_PENCE, 1));
        assertThat(change).hasSize(3);
    }

    @Test
    void testCase7() {
        assertThatThrownBy(() -> underTest.find(coins, 7500))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("We do not have enough coins to provide change. Sorry.");
    }

}
