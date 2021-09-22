package com.game.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private Race race;
    public Race getRace() {
        return race;
    }
    public void setRace(Race race) {
        this.race = race;
    }

    @Column(name = "profession")
    @Enumerated(EnumType.STRING)
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
    private boolean banned = false;
    public boolean isBanned() {
        return banned;
    }
    public void setBanned(boolean banned) {
        this.banned = banned;
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
    public Integer calculateLevel() {
        return (int) (((Math.sqrt(2500 + 200 * this.experience)) - 50) / 100);
    }

    @Column(name = "untilNextLevel")
    private Long untilNextLevel;
    public Long getUntilNextLevel() {
        return untilNextLevel;
    }
    public void setUntilNextLevel(Long untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }
    public Long calculateNextLevelExp() {
        return (50 * (long) (this.level + 1) * (this.level + 2)) - this.experience;
    }

    public Player() {
    }

    public Player(String name, String title,
                  Race race, Profession profession,
                  Date birthday, boolean banned,
                  Long experience, Integer level,
                  Long untilNextLevel) {

        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.birthday = birthday;
        this.banned = banned;
        this.experience = experience;

        if (level == null)
            this.level = calculateLevel();
        else this.level = level;

        if (untilNextLevel == null)
            this.untilNextLevel = calculateNextLevelExp();
        else this.untilNextLevel = untilNextLevel;
    }
}
