package com.terrik.footballmanager.controller;

import com.terrik.footballmanager.entity.FootballTeam;
import com.terrik.footballmanager.service.FootballTeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class FootballTeamController {

    private final FootballTeamService service;

    public FootballTeamController(FootballTeamService service) {
    this.service = service;
}

    // GET all teams
@GetMapping
public List<FootballTeam> getAllTeams() {
    return service.getAllTeams();
}

@GetMapping("/{id}")
public FootballTeam getTeamById(@PathVariable Long id) {
    return service.getTeamById(id);
}

@PostMapping
public FootballTeam createTeam(@RequestBody FootballTeam team) {
    return service.saveTeam(team);
    }
@DeleteMapping("/{id}")
public void deleteTeam(@PathVariable Long id) {
    service.deleteTeam(id);
}

    @PutMapping("/{id}")
public FootballTeam updateTeam(@PathVariable Long id,
                               @RequestBody FootballTeam updatedTeam) {

    FootballTeam team = service.getTeamById(id);

    if (team == null) {
        return null;
    }

    team.setTeamName(updatedTeam.getTeamName());
    team.setCity(updatedTeam.getCity());
    team.setCoach(updatedTeam.getCoach());

    return service.saveTeam(team);
}
}
