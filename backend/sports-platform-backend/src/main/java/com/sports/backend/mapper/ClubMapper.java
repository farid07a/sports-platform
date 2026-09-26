package com.sports.backend.mapper;

import com.sports.backend.dto.ClubRequest;
import com.sports.backend.dto.ClubResponse;
import com.sports.backend.model.Club;
import org.springframework.stereotype.Component;


@Component
public class ClubMapper {
    //public static Club toEntity(ClubRequest request) {
    public Club toEntity(ClubRequest request) {
        Club club = new Club();
        club.setName(request.name());
        club.setCity(request.city());
        club.setSport(request.sport());
        return club;
    }

    //public static ClubResponse toResponse(Club club){
    public ClubResponse toResponse(Club club){
        return new ClubResponse(
                club.getId(),
                club.getName(),
                club.getCity(),
                club.getSport()
        );
    }

    public void updateEntity(Club club, ClubRequest request) {

        club.setName(request.name());
        club.setCity(request.city());
        club.setSport(request.sport());
    }
}
