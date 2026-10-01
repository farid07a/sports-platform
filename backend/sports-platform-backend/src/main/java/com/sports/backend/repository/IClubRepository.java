package com.sports.backend.repository;

import com.sports.backend.dto.ClubResponse;
import com.sports.backend.model.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClubRepository extends JpaRepository<Club,Long> {
    List<Club> findByCity(String city);
    List<Club> findBySport(String sport);
    List<Club> findByCityAndSport(String city,String sport);

    Page<Club> findByCity(String city, Pageable pageable);

    Page<Club> findBySport(String sport, Pageable pageable);

    Page<Club> findByCityAndSport(
            String city,
            String sport,
            Pageable pageable);


}
