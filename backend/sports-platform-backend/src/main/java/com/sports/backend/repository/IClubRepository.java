package com.sports.backend.repository;

import com.sports.backend.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClubRepository extends JpaRepository<Club,Long> {
    List<Club> findByCity(String city);
    List<Club> findBySport(String sport);
    List<Club> findByCityAndSport(String city,String sport);
}
