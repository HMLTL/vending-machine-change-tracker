package com.mpikuza.vendingmachine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static java.lang.String.format;

@AllArgsConstructor
@Getter
public enum Denomination {
    FIVE_PENCE(5),
    TEN_PENCE(10),
    TWENTY_PENCE(20),
    FIFTY_PENCE(50),
    ONE_POUND(100),
    TWO_POUND(200);

    private final Integer value;

    public static Denomination fromValue(Integer denomination) {
        for (Denomination c : Denomination.values()) {
            if (c.getValue().equals(denomination)) {
                return c;
            }
        }
        throw new IllegalArgumentException(format("Unsupported coin denomination: %s", denomination));
    }

}
