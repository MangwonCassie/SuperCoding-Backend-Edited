package com.github.supercoding.repository.passenger;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "passengerId")
@Builder
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id @Column(name = "passenger_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer passengerId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "passport_num", length = 50)
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
