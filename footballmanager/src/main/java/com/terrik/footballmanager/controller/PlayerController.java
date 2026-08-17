package com.terrik.footballmanager.controller;

import com.terrik.footballmanager.dto.PlayerDTO;
import com.terrik.footballmanager.entity.Player;
import com.terrik.footballmanager.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    // GET all players
    @GetMapping
    public List<PlayerDTO> getAllPlayers() {

        return service.getAllPlayers()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // GET player by ID
    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Long id) {

        Player player = service.getPlayerById(id);

        if (player == null) {
            return null;
        }

        return convertToDTO(player);
    }

    // POST create player
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return service.savePlayer(player);
    }

    // PUT update player
    @PutMapping("/{id}")
    public Player updatePlayer(
            @PathVariable Long id,
            @RequestBody Player updatedPlayer) {

        return service.updatePlayer(id, updatedPlayer);
    }

    // PUT assign player to a team
    @PutMapping("/{playerId}/team/{teamId}")
    public Player assignTeam(
            @PathVariable Long playerId,
            @PathVariable Long teamId) {

        return service.assignTeam(playerId, teamId);
    }

    // DELETE player
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        service.deletePlayer(id);
    }

    // Convert Player entity into PlayerDTO
    private PlayerDTO convertToDTO(Player player) {

        String teamName = null;

        if (player.getTeam() != null) {
            teamName = player.getTeam().getTeamName();
        }

        return new PlayerDTO(
                player.getId(),
                player.getPlayerName(),
                player.getPosition(),
                player.getJerseyNumber(),
                player.getAge(),
                teamName
        );
    }
}