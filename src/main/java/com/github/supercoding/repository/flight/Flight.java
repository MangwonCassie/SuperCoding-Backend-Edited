package com.github.supercoding.repository.flight;

import com.github.supercoding.repository.airlineTicket.AirlineTicket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "flight")
public class Flight {

   @Id
   @Column(name = "flight_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;

   // 즉, 하나의 항공권(AirlineTicket)은 여러 개의 항공편(Flight)을 가질 수 있음.
   @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "ticket_id")
//    private Integer ticket;
   private AirlineTicket airlineTicket;

    @Column(name = "departure_at")
    private LocalDateTime departAt;

    @Column(name = "arrival_at")
    private LocalDateTime arrivalAt;

    @Column(name = "departure_loc", length = 50)
    private String departureLocation;

    @Column(name = "arrival_loc", length = 50)
    private String arrivalLocation;

    @Column(name = "flight_price")
    private Double flightPrice;

    @Column(name = "charge")
    private Double charge;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Flight flight = (Flight) o;
        return flightId != null && Objects.equals(flightId, flight.flightId);
    }

    //클래스 타입 기준으로 직접 생성
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
