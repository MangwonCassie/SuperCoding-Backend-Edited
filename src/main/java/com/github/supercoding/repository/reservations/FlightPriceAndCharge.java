package com.github.supercoding.repository.reservations;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlightPriceAndCharge {
    private Double flightPrice;
    private Double charge;

    public FlightPriceAndCharge(Double flightPrice, Double charge) {
        this.flightPrice = flightPrice;
        this.charge = charge;
    }
}
