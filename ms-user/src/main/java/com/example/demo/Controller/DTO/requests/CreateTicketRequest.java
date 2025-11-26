package com.example.demo.Controller.DTO.requests;

import java.util.List;

public record CreateTicketRequest (
    Integer criador,
    Integer destinatario,
    List<Integer> observadores,
    String objeto,
    String acao,
    String detalhes,
    String local
) {}
