package com.sports.backend.controller;


import com.sports.backend.model.Club;
import com.sports.backend.service.ClubService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService){
        this.clubService = clubService;
    }


    @GetMapping
    public List<Club> getAllClubs(){
        return this.clubService.getAllClubs();
    }

/*
    @GetMapping("/{id}")
    public Club getClubById(@PathVariable Long id) {
        return clubService.getClubById(id);
    }
*/

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {

    Club club = clubService.getClubById(id);

    if (club == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(club);
    }

    @GetMapping("/search")
    public List<Club> searchClubs(@RequestParam String sport) {

        return clubService.searchBySport(sport);
    }

    @GetMapping("/findCity")
    public List<Club> searchingClub(@RequestParam String city){
        return clubService.searchByCity(city);
    }

    @PostMapping
    public Club createClub(@RequestBody Club club) {
        return clubService.createClub(club);
    }
}
