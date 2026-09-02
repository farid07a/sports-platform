package com.sports.backend.repository;

import com.sports.backend.model.Club;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubRepository {

    public List<Club> findAll(){

        return List.of(
                new Club(1L,"Biskra FC","Biskra","Football"),
                new Club(1L,"Biskra KickBoxing","Biskra","KickBoxing")
        );
    }
}
