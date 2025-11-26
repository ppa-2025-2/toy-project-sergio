package com.example.demo.domain;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Controller.DTO.requests.CreateTicketRequest;
import com.example.demo.domain.interfaces.ITicketService;

@Service
public class ExternalTicketService implements ITicketService {

    private final RestTemplate http;
    private final String ticketServiceUrl;
    private static Logger logger = LoggerFactory.getLogger(ExternalTicketService.class.getName());

    public ExternalTicketService(
        RestTemplate http,
        @Value("${ticket.service.url}")
        String ticketServiceUrl
    ) {
        this.http = http;
        this.ticketServiceUrl = ticketServiceUrl;
    }

    @Override
    @Async
    public void createTicket(CreateTicketRequest createTicketRequest) {
        http.postForEntity(
            ticketServiceUrl,
            Map.of(
                "criador", createTicketRequest.criador(),
                "destinatario", createTicketRequest.destinatario(),
                "observadores", createTicketRequest.observadores(),
                "objeto", createTicketRequest.objeto(),
                "acao", createTicketRequest.acao(),
                "detalhes", createTicketRequest.detalhes(),
                "local", createTicketRequest.local()
            ),
            Void.class
        );
    }

}
