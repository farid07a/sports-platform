package com.sports.backend.controller;


import com.sports.backend.model.Club;
import com.sports.backend.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public Club createClub(@Valid @RequestBody Club club) {

        Club createdClub = clubService.createClub(club);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClub).getBody();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club club){
        Club clubUpdate=clubService.updateClub(id,club);
        if (clubUpdate==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clubUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id){

        boolean deleted = clubService.deleteClub(id);
        if (!deleted)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build(); //204 No Content
    }
}
