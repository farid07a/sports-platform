package com.sports.backend.repository;

import com.sports.backend.model.Club;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubRepository {

    
     private final List<Club> clubs = List.of(
            new Club(1L, "Biskra FC", "Biskra", "Football"),
            new Club(2L, "Biskra Kickboxing", "Biskra", "Kickboxing")
    );

    public List<Club> findAll(){
        return clubs;
    }

    public Club findById(Long id) {

        return clubs.stream()
                .filter(club -> club.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    
}
