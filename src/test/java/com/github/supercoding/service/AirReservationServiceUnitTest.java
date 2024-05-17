package com.github.supercoding.service;

import com.github.supercoding.repository.airlineTicket.AirLineTicketRepository;
import com.github.supercoding.repository.airlineTicket.AirlineTicket;
import com.github.supercoding.repository.airlineTicket.AirlineTicketJpaRepository;
import com.github.supercoding.repository.users.UserEntity;
import com.github.supercoding.repository.users.UserJpaRepository;
import com.github.supercoding.web.dto.airline.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
class AirReservationServiceUnitTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private AirlineTicketJpaRepository airlineTicketJpaRepository;

    @InjectMocks
    private AirReservationService airReservationService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("airlineTicket에 해당하는 유저항공권들이 모두 있어서 성공하는 경우")
    @Test
    void findUserFavoritePlaceTicketcase1() {

        // given
        Integer userId = 5;
        String likePlace = "파리";
        String ticketType = "왕복";


        UserEntity userEntity = UserEntity.builder()
                .userId(userId)
                .likeTravelPlace(likePlace)
                .userName("name1")
                .phoneNum("1234")
                .build();

        List<AirlineTicket> airlineTickets = Arrays.asList(
                AirlineTicket.builder()
                        .ticketId(1)
                        .arrivalLocation(likePlace)
                        .ticketType(ticketType)
                        .build(),
                AirlineTicket.builder()
                        .ticketId(2)
                        .arrivalLocation(likePlace)
                        .ticketType(ticketType)
                        .build(),
                AirlineTicket.builder()
                        .ticketId(3)
                        .arrivalLocation(likePlace)
                        .ticketType(ticketType)
                        .build(),
                AirlineTicket.builder()
                        .ticketId(4)
                        .arrivalLocation(likePlace)
                        .ticketType(ticketType)
                        .build()
        );

        // when
        when(userJpaRepository.findById(any())).thenReturn(Optional.of(userEntity));
        when(airlineTicketJpaRepository.findAirlineTicketsByArrivalLocationAndTicketType(likePlace, ticketType))
                .thenReturn(airlineTickets);

        // then
        List<Ticket> tickets = airReservationService.findUserFavoritePlaceTickets(userId, ticketType);
        log.info("tickets: " + tickets);
        assertTrue(
                tickets.stream()
                        .map(Ticket::getArrival)
                        .allMatch((arrival) -> arrival.equals(likePlace))
        );

    }
}