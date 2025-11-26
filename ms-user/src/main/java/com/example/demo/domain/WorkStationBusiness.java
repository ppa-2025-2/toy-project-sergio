package com.example.demo.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Controller.DTO.requests.CreateTicketRequest;
import com.example.demo.Repository.Entity.User;
import com.example.demo.domain.interfaces.ITicketService;

@Service
public class WorkStationBusiness {

    private ITicketService ticketService;

    public WorkStationBusiness(ITicketService ticketService){
        this.ticketService = ticketService;
    }

    public void agendarAlocacao(User user, Integer criadorId){
        CreateTicketRequest ticketRequest = new CreateTicketRequest(
            criadorId, 
            user.getId(),
            List.of(criadorId), 
            String.format("Usuário: %s; ID: %d", user.getHandle(), user.getId()), 
            "Alocar Workstation", 
            "Alocar workstation para novo usuário", 
            "presencial");
        
        ticketService.createTicket(ticketRequest);
    }
}
