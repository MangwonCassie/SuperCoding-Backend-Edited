package com.github.supercoding.repository.airlineTicket;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirlineTicketJdbcTemplateDao implements AirLineTicketRepository {
    private JdbcTemplate jdbcTemplate;

    public AirlineTicketJdbcTemplateDao(@Qualifier("jdbcTemplate2") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static RowMapper<AirlineTicket> airlineTicketRowMapper = (((rs, rowNum) ->
            new AirlineTicket(
                    rs.getInt("ticket_id"),
                    rs.getString("ticket_type"),
                    rs.getNString("departure_loc"),
                    rs.getNString("arrival_loc"),
                    rs.getDate("departure_at"),
                    rs.getDate("return_at"),
                    rs.getDouble("tax"),
                    rs.getDouble("total_price")
            )
    ));


    @Override
    public List<AirlineTicket> findAllAirlineTicketsWithPlaceAndTicketType(String likePlace, String ticketType) {
        return jdbcTemplate.query("SELECT * FROM airline_ticket " +
                "WHERE arrival_loc = ? AND ticket_type = ?", airlineTicketRowMapper, likePlace, ticketType);
    }

    @Override
    public List<AirlineTicketAndFlightInfo> findAllAirlineTicketAndFlightInfo(Integer airlineTicketId) {
        return null;
    }

}