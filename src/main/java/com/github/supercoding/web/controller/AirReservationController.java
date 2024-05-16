package com.github.supercoding.web.controller;

import com.github.supercoding.service.AirReservationService;
import com.github.supercoding.service.exceptions.InValidValueException;
import com.github.supercoding.service.exceptions.NotFoundException;
import com.github.supercoding.web.dto.airline.ReservationRequest;
import com.github.supercoding.web.dto.airline.ReservationResult;
import com.github.supercoding.web.dto.airline.Ticket;
import com.github.supercoding.web.dto.airline.TicketResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/air-reservation")
@RequiredArgsConstructor
@Slf4j
public class AirReservationController {

    //NOTE: Controller 먼저 만든 후 Service를 필드로

    private final AirReservationService airReservationService;



    @GetMapping("/tickets")
    public ResponseEntity findAirlineTickets(@RequestParam("user-id") Integer userId,
                                             @RequestParam("airline-ticket-type") String ticketType){
    try {
        List<Ticket> tickets = airReservationService.findUserFavoritePlaceTickets(userId, ticketType);
        TicketResponse ticketResponse = new TicketResponse(tickets);
        return new ResponseEntity<>(ticketResponse, HttpStatus.OK);
    } catch (InValidValueException ive) {
        log.error("Client 요청에 문제가 있어 다음처럼 출력합니다." + ive.getMessage()); // 우리 쪽 위한거
        return new ResponseEntity<>(ive.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (NotFoundException nfe){
        log.error("Client 요청에 문제가 있어 다음처럼 출력합니다." + nfe.getMessage());
        return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
    }
    }

    @PostMapping("/reservations")
    public ReservationResult makeReservation(@RequestBody ReservationRequest reservationRequest){
        return airReservationService.makeReservation(reservationRequest);
    }


}
