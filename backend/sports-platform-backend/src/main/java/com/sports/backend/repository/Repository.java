package com.sports.backend.repository;

import com.sports.backend.model.Club;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class Repository {

    
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
                .filter(club -> club.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public List<Club> findBySport(String sport) {

        return clubs.stream()
            .filter(club -> club.getSport().equalsIgnoreCase(sport))
            .collect(Collectors.toList());
    }

    public List<Club> findByCity(String city){
        return clubs.stream()
                .filter(club -> club.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public Club save(Club club){
        Long newId = clubs.stream()
                .mapToLong(Club::getId)
                .max()
                .orElse(0) + 1;

        Club newClub = new Club(
                newId,
                club.getName(),
                club.getCity(),
                club.getSport()
        );

        clubs.add(newClub);

        return newClub;

    }

    public Club update(Long id, Club club){

        for (Club clubItem : clubs) {
            if (Objects.equals(clubItem.getId(), id)) {

                return new Club(id, club.getName(), club.getName(), club.getSport());
            }

        }
        return null;
    }
    public boolean delete(Long id){
        return clubs.removeIf(club -> club.getId().equals(id));
    }

}
