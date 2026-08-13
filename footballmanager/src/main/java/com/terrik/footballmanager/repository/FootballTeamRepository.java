package com.terrik.footballmanager.repository;

import com.terrik.footballmanager.entity.FootballTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballTeamRepository extends JpaRepository<FootballTeam, Long> {

}