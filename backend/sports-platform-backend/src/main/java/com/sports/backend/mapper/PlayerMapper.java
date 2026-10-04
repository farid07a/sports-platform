package com.sports.backend.mapper;

import com.sports.backend.dto.ClubSummaryResponse;
import com.sports.backend.dto.PlayerResponse;
import com.sports.backend.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerResponse toResponse(Player player){
        ClubSummaryResponse clubResponse = new ClubSummaryResponse(player.getClub().getId(),
                player.getClub().getName());

        return new PlayerResponse(player.getId(),player.getName(),player.getPosition(),clubResponse);

    }
}
