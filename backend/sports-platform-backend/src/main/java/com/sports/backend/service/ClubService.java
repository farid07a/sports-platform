package com.sports.backend.service;


import com.sports.backend.dto.ClubRequest;
import com.sports.backend.dto.ClubResponse;
import com.sports.backend.exception.ClubNotFoundException;
import com.sports.backend.exception.InvalidSearchException;
import com.sports.backend.mapper.ClubMapper;
import com.sports.backend.model.Club;
import com.sports.backend.repository.IClubRepository;
import com.sports.backend.repository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;



@Service
public class ClubService {
    //private final Repository repository;
    private final IClubRepository repositoryClub;
    private final ClubMapper clubMapper;

    public ClubService(IClubRepository repositoryClub,ClubMapper clubMapper) {
        this.repositoryClub = repositoryClub;
        this.clubMapper = clubMapper;
    }

    public List<ClubResponse> getAllClubs(){
//        return  repositoryClub.findAll()
//                .stream()
//                .map(club -> new ClubResponse(
//                        club.getId(),
//                        club.getName(),
//                        club.getCity(),
//                        club.getSport()
//                ))
//                .toList();
        return repositoryClub.findAll()
                .stream()
                .map(clubMapper::toResponse)
                .toList();
    }

    public ClubResponse createClub(ClubRequest request){
        //Club club =new Club(null,request.name(),request.city(),request.sport());
        Club club = clubMapper.toEntity(request);
        Club createdClub = repositoryClub.save(club);
        return clubMapper.toResponse(createdClub);
        //return new ClubResponse(createdClub.getId(),createdClub.getName(),createdClub.getCity(),createdClub.getName());

    }

    public ClubResponse getClubById(Long id){
        Club retrivedClub = repositoryClub.findById(id).orElseThrow(() ->
                new ClubNotFoundException(STR."Club not found with id: \{id}"));
        return clubMapper.toResponse(retrivedClub);
        //return new ClubResponse(retrivedClub.getId(),retrivedClub.getName(),retrivedClub.getCity(),retrivedClub.getSport());
    }

    public List<ClubResponse> findByCity(String city) {

        return repositoryClub.findByCity(city)
                .stream()
                .map(clubMapper::toResponse)
                .toList();
    }

    public List<ClubResponse> findBySport(String sport){

        return repositoryClub.findBySport(sport)
                .stream()
                .map(clubMapper::toResponse)
                .toList();
    }

    public ClubResponse updateClub(Long id,ClubRequest request){
        Club club=repositoryClub.findById(id).
                orElseThrow(()->new ClubNotFoundException("Club not found with id : "+id));

//        club.setName(request.name());
//        club.setCity(request.city());
//        club.setSport(request.sport());
        clubMapper.updateEntity(club, request);
        Club clubUpdated = repositoryClub.save(club);
        return clubMapper.toResponse(clubUpdated);

        //return new ClubResponse(clubUpdated.getId(),clubUpdated.getName(),clubUpdated.getCity(), clubUpdated.getSport());
    }

    public void deleteClub(Long id){
        Club club = repositoryClub.findById(id)
                .orElseThrow(()->new ClubNotFoundException("Club not found with id: "+id));
        repositoryClub.delete(club);
    }

    public List<ClubResponse> searchClubs(String city,
                                          String sport){

        boolean hasCity = StringUtils.hasText(city);
        boolean hadSport = StringUtils.hasText(sport);
        if (!hasCity && !hadSport)
            throw new IndexOutOfBoundsException("At least one search parameter is required");

        if (hasCity && hadSport)
        //if (city!=null && sport!=null)

            return repositoryClub.findByCityAndSport(city, sport)
                .stream()
                .map(clubMapper::toResponse)
                .toList();

        if(hasCity)
        //if(city!=null)
            return repositoryClub.findByCity(city)
                    .stream()
                    .map(clubMapper::toResponse)
                    .toList();

        return repositoryClub.findBySport(sport).
                stream()
                .map(clubMapper::toResponse)
                .toList();

    }

    public Page<ClubResponse> getAllClubs(Pageable pageable) {

        return repositoryClub
                .findAll(pageable)
                .map(clubMapper::toResponse);
    }

    /*****************************************************************/

    public Page<ClubResponse> findByCity(
            String city,
            Pageable pageable) {

        return repositoryClub
                .findByCity(city, pageable)
                .map(clubMapper::toResponse);
    }

    public Page<ClubResponse> findBySport(
            String sport,
            Pageable pageable) {

        return repositoryClub
                .findBySport(sport, pageable)
                .map(clubMapper::toResponse);
    }

    public Page<ClubResponse> searchClubs(
            String city,
            String sport,
            Pageable pageable) {

        boolean hasCity = StringUtils.hasText(city);
        boolean hasSport = StringUtils.hasText(sport);

        if (!hasCity && !hasSport) {
            throw new InvalidSearchException(
                    "At least one search parameter is required");
        }

        if (hasCity && hasSport) {

            return repositoryClub
                    .findByCityAndSport(city, sport, pageable)
                    .map(clubMapper::toResponse);
        }

        if (hasCity) {

            return repositoryClub
                    .findByCity(city, pageable)
                    .map(clubMapper::toResponse);
        }

        return repositoryClub
                .findBySport(sport, pageable)
                .map(clubMapper::toResponse);
    }

    /*
    public ClubService(Repository repository){

        this.repository = repository;
    }

    public List<ClubResponse> getAllClubs(){
        return repository.findAll()
                .stream()
                .map(club -> new ClubResponse(
                        club.getId(),
                        club.getName(),
                        club.getCity(),
                        club.getSport()
                ))
                .toList();
    }

    public ClubResponse getClubById(Long id) {
        Club club = repository.findById(id);
        if(club==null) return null;

        return new ClubResponse(club.getId(),club.getName(),club.getCity(),club.getSport());

    }

    public List<Club> searchBySport(String sport) {
        return repository.findBySport(sport);
    }

    public List<Club> searchByCity(String city){
        return repository.findByCity(city);
    }

    public ClubResponse createClub(ClubRequest request) {
        Club club=new Club(null,request.name(),request.city(), request.sport());
        Club savedClub = repository.save(club);
        return new ClubResponse(savedClub.getId(),savedClub.getName(), savedClub.getCity(), savedClub.getSport());
    }

    public ClubResponse updateClub(Long id,ClubRequest request){
        Club updatedClub = repository.update(
                id,
                new Club(id,
                        request.name(),
                        request.city(),
                        request.sport()
                )
        );
        if (updatedClub == null) {
            return null;
        }
        return new ClubResponse(
                updatedClub.getId(),
                updatedClub.getName(),
                updatedClub.getCity(),
                updatedClub.getSport()
        );


        //return clubRepository.update(id, request);
    }

    public boolean deleteClub(Long id){
        return repository.delete(id);
    }*/



}
