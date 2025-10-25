package com.example.demo.application;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.domain.IslandDomainService;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.repository.IslandRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Island;

@Service
public class IslandService {

    private final IslandRepository islandRepository;
    private final UserRepository userRepository;
    private final IslandDomainService islandDomainService;

    public IslandService(
        IslandRepository islandRepository,
        UserRepository userRepository,
        IslandDomainService islandDomainService
    ) {
        this.islandRepository = islandRepository;
        this.userRepository = userRepository;
        this.islandDomainService = islandDomainService;
    }

    public void alocarWorkstationDisponivel(@NonNull Integer userId) {
        final var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException());
        final var islands = islandRepository.findIslandWithAvailableWorkstations();

        Island freeIsland = islandDomainService.getIslandFreeAndAttachUser(user, islands);

        islandRepository.save(freeIsland);
    }
    
}
