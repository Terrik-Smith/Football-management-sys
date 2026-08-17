package com.terrik.footballmanager.service;

import com.terrik.footballmanager.dto.FootballTeamDTO;
import com.terrik.footballmanager.entity.FootballTeam;
import com.terrik.footballmanager.entity.Player;
import com.terrik.footballmanager.repository.FootballTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballTeamService {

    private final FootballTeamRepository repository;

    public FootballTeamService(FootballTeamRepository repository) {
        this.repository = repository;
    }

    public List<FootballTeam> getAllTeams() {
        return repository.findAll();
    }

    public FootballTeam getTeamById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public FootballTeam saveTeam(FootballTeam team) {
        return repository.save(team);
    }
    public List<FootballTeamDTO> getAllTeamDTOs() {

    return repository.findAll()
            .stream()
            .map(team -> new FootballTeamDTO(
                    team.getId(),
                    team.getTeamName(),
                    team.getCity(),
                    team.getCoach()
            ))
            .toList();
}
    public FootballTeam updateTeam(Long id, FootballTeam updatedTeam) {
        FootballTeam team = repository.findById(id).orElse(null);

        if (team == null) {
            return null;
        }

        team.setTeamName(updatedTeam.getTeamName());
        team.setCity(updatedTeam.getCity());
        team.setCoach(updatedTeam.getCoach());

        return repository.save(team);
    }
    
    public List<Player> getPlayersByTeamId(Long id) {

    FootballTeam team = repository.findById(id).orElse(null);

    if (team == null) {
        return List.of();
    }

    return team.getPlayers();
}
    public void deleteTeam(Long id) {
        repository.deleteById(id);
    }
}