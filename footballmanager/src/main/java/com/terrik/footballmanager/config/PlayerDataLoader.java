package com.terrik.footballmanager.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terrik.footballmanager.entity.FootballTeam;
import com.terrik.footballmanager.entity.Player;
import com.terrik.footballmanager.repository.FootballTeamRepository;
import com.terrik.footballmanager.repository.PlayerRepository;
import com.terrik.footballmanager.service.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class PlayerDataLoader implements CommandLineRunner {

    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final FootballTeamRepository teamRepository;

    public PlayerDataLoader(
            PlayerService playerService,
            PlayerRepository playerRepository,
            FootballTeamRepository teamRepository) {

        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream =
                new ClassPathResource("players.json").getInputStream();

        List<PlayerImport> players =
                mapper.readValue(
                        inputStream,
                        new TypeReference<List<PlayerImport>>() {}
                );

        int imported = 0;
        int skipped = 0;

        for (PlayerImport importPlayer : players) {

            FootballTeam team =
                    teamRepository.findAll()
                            .stream()
                            .filter(t ->
                                    t.getTeamName()
                                            .equals(importPlayer.getTeamName()))
                            .findFirst()
                            .orElse(null);

            if (team == null) {
                skipped++;
                continue;
            }

            // Check if this player already exists on this team
            if (playerRepository.existsByPlayerNameAndTeam(
                    importPlayer.getPlayerName(),
                    team)) {

                skipped++;
                continue;
            }

            Player player = new Player();

            player.setPlayerName(importPlayer.getPlayerName());
            player.setPosition(importPlayer.getPosition());
            player.setJerseyNumber(importPlayer.getJerseyNumber());
            player.setAge(importPlayer.getAge());
            player.setTeam(team);

            playerService.savePlayer(player);

            imported++;
        }

        System.out.println(
                "Players imported: " + imported +
                " | Players skipped: " + skipped
        );
    }
}