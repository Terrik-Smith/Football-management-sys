package com.terrik.footballmanager.controller;

import com.terrik.footballmanager.entity.Player;
import com.terrik.footballmanager.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    // GET all players
    @GetMapping
    public List<Player> getAllPlayers() {
        return service.getAllPlayers();
    }

    // GET player by ID
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return service.getPlayerById(id);
    }

    // POST create player
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return service.savePlayer(player);
    }

    // PUT update player
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Long id,
                               @RequestBody Player updatedPlayer) {
        return service.updatePlayer(id, updatedPlayer);
    }

    // PUT assign player to a team
    @PutMapping("/{playerId}/team/{teamId}")
    public Player assignTeam(@PathVariable Long playerId,
                             @PathVariable Long teamId) {
        return service.assignTeam(playerId, teamId);
    }

    // DELETE player
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        service.deletePlayer(id);
    }
}