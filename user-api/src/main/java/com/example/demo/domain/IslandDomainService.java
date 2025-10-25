package com.example.demo.domain;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.repository.entity.Island;
import com.example.demo.repository.entity.User;
import com.example.demo.repository.entity.Workstation;

@Service
public class IslandDomainService {

    //aqui eu tentei usar o conceito do bob no clean code, onde ele fala que um método deve ter poucas linhas
    //então criei um método pra cada "lógica específica", acho que ficou mais clean mesmo
    public Island getIslandFreeAndAttachUser(User user, List<Island> islands) {
        if (islands.isEmpty()) throw new IllegalStateException("Workstations not available");

        Island freeIsland = getBestFreeIsland(islands);
        attachUserOnWorkstation(freeIsland, user);

        return freeIsland;
    }

    private Island getBestFreeIsland(List<Island> islands) {
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

    private void attachUserOnWorkstation(Island island, User user) {
        island.getWorkstations().stream()
                .filter(ws -> ws.getUser() == null)
                .findFirst()
                .ifPresent(ws -> ws.setUser(user));
    }
    
}
