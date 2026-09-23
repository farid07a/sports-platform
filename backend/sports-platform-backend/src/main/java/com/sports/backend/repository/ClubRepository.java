package com.sports.backend.repository;

import com.sports.backend.model.Club;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ClubRepository {

    
     private final List<Club> clubs = new ArrayList<Club>(List.of(
            new Club(1L, "Biskra FC", "Biskra", "Football"),
            new Club(2L, "Biskra Kickboxing", "Biskra", "Kickboxing")
    )
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


    public List<Club> findBySport(String sport) {

        return clubs.stream()
            .filter(club -> club.sport().equalsIgnoreCase(sport))
            .collect(Collectors.toList());
    }

    public List<Club> findByCity(String city){
        return clubs.stream()
                .filter(club -> club.city().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public Club save(Club club){
        Long newId = clubs.stream()
                .mapToLong(Club::id)
                .max()
                .orElse(0) + 1;

        Club newClub = new Club(
                newId,
                club.name(),
                club.city(),
                club.sport()
        );

        clubs.add(newClub);

        return newClub;

    }

    public Club update(Long id,Club club){

        for (Club clubItem : clubs) {
            if (Objects.equals(clubItem.id(), id)) {
                return new Club(id, club.name(), club.city(), club.sport());
            }

        }
        return null;
    }
    public boolean delete(Long id){
        return clubs.removeIf(club -> club.id().equals(id));
    }

}
