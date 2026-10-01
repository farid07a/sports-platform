package com.sports.backend.service;

import com.sports.backend.dto.PlayerRequest;
import com.sports.backend.dto.PlayerResponse;
import com.sports.backend.model.Club;
import com.sports.backend.model.Player;
import com.sports.backend.repository.IClubRepository;
import com.sports.backend.repository.IPlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private final IPlayerRepository  playerRepository;
    private final IClubRepository clubRepository;

    public PlayerService(IPlayerRepository playerRepository, IClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
    }

    public PlayerResponse createPlayer(PlayerRequest request){
        Optional<Club> club = Optional.ofNullable(clubRepository.findById(request.clubId()).orElse(null));
        if (club.isEmpty()) return null;
        Player savedPlayer = new Player(null,request.name(),request.position());
        Player player = playerRepository.save(savedPlayer);

        return new PlayerResponse(player.getId(),player.getName(),player.getPosition());
    }
}
