package com.example.springtransiaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ReservationService2 {

    private final TicketRepo ticketRepo;
    private final PassengerRepo passengerRepo;
    private BigDecimal TICKET_PRICE = BigDecimal.valueOf(150);

    public ReservationService2(TicketRepo ticketRepo, PassengerRepo passengerRepo) {
        this.ticketRepo = ticketRepo;
        this.passengerRepo = passengerRepo;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void reserveTicket2(){
        Ticket ticket = new Ticket();
        ticket.setName("Canada");
        ticketRepo.save(ticket);
        throw new RuntimeException("e");
    }

}
