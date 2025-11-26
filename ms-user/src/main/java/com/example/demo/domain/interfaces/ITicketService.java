package com.example.demo.domain.interfaces;

import org.springframework.scheduling.annotation.Async;

import com.example.demo.Controller.DTO.requests.CreateTicketRequest;

public interface ITicketService {
    @Async
    void createTicket(CreateTicketRequest createTicketRequest);
}
