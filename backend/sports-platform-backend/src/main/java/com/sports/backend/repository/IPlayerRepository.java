package com.sports.backend.repository;

import com.sports.backend.dto.PageResponse;
import com.sports.backend.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IPlayerRepository extends JpaRepository<Player,Long>,
        JpaSpecificationExecutor<Player>
{

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

    Page<Player> findAll(
            Specification<Player> specification,
            Pageable pageable
    );

}
