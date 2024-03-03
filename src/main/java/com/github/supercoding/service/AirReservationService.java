package com.github.supercoding.service;

import com.github.supercoding.repository.airlineTicket.AirLineTicketRepository;
import com.github.supercoding.repository.airlineTicket.AirlineTicket;
import com.github.supercoding.repository.airlineTicket.AirlineTicketAndFlightInfo;
import com.github.supercoding.repository.passenger.Passenger;
import com.github.supercoding.repository.passenger.PassengerRepository;
import com.github.supercoding.repository.reservations.Reservation;

import com.github.supercoding.repository.reservations.ReservationRepository;
import com.github.supercoding.repository.users.UserEntity;
import com.github.supercoding.repository.users.UserRepository;
import com.github.supercoding.web.dto.airline.ReservationRequest;
import com.github.supercoding.web.dto.airline.ReservationResult;
import com.github.supercoding.web.dto.airline.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirReservationService {

    private UserRepository userRepository;
    private AirLineTicketRepository airLineTicketRepository;

    private PassengerRepository passengerRepository;

    private ReservationRepository reservationRepository;

    public AirReservationService(UserRepository userRepository, AirLineTicketRepository airLineTicketRepository, PassengerRepository passengerRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.airLineTicketRepository = airLineTicketRepository;
        this.passengerRepository = passengerRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Ticket> findUserFavoritePlaceTickets(Integer userId, String ticketType) {
        //필요한 Repository: UserRepository, airLineTicket Repository
        //1. 유저를 userId로 가져와서, 선호하는 여행지 도출
        //2. 선호하는 여행지와 ticketType으로 AirLineTicket Table 질의해서 필요한 AirLineTicket 들고오기
        //3. 이 둘의 정보를 조합해서 Ticket DTO를 만든다.

        UserEntity userEntity = userRepository.findUserById(userId);

        String likePlace =  userEntity.getLikeTravelPlace();

        List<AirlineTicket> airlineTickets = airLineTicketRepository.findAllAirlineTicketsWithPlaceAndTicketType(likePlace, ticketType);

       List<Ticket> tickets = airlineTickets.stream().map(Ticket:: new).collect(Collectors.toList());
        return tickets;
    }


    @Transactional
    public ReservationResult makeReservation(ReservationRequest reservationRequest) {
        //NOTE: 1. Reservation Repository, Join Table (flight/airline_ticket), userId가져올 때 User테이블 아닌 passenger 테이블에서 가져옴

        //ReservationRequest 에 UserId, AirlineTicketId 있으니까 가져올 수 있음
        Integer userId = reservationRequest.getUserId();
        Integer airlineTicketId = reservationRequest.getAirlineTicketId();

        //1. Passenger
        Passenger passenger = passengerRepository.findPassengerByUserId(userId);
        Integer passengerId = passenger.getPassengerId();
        
        //2. price 등 정보 가져오기
        
       List<AirlineTicketAndFlightInfo> airlineTicketAndFlightInfo = airLineTicketRepository.
               findAllAirlineTicketAndFlightInfo(airlineTicketId); //조인해서 불러올 예정
        //3. reservation 생성
        Reservation reservation = new Reservation(passengerId, airlineTicketId);
        Boolean isSuccess = reservationRepository.saveReservation(reservation);
        
        //4. TODO: Reservation DTO 만들기
        
    }
}
