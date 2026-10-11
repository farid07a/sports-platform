package com.sports.backend.specification;

import com.sports.backend.model.Player;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;


public class PlayerSpecification {
//
//    public static Specification<Player> hasName(String name) {
//
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.like(
//                        criteriaBuilder.lower(root.get("name")),
//                        "%" + name.toLowerCase() + "%"
//                );
//    }
//
//
//    public static Specification<Player> hasPosition(String position) {
//
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.like(
//                        criteriaBuilder.lower(root.get("position")),
//                        "%" + position.toLowerCase() + "%"
//                );
//    }

    private static Specification<Player> containsIgnoreCase(
            String field,
            String value) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(field)),
                        "%" + value.toLowerCase(Locale.ROOT) + "%"
                );
    }

    public static Specification<Player> hasName(String name) {
        return containsIgnoreCase("name", name);
    }

    public static Specification<Player> hasPosition(String position) {
        return containsIgnoreCase("position", position);
    }

    public static Specification<Player> hasClubId(Long clubId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("club").get("id"),
                        clubId
                );
    }

}
