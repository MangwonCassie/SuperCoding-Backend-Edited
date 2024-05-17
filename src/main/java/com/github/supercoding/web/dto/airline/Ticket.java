package com.github.supercoding.web.dto.airline;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.supercoding.repository.airlineTicket.AirlineTicket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Ticket {

    private String depart;
    private String arrival;
    private String departureTime;
    private String returnTime;
    private Integer ticketId;


}
