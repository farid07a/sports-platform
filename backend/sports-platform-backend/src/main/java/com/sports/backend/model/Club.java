package com.sports.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clubs")
public class Club{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@NotBlank(message = "Name is required") use in ClubRequest

    String name;
    //@NotBlank
    String city;
    //@NotBlank
    String sport;

    @OneToMany(mappedBy = "club")// العلاقة موجودة أصلاً ومُدارة من الخاصية club الموجودة داخل Player.
    private List<Player> players  = new ArrayList<>();
    /*فـ "club" ليس اسم العمود في قاعدة البيانات.
إنه اسم المتغير في Player: */

    public Club() {
    }

    public Club(Long id, String name, String city, String sport) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.sport = sport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public List<Player> getPlayers() {
        return players;

    }
}


