package com.example.demo.domain;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.repository.entity.Island;
import com.example.demo.repository.entity.Workstation;

@Service
public class IslandDomainService {


    public Island getBestFreeIsland(List<Island> islands) {
        Island freeIsland = islands.getFirst();
        for (int slots = 1; slots < Island.Disposition.CIRCULAR.getPlacements(); slots++) {
            final int positions = slots;
            var possibleIsland = islands.stream()
                    .filter(i -> i.getWorkstations().stream()
                            .map(Workstation::getUser)
                            .filter(Objects::nonNull)
                            .count() == positions)
                    .findFirst();
            if (possibleIsland.isPresent()) {
                freeIsland = possibleIsland.get();
                break;
            }
        }

        return freeIsland;
    }
    
}
