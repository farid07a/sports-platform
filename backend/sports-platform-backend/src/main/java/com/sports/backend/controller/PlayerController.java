package com.sports.backend.controller;


import com.sports.backend.dto.PageResponse;
import com.sports.backend.dto.PlayerRequest;
import com.sports.backend.dto.PlayerResponse;
import com.sports.backend.exception.ResourceNotFoundException;
import com.sports.backend.model.Player;
import com.sports.backend.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;


    @PostMapping
    public ResponseEntity<PlayerResponse> addPlayer(@Valid @RequestBody PlayerRequest player){

         PlayerResponse response = playerService.createPlayer(player);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping
    public List<PlayerResponse> getAllPlayers(){

        return playerService.getAllPlayers();
    }

    @GetMapping("/page")
    public PageResponse<PlayerResponse> getAllPlayers(
            Pageable pageable) {

        return playerService.getAllPlayers(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getPlayerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                playerService.getPlayerById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponse> updatePlayer(
            @PathVariable Long id,
            @Valid @RequestBody PlayerRequest request) {

        PlayerResponse response =
                playerService.updatePlayer(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/club/{clubId}")
    public List<PlayerResponse> getPlayersByClub(
            @PathVariable Long clubId) {
        return playerService.findByClubId(clubId);
    }

    //@GetMapping("/club/{clubId}")
    //    public List<PlayerResponse> getPlayersByClub(
    //            @PathVariable Long clubId) {
    //        return playerService.findByClubId(clubId);
    //    }

    @GetMapping("/club/page/{clubId}")
    public PageResponse<PlayerResponse> getPlayersByClub(
            @PathVariable Long clubId, Pageable pageable) {
        return playerService.findByClubId(clubId,pageable);
    }


    @GetMapping("/search")
    public PageResponse<PlayerResponse> searchPlayer(@RequestParam String name, Pageable pageable) {
        return playerService.findByNameContainingIgnoreCase(name, pageable);
    }
}
