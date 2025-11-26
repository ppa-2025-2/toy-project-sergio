package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Controller.DTO.AcceptTicketDTO;
import com.example.demo.Controller.DTO.TicketDTO;
import com.example.demo.Repository.TicketRepository;
import com.example.demo.Repository.Entity.Ticket;
import com.example.demo.Repository.Entity.Ticket.Status;
import com.example.demo.Repository.Entity.TicketUser;

@Service
public class TicketBusiness {

    private TicketRepository ticketRepository;

    public TicketBusiness (TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public ResponseEntity<List<Ticket>> ListarTickets() {
        return ResponseEntity.ok(ticketRepository.findAll());
    }

    public void CadastrarTickets(TicketDTO ticket) {
        if (ticket.criador() == null) {
            throw new IllegalArgumentException("Criador é obrigatório");
        }

        Ticket newTicket = new Ticket();
        newTicket.setCriadorId(ticket.criador());
        newTicket.setDestinatarioId(ticket.destinatario());
        newTicket.setObjeto(ticket.objeto());
        newTicket.setAcao(ticket.acao());
        newTicket.setDetalhes(ticket.detalhes());
        newTicket.setLocal(ticket.local());
        newTicket.setStatus(Status.PENDENTE);

        for (Integer id : ticket.observadores()) {

        TicketUser ticketUsers = new TicketUser();
            ticketUsers.setUserId(id);
            ticketUsers.setTicket(newTicket);
            newTicket.getObservadores().add(ticketUsers);
        }
        
        ticketRepository.save(newTicket);
    }

    public void AtualizarTickets(Integer idTicket, AcceptTicketDTO ticket) {
        if (ticket.responsavel() == null) {
            throw new IllegalArgumentException("Responsavel é obrigatório");
        }

        Ticket updatedTicket = ticketRepository.findById(idTicket).orElseThrow();

        
        if (updatedTicket.getStatus() == Status.CANCELADO || updatedTicket.getStatus() == Status. CONCLUIDO) {
            throw new IllegalArgumentException("Status Atual Imutavel");
        }

        if (ticket.novoStatus() == Status.PENDENTE) {
            throw new IllegalArgumentException("Status Invalido");
        }

        if (ticket.novoStatus() == Status.CONCLUIDO && updatedTicket.getStatus() == Status.PENDENTE) {
            throw new IllegalArgumentException("Nao eh possivel finalizar um ticket que nao esta em andamento");
        }

        //User responsavel = userRepository.findById(ticket.responsavel()).orElseThrow();

        //updatedTicket.setResponsavel(responsavel);
        updatedTicket.setStatus(ticket.novoStatus());

        ticketRepository.save(updatedTicket);
    }
}
