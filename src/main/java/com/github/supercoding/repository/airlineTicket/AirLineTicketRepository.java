package com.github.supercoding.repository.airlineTicket;

import java.util.List;

public interface AirLineTicketRepository {
    List<AirlineTicket> findAllAirlineTicketsWithPlaceAndTicketType(String likePlace, String ticketType);
}
