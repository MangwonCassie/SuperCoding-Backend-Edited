package com.github.supercoding.web.dto.airline;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Ticket {

    private String depart;
    private String arrival;
    private String departureTime;
    private String returnTime;
    private Integer ticketId;

    public Ticket() {
    }

    public String getDepart() {
        return depart;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public Integer getTicketId() {
        return ticketId;
    }
}
