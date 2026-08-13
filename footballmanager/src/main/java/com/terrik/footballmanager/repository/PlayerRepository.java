package com.terrik.footballmanager.repository;

import com.terrik.footballmanager.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}