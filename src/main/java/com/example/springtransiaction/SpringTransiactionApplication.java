package com.example.springtransiaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@SpringBootApplication
public class SpringTransiactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTransiactionApplication.class, args);
    }

    private final ReservationService reservationService;

    public SpringTransiactionApplication(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() throws SQLException {
        reservationService.reserveTicket();
    }
}
