package com.terrik.footballmanager.service;

import com.terrik.footballmanager.entity.FootballTeam;
import com.terrik.footballmanager.entity.Player;
import com.terrik.footballmanager.repository.FootballTeamRepository;
import com.terrik.footballmanager.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository repository;
    private final FootballTeamRepository teamRepository;

    public PlayerService(PlayerRepository repository,
                         FootballTeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }

    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    public Player getPlayerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Player savePlayer(Player player) {
        return repository.save(player);
    }

    public Player updatePlayer(Long id, Player updatedPlayer) {
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

    public Player assignTeam(Long playerId, Long teamId) {
        Player player = repository.findById(playerId).orElse(null);
        FootballTeam team = teamRepository.findById(teamId).orElse(null);

        if (player == null || team == null) {
            return null;
        }

        player.setTeam(team);

        return repository.save(player);
    }

    public void deletePlayer(Long id) {
        repository.deleteById(id);
    }
}