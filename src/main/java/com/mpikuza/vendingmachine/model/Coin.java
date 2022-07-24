package com.mpikuza.vendingmachine.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Coin {
    private Denomination denomination;
    private Integer count;

    public Integer getValue() {
        return denomination.getValue() * count;
    }

}
