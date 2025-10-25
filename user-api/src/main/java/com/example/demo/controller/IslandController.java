package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.IslandService;

@RestController
@RequestMapping("/api/v1/islands")
public class IslandController {
    private final IslandService islandService;

    public IslandController(IslandService islandService) {
        this.islandService = islandService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void alocarWorkstationDisponivel(@RequestBody Integer userId) {
        islandService.alocarWorkstationDisponivel(userId);
    }
}
