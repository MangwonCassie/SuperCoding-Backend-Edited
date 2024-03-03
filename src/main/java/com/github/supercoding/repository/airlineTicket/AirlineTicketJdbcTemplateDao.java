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
    
    //AirLineTicketAndFlightInfo를 위한 RowMapper는 없음 very impo ticket id를 join 통해서 가져올예정 (airline_ticket & flight)
    static RowMapper<AirlineTicketAndFlightInfo> airlineTicketAndFlightInfoRowMapper
            = (((rs, rowNum) -> 
            new AirlineTicketAndFlightInfo(
                    rs.getInt("A.ticket_id"),
                    rs.getDouble("F.flight_price"),
                    rs.getDouble(("F.charge")),
                    rs.getDouble("A.tax"),
                    rs.getDouble("A.total_price")
            )));

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