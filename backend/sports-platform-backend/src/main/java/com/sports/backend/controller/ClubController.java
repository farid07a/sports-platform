package com.sports.backend.controller;


import com.sports.backend.model.Club;
import com.sports.backend.service.ClubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
