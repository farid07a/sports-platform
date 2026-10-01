package com.sports.backend.repository;

import com.sports.backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayerRepository extends JpaRepository<Player,Long> {

}
