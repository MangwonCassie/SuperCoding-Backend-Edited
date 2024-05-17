package com.github.supercoding.repository.airlineTicket;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AirlineTicket {
    private Integer ticketId;
    private String ticketType;
    private String departureLocation;
    private String arrivalLocation;
    private LocalDateTime departureAt;
    private LocalDateTime returnAt;
    private Double tax;
    private Double totalPrice;


//지우고 builder를 클래스로 위에 하래
//    public AirlineTicket(Integer ticketId, String ticketType, String departureLocation, String arrivalLocation, Date departureAt, Date returnAt, Double tax, Double totalPrice) {
//        this.ticketId = ticketId;
//        this.ticketType = ticketType;
//        this.departureLocation = departureLocation;
//        this.arrivalLocation = arrivalLocation;
//        this.departureAt = departureAt.toLocalDate().atStartOfDay();
//        this.returnAt = returnAt.toLocalDate().atStartOfDay();
//        this.tax = tax;
//        this.totalPrice = totalPrice;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AirlineTicket)) {
            return false;
        }

        AirlineTicket that = (AirlineTicket) o;

        return ticketId.equals(that.ticketId);
    }

    @Override
    public int hashCode() {
        return ticketId.hashCode();
    }
}
