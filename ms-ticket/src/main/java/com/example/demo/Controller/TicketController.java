package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Controller.DTO.TicketDTO;
import com.example.demo.Controller.DTO.AcceptTicketDTO;
import com.example.demo.Repository.Entity.Ticket;
import com.example.demo.domain.TicketBusiness;


@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    private TicketBusiness ticketBusiness;

    public TicketController(TicketBusiness ticketBusiness) {
        this.ticketBusiness = ticketBusiness; 
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void newTicket(@RequestBody TicketDTO ticket) {
        ticketBusiness.CadastrarTickets(ticket);
    }

    @PatchMapping(value = "{idTicket}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void acceptTicketUser(@PathVariable @NonNull Integer idTicket, 
                                 @RequestBody AcceptTicketDTO ticket) {
        ticketBusiness.AtualizarTickets(idTicket, ticket);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> getTickets() {
        return ticketBusiness.ListarTickets();
    }
}
