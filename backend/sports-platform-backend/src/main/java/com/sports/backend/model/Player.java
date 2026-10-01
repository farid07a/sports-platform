package com.sports.backend.model;


import jakarta.persistence.*;

//يعني أن Player كيان تديره JPA/Hibernate.
@Entity
@Table(name = "players") //يعني أن الكيان مرتبط بجدول
public class Player {
    //Owning Side هنا نسم اللاعبب

    /*
    * Player.id
        ↓
    Primary Key
        ↓
    Database generates the value
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String position;

    @ManyToOne //Many Players → One Club
    @JoinColumn(name = "club_id") // استخدم العمود club_id في جدول players لتخزين العلاقة مع Club.
    private Club club; // إذن Player هو الذي يملك العلاقة من ناحية قاعدة البيانات.

    public Player(Long id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }
    public Player() {

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
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;

    }
    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return STR."id=\{id}, name=\{name}, position=\{position}";
    }

}

