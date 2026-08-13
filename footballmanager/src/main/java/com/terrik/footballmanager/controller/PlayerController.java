package com.terrik.footballmanager.controller;

import com.terrik.footballmanager.entity.Player;
import com.terrik.footballmanager.repository.PlayerRepository;
import org.springframework.web.bind.annotation.*;
import com.terrik.footballmanager.entity.FootballTeam;
import com.terrik.footballmanager.repository.FootballTeamRepository;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

private final PlayerRepository repository;
private final FootballTeamRepository teamRepository;

public PlayerController(PlayerRepository repository,
                        FootballTeamRepository teamRepository) {

    this.repository = repository;
    this.teamRepository = teamRepository;
}

    // GET all players
    @GetMapping
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
public Player getPlayerById(@PathVariable Long id) {
    return repository.findById(id).orElse(null);
}
    @PostMapping
public Player createPlayer(@RequestBody Player player) {
    return repository.save(player);
}
@PutMapping("/{playerId}/team/{teamId}")
public Player assignTeam(@PathVariable Long playerId,
                         @PathVariable Long teamId) {

    Player player = repository.findById(playerId).orElse(null);
    FootballTeam team = teamRepository.findById(teamId).orElse(null);

    if (player == null || team == null) {
        return null;
    }

    player.setTeam(team);

    return repository.save(player);
}
@PutMapping("/{id}")
public Player updatePlayer(@PathVariable Long id,
                           @RequestBody Player updatedPlayer) {

    Player player = repository.findById(id).orElse(null);

    if (player == null) {
        return null;
    }

    player.setPlayerName(updatedPlayer.getPlayerName());
    player.setPosition(updatedPlayer.getPosition());
    player.setJerseyNumber(updatedPlayer.getJerseyNumber());
    player.setAge(updatedPlayer.getAge());

    return repository.save(player);
}
@DeleteMapping("/{id}")
public void deletePlayer(@PathVariable Long id) {
    repository.deleteById(id);
}
}