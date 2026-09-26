package com.sports.backend.controller;


import com.sports.backend.dto.ClubRequest;
import com.sports.backend.dto.ClubResponse;
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
    public List<ClubResponse> getAllClubs(){

        return this.clubService.getAllClubs();
    }

    @GetMapping("/{id}")
    public ClubResponse getClubById(@PathVariable Long id){
        return clubService.getClubById(id);
    }

    @GetMapping("/city/{city}")
    public List<ClubResponse> findByCity(@PathVariable String city){
        return clubService.findByCity(city);
    }

    @GetMapping("/sport/{sport}")
    public List<ClubResponse> findBySport(@PathVariable String sport){

        return clubService.findBySport(sport);
    }

    @PutMapping("/{id}")
    public ClubResponse updateClub(
            @PathVariable Long id,
             @Valid @RequestBody ClubRequest request){
        return clubService.updateClub(id,request);
    }


    @PostMapping
    public ResponseEntity<ClubResponse> createClub(@Valid @RequestBody ClubRequest request){

        ClubResponse response = clubService.createClub(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClube(@PathVariable Long id){
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/search")
    public List<ClubResponse> searchClubs(
            @RequestParam String city,
            @RequestParam String sport) {

        return clubService.searchClubs(city, sport);
    }



/*
    @GetMapping("/{id}")
    public Club getClubById(@PathVariable Long id) {
        return clubService.getClubById(id);
    }
*/


    /*


    @GetMapping("/{id}")
    public ResponseEntity<ClubResponse> getClubById(@PathVariable Long id) {

    ClubResponse club = clubService.getClubById(id);

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
    public ResponseEntity<ClubResponse> createClub(@Valid @RequestBody ClubRequest request) {

        ClubResponse clubResponse = clubService.createClub(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clubResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubResponse> updateClub(@PathVariable Long id, @Valid @RequestBody ClubRequest request){
        ClubResponse clubResponse=clubService.updateClub(id,request);
        if (clubResponse==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clubResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id){

        boolean deleted = clubService.deleteClub(id);
        if (!deleted)
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build(); //204 No Content
    }

     */
}
