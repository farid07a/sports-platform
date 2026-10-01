package com.sports.backend.controller;


import com.sports.backend.dto.PlayerRequest;
import com.sports.backend.dto.PlayerResponse;
import com.sports.backend.model.Player;
import com.sports.backend.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        return playerService.;
    }
}
