package com.github.supercoding.repository.reservations;

import com.github.supercoding.service.AirReservationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest //대표적인 slice test => Dao Layer / Jpa 사용하고있는 Slice Test / 어노테이션 꼭 적어줘야함
class ReservationJpaRepositoryJpaTest {

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @Test
    void findFlightPriceAndCharge() {

        //given
        Integer userId = 10;

        //when
        List<FlightPriceAndCharge> flightPriceAndCharges = reservationJpaRepository.findFlightPriceAndCharge(userId);

        //then
        log.info("결과:" + flightPriceAndCharges);
    }
}