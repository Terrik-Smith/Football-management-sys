package com.terrik.footballmanager.controller;

import com.terrik.footballmanager.dto.FootballTeamDTO;
import com.terrik.footballmanager.entity.FootballTeam;
import com.terrik.footballmanager.entity.Player;
import com.terrik.footballmanager.service.FootballTeamService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/teams")
public class FootballTeamController {

    private final FootballTeamService service;

    public FootballTeamController(FootballTeamService service) {
    this.service = service;
}

    // GET all teams
@GetMapping
public List<FootballTeamDTO> getAllTeams() {
    return service.getAllTeamDTOs();
}

@GetMapping("/{id}")
public FootballTeam getTeamById(@PathVariable Long id) {
    return service.getTeamById(id);
}

@GetMapping("/{id}/players")
public List<Player> getPlayersByTeam(@PathVariable Long id) {
    return service.getPlayersByTeamId(id);
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
