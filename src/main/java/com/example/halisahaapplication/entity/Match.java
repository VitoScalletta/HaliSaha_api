package com.example.halisahaapplication.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
