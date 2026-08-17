package com.terrik.footballmanager.dto;

public class FootballTeamDTO {

    private Long id;
    private String teamName;
    private String city;
    private String coach;

    public FootballTeamDTO() {
    }

    public FootballTeamDTO(Long id, String teamName, String city, String coach) {
        this.id = id;
        this.teamName = teamName;
        this.city = city;
        this.coach = coach;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
    this.teamName = teamName;
}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}