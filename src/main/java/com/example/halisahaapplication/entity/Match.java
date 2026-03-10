package com.example.halisahaapplication.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "matchs")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime tarih;
    private String sahaAdi;
    private int kontenjan;

    public Match(){

    }

    public Match(LocalDateTime tarih,String sahaAdi,int kontenjan){
        this.tarih=tarih;
        this.kontenjan=kontenjan;
        this.sahaAdi=sahaAdi;
    }

    @ManyToMany
    @JoinTable(
            name = "match_players",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKontenjan() {
        return kontenjan;
    }

    public void setKontenjan(int kontenjan) {
        this.kontenjan = kontenjan;
    }

    public String getSahaAdi() {
        return sahaAdi;
    }

    public void setSahaAdi(String sahaAdi) {
        this.sahaAdi = sahaAdi;
    }

    public LocalDateTime getTarih() {
        return tarih;
    }

    public void setTarih(LocalDateTime tarih) {
        this.tarih = tarih;
    }
}
