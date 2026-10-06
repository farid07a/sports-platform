package com.sports.backend.repository;

import com.sports.backend.dto.PageResponse;
import com.sports.backend.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPlayerRepository extends JpaRepository<Player,Long> {

    /*
    * findByClubId
     ↓
    find By Club Id
         ↓
    ابحث عن Player
    حيث club.id = القيمة*
    */

    List<Player> findByClubId(Long clubId);

    Page<Player> findByClubId(Long clubId, Pageable pageable);

    /*
    * findByNameContainingIgnoreCase
       ↓
    find By
           ↓
    Name
           ↓
    Containing
           ↓
    IgnoreCase
    * */

    Page<Player> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}
