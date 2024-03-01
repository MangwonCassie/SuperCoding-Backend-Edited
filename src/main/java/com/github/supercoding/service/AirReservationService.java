package com.github.supercoding.service;

import com.github.supercoding.web.dto.airline.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirReservationService {
    public List<Ticket> findUserFavoritePlaceTickets(Integer userId, String ticketType) {
        //필요한 Repository: UserRepository, airLineTicket Repository,
    }
}
