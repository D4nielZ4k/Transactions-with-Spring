package com.example.springtransiaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ReservationService {

    private final PassengerRepo passengerRepo;
    private final ReservationService2 reservationService2;
    private BigDecimal TICKET_PRICE = BigDecimal.valueOf(150);

    public ReservationService(PassengerRepo passengerRepo, ReservationService2 reservationService2) {
        this.passengerRepo = passengerRepo;
        this.reservationService2 = reservationService2;
    }


    @Transactional(propagation = Propagation.MANDATORY)
    public void reserveTicket() {
        Passenger passenger = new Passenger();
        passenger.setName("kristofer");
        passenger.setAccountBalance(new BigDecimal(250));
        passengerRepo.save(passenger);
        validateCreditLimit(passenger.getAccountBalance());
        passenger.setAccountBalance(credit(passenger.getAccountBalance()));

        try {
            reservationService2.reserveTicket2();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private boolean validateCreditLimit(BigDecimal accountBalance) {
        if (accountBalance.compareTo(TICKET_PRICE) < 0) {
            throw new CreditLimitException();
        } else
            return true;

    }

    private BigDecimal credit(BigDecimal accountBalance) {
        return accountBalance.subtract(TICKET_PRICE);
    }
}
