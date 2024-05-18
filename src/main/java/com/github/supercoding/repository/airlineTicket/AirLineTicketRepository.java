package com.github.supercoding.repository.airlineTicket;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface AirLineTicketRepository {
    List<AirlineTicket> findAllAirlineTicketsWithPlaceAndTicketType(String likePlace, String ticketType);

    List<AirlineTicketAndFlightInfo> findAllAirlineTicketAndFlightInfo(Integer airlineTicketId);
}
