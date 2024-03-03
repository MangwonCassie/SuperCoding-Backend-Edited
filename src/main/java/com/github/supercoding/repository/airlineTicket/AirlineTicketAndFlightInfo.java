package com.github.supercoding.repository.airlineTicket;

public class AirlineTicketAndFlightInfo {
    //이 파트 very impo success 파트 빼고 다 얻어와야함

    private Integer ticketId;
    private Integer price;
    private Integer charge;
    private Integer tax;
    private Integer totalPrice;

    public AirlineTicketAndFlightInfo(Integer ticketId, Integer price, Integer charge, Integer tax, Integer totalPrice) {
        this.ticketId = ticketId;
        this.price = price;
        this.charge = charge;
        this.tax = tax;
        this.totalPrice = totalPrice;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
