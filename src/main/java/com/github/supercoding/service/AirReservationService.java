package com.github.supercoding.service;

import com.github.supercoding.repository.airlineTicket.AirLineTicketRepository;
import com.github.supercoding.repository.airlineTicket.AirlineTicket;
import com.github.supercoding.repository.airlineTicket.AirlineTicketAndFlightInfo;
import com.github.supercoding.repository.airlineTicket.AirlineTicketJpaRepository;
import com.github.supercoding.repository.passenger.Passenger;
import com.github.supercoding.repository.passenger.PassengerRepository;
import com.github.supercoding.repository.reservations.Reservation;

import com.github.supercoding.repository.reservations.ReservationRepository;
import com.github.supercoding.repository.users.UserEntity;
import com.github.supercoding.repository.users.UserJpaRepository;
import com.github.supercoding.repository.users.UserRepository;
import com.github.supercoding.service.exceptions.InValidValueException;
import com.github.supercoding.service.exceptions.NotFoundException;
import com.github.supercoding.service.mapper.TicketMapper;
import com.github.supercoding.web.dto.airline.ReservationRequest;
import com.github.supercoding.web.dto.airline.ReservationResult;
import com.github.supercoding.web.dto.airline.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AirReservationService {

    private final UserRepository userRepository;

    private final UserJpaRepository userJpaRepository;

    private final AirLineTicketRepository airLineTicketRepository;

    private final PassengerRepository passengerRepository;

    private final ReservationRepository reservationRepository;

    private final AirlineTicketJpaRepository airlineTicketJpaRepository;


    public List<Ticket> findUserFavoritePlaceTickets(Integer userId, String ticketType) {
        //필요한 Repository: UserRepository, airLineTicket Repository
        //1. 유저를 userId로 가져와서, 선호하는 여행지 도출
        //2. 선호하는 여행지와 ticketType으로 AirLineTicket Table 질의해서 필요한 AirLineTicket 들고오기
        //3. 이 둘의 정보를 조합해서 Ticket DTO를 만든다.

        Set<String> ticketTypeSet = new HashSet<>(Arrays.asList("편도", "왕복"));

        if(!ticketTypeSet.contains(ticketType))
            throw new InValidValueException("해당 TicketType" + ticketType + "은 지원하지않습니다." );


        UserEntity userEntity = userJpaRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 id: " + userId + " 유저를 찾을 수 없습니다."));

        String likePlace =  userEntity.getLikeTravelPlace();

//        List<AirlineTicket> airlineTickets = airLineTicketRepository.findAllAirlineTicketsWithPlaceAndTicketType(likePlace, ticketType);

        List<AirlineTicket> airlineTickets
                = airlineTicketJpaRepository.findAirlineTicketsByArrivalLocationAndTicketType(likePlace, ticketType);

        if (airlineTickets.isEmpty())
            throw new NotFoundException("해당 likePlace: " + likePlace + " 와 TicketType: " + ticketType + "에 해당하는 항공권 찾을 수 없습니다.");
       List<Ticket> tickets = airlineTickets.stream().map(TicketMapper.INSTANCE::airlineTicketToTicket).collect(Collectors.toList());
        return tickets;
    }


    @Transactional(transactionManager = "tm2")
    public ReservationResult makeReservation(ReservationRequest reservationRequest) {
        //NOTE: 1. Reservation Repository, Join Table (flight/airline_ticket), userId가져올 때 User테이블 아닌 passenger 테이블에서 가져옴

        //ReservationRequest 에 UserId, AirlineTicketId 있으니까 가져올 수 있음
        Integer userId = reservationRequest.getUserId();
        Integer airlineTicketId = reservationRequest.getAirlineTicketId();

        //1. Passenger
        Passenger passenger = passengerRepository.findPassengerByUserId(userId)
                .orElseThrow(() -> new NotFoundException("요청하신 userId" + userId + "에 해당하는 Passenger를 찾을 수 없습니다."));
        Integer passengerId = passenger.getPassengerId();
        
        //2. price 등 정보 가져오기
        
       List<AirlineTicketAndFlightInfo> airlineTicketAndFlightInfo = airLineTicketRepository.
               findAllAirlineTicketAndFlightInfo(airlineTicketId); //조인해서 불러올 예정
        //3. reservation 생성
        Reservation reservation = new Reservation(passengerId, airlineTicketId);
        Boolean isSuccess = reservationRepository.saveReservation(reservation);
        
        //4. TODO: Reservation DTO 만들기

        List<Integer> prices = airlineTicketAndFlightInfo.stream().map(AirlineTicketAndFlightInfo::getPrice).collect(Collectors.toList());
        List<Integer> charges = airlineTicketAndFlightInfo.stream().map(AirlineTicketAndFlightInfo::getCharge).collect(Collectors.toList());
        Integer tax = airlineTicketAndFlightInfo.stream().map(AirlineTicketAndFlightInfo::getTax).findFirst().get();
        Integer totalPrice = airlineTicketAndFlightInfo.stream().map(AirlineTicketAndFlightInfo::getTotalPrice).findFirst().get();

        return new ReservationResult(prices, charges, tax, totalPrice, isSuccess);
    }
}
