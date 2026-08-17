package com.terrik.footballmanager.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terrik.footballmanager.entity.FootballTeam;
import com.terrik.footballmanager.service.FootballTeamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final FootballTeamService footballTeamService;

    public DataLoader(FootballTeamService footballTeamService) {
        this.footballTeamService = footballTeamService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!footballTeamService.getAllTeams().isEmpty()) {
        System.out.println("Teams already loaded.");
        return;
    }
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream =
                new ClassPathResource("teams.json").getInputStream();

        List<FootballTeam> teams =
                mapper.readValue(inputStream, new TypeReference<List<FootballTeam>>() {});

        for (FootballTeam team : teams) {
            footballTeamService.saveTeam(team);
        }

        System.out.println("NFL teams imported successfully!");
    }
}