package com.github.supercoding.service;

import com.github.supercoding.repository.airlineTicket.AirLineTicketRepository;
import com.github.supercoding.repository.users.UserJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AirReservationServiceUnitTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private AirLineTicketRepository airLineTicketRepository;

    private AirReservationService airReservationService;

    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("airlineTicket에 해당하는 유저항공권들이 모두 있어서 성공하는 경우")
    @Test
    void findUserFavoritePlaceTicketcase1() {
    }
}