package com.github.supercoding.repository.passenger;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "passengerId")
@Builder
public class Passenger {
    private Integer passengerId;
    private Integer userId;
    private String passportNum;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Passenger)) {
            return false;
        }

        Passenger passenger = (Passenger) o;

        return passengerId.equals(passenger.passengerId);
    }

    @Override
    public int hashCode() {
        return passengerId.hashCode();
    }


}
