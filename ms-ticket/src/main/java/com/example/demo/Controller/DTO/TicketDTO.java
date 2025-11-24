package com.example.demo.Controller.DTO;

import java.util.List;

public record TicketDTO(
        Integer criador,
        Integer destinatario,
        List<Integer> observadores,
        String objeto,
        String acao,
        String detalhes,
        String local
) {}
