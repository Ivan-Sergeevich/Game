package com.game.entity;

import com.sun.tracing.dtrace.Attributes;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "player")
public class Player {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "title")
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "Race")
    @Enumerated (EnumType.STRING)
    private Race race;
    public Race getRace() {
        return race;
    }
    public void setRace(Race race) {
        this.race = race;
    }

    @Column(name = "profession")
    @Enumerated (EnumType.STRING)
    private Profession profession;
    public Profession getProfession() {
        return profession;
    }
    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "banned")
    private boolean isBanned;
    public boolean isBanned() {
        return isBanned;
    }
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    @Column(name = "experience")
    private Long experience;
    public Long getExperience() {
        return experience;
    }
    public void setExperience(Long experience) {
        this.experience = experience;
    }

    @Column(name = "level")
    private Integer level;
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "untilNextLevel")
    private Long untilNextLevel;
    public Long getUntilNextLevel() {
        return untilNextLevel;
    }
    public void setUntilNextLevel(Long untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }


    public Player() {
    }
}
