package com.sports.backend.service;

import com.sports.backend.dto.PageResponse;
import com.sports.backend.dto.PlayerRequest;
import com.sports.backend.dto.PlayerResponse;
import com.sports.backend.exception.ClubNotFoundException;
import com.sports.backend.exception.ResourceNotFoundException;
import com.sports.backend.mapper.PlayerMapper;
import com.sports.backend.model.Club;
import com.sports.backend.model.Player;
import com.sports.backend.repository.IClubRepository;
import com.sports.backend.repository.IPlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import java.awt.print.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final IPlayerRepository  playerRepository;
    private final IClubRepository clubRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(IPlayerRepository playerRepository, IClubRepository clubRepository
                        ,PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.playerMapper = playerMapper;
    }

    public PlayerResponse createPlayer(PlayerRequest request){

        //Optional<Club> club1 = Optional.ofNullable(clubRepository.findById(request.clubId()).orElse(null));

        Optional<Club> club  = Optional.ofNullable(clubRepository.findById(request.clubId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        " \"Club not found with id: \" + request.clubId()")
                ));

        //if (club.isEmpty()) return null;

        Player savedPlayer = new Player(null,request.name(),request.position());
        savedPlayer.setClub(club.get());
        Player player = playerRepository.save(savedPlayer);

        //return new PlayerResponse(player.getId(),player.getName(),player.getPosition());
        return playerMapper.toResponse(player);
    }

    public List<PlayerResponse> getAllPlayers(){
        //return playerRepository.findAll();

//        return playerRepository.findAll()
//                .stream()
//                .map(player -> new PlayerResponse(player.getId(),
//                        player.getName(),
//                        player.getPosition()
//                        )).toList();

        return playerRepository.findAll()
                .stream()
                .map(playerMapper::toResponse)
                .toList();
    }

    /******** Pageable ***********/

    public PageResponse<PlayerResponse> getAllPlayers(Pageable pageable){
        // content - number pages 2 -
        Page<PlayerResponse> page = playerRepository
                .findAll(pageable)
                .map(playerMapper::toResponse);

        return PageResponse.from(page);

    }

    public PlayerResponse getPlayerById(Long id){
//        Optional<Player> ObjPlayer = Optional.ofNullable(playerRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException(
//                        "Player not found with id: " + id
//                )));

        Player player = playerRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Player not found with id: " + id
                        )
                );

        return playerMapper.toResponse(player);
    }

    public PlayerResponse updatePlayer(
            Long id,
            PlayerRequest request) {

        Player player = playerRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Player not found with id: " + id
                        )
                );

        Club club = clubRepository
                .findById(request.clubId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Club not found with id: " + request.clubId()
                        )
                );

        player.setName(request.name());
        player.setPosition(request.position());
        player.setClub(club);

        Player updatedPlayer = playerRepository.save(player);

        return playerMapper.toResponse(updatedPlayer);
    }

    public void deletePlayer(Long id){
        Player player = playerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        STR."Player not found with id: \{id}") );

        playerRepository.delete(player);
    }

    public List<PlayerResponse> findByClubId(Long clubId){
        return playerRepository.findByClubId(clubId).stream().map(playerMapper::toResponse).toList();

    }

}
