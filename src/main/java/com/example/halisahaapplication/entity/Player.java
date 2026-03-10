package com.example.halisahaapplication.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isim;
    private String mevki;
    private int yas;

    public Player(){

    }

    public Player(String isim,String mevki,int yas){
        this.isim = isim;
        this.mevki = mevki;
        this.yas = yas;
    }

    @ManyToMany(mappedBy = "players")
    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public String getMevki() {
        return mevki;
    }

    public void setMevki(String mevki) {
        this.mevki = mevki;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

