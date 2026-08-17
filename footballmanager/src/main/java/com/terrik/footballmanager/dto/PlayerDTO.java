package com.terrik.footballmanager.dto;

public class PlayerDTO {

    private Long id;
    private String playerName;
    private String position;
    private int jerseyNumber;
    private int age;
    private String teamName;

    public PlayerDTO() {
    }

    public PlayerDTO(
            Long id,
            String playerName,
            String position,
            int jerseyNumber,
            int age,
            String teamName) {

        this.id = id;
        this.playerName = playerName;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
        this.teamName = teamName;
    }

    public Long getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public int getAge() {
        return age;
    }

    public String getTeamName() {
        return teamName;
    }
}