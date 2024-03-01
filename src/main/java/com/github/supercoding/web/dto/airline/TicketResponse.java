package com.github.supercoding.web.dto.airline;

import java.util.List;

public class TicketResponse {
    private List<Ticket> tickets;

    public TicketResponse() {
    }

    public TicketResponse(List<Ticket> tickets) {
    }

    public List<Ticket> getTickets() {
        return tickets;
    }


}
