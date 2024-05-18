package com.github.supercoding.repository.passenger;

import com.github.supercoding.repository.users.UserEntity;
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

    //이게 문제
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private UserEntity user;

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
