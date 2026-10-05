package com.sports.backend.controller;


import com.sports.backend.dto.ClubRequest;
import com.sports.backend.dto.ClubResponse;
import com.sports.backend.dto.PageResponse;
import com.sports.backend.model.Club;
import com.sports.backend.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            @RequestParam (required = false)  String city,
            @RequestParam (required = false) String sport) {

        return clubService.searchClubs(city, sport);
    }


    // GET http://localhost:8080/api/clubs/page?page=0&size=5&sort=city,desc
    /*
        * Pageable
     ├── page = 0
     ├── size = 5
     └── sort = name ASC
     * after that clubRepository.findAll(pageable) with Spring Data JPA/Hibernate make query like
     * SELECT *
        FROM club
        ORDER BY name ASC
        LIMIT 5
        OFFSET 0;
    * */
    @GetMapping("/page")
    public PageResponse<ClubResponse> getAllClubsByPage(
            Pageable pageable) {
    //public Page<ClubResponse> getAllClubsByPage(


        return clubService.getAllClubs(pageable);
    }


    // GET /api/clubs/city/Biskra?page=0&size=2
    // GET /api/clubs/city/Biskra?page=0&size=2&sort=name,asc
    // content json as ClubResponse
    //public Page<ClubResponse> findByCity
    @GetMapping("/page/city/{city}")
    public PageResponse<ClubResponse> findByCity(
            @PathVariable String city,
            Pageable pageable) {

        return clubService.findByCity(city, pageable);
    }

    @GetMapping("/page/sport/{sport}")
    public PageResponse<ClubResponse> findBySport(
    //public Page<ClubResponse> findBySport(
            @PathVariable String sport,
            Pageable pageable) {

        return clubService.findBySport(sport, pageable);
    }

    @GetMapping("/page/search")
    public PageResponse<ClubResponse> searchClubs(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String sport,
            Pageable pageable) {

        return clubService.searchClubs(
                city,
                sport,
                pageable);
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
