package com.example.halisahaapplication.dto;

import java.time.LocalDateTime;

public class MatchDto {
    private int kontenjan;
    private String saha_adi;
    private LocalDateTime tarih;

    public int getKontenjan() {
        return kontenjan;
    }

    public void setKontenjan(int kontenjan) {
        this.kontenjan = kontenjan;
    }

    public String getSaha_adi() {
        return saha_adi;
    }

    public void setSaha_adi(String saha_adi) {
        this.saha_adi = saha_adi;
    }

    public LocalDateTime getTarih() {
        return tarih;
    }

    public void setTarih(LocalDateTime tarih) {
        this.tarih = tarih;
    }
}
