package com.example.halisahaapplication.dto;

import com.example.halisahaapplication.entity.Player;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class MatchDto {
    private int kontenjan;
    private String sahaAdi;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime tarih;

    private List<PlayerDto> players;
    public List<PlayerDto> getPlayers(){
        return players;
    }
    public void setPlayers(List<PlayerDto> players){
        this.players=players;
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
