package com.github.supercoding.repository.reservations;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest //대표적인 slice test => Dao Layer / Jpa 사용하고있는 Slice Test / 어노테이션 꼭 적어줘야함
class ReservationJpaRepositoryJpaTest {

    @Test
    void findFlightPriceAndCharge() {
    }
}