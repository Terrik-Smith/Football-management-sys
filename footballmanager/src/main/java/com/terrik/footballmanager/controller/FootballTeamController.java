package com.terrik.footballmanager.controller;

import com.terrik.footballmanager.entity.FootballTeam;
import com.terrik.footballmanager.repository.FootballTeamRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class FootballTeamController {

    private final FootballTeamRepository repository;

    public FootballTeamController(FootballTeamRepository repository) {
        this.repository = repository;
    }

    // GET all teams
@GetMapping
public List<FootballTeam> getAllTeams() {
    return repository.findAll();
}

@GetMapping("/{id}")
public FootballTeam getTeamById(@PathVariable Long id) {
    return repository.findById(id).orElse(null);
}

@PostMapping
public FootballTeam createTeam(@RequestBody FootballTeam team) {
    return repository.save(team);
    }
@DeleteMapping("/{id}")
public void deleteTeam(@PathVariable Long id) {
    repository.deleteById(id);
}

    @PutMapping("/{id}")
public FootballTeam updateTeam(@PathVariable Long id,
                               @RequestBody FootballTeam updatedTeam) {

    FootballTeam team = repository.findById(id).orElse(null);

    if (team == null) {
        return null;
    }

    team.setTeamName(updatedTeam.getTeamName());
    team.setCity(updatedTeam.getCity());
    team.setCoach(updatedTeam.getCoach());

    return repository.save(team);
}
}
