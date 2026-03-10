package com.example.halisahaapplication.dto;

public class PlayerDto {
    private String isim;
    private String mevki;
    private Long yas;

    public String getIsim(){
        return isim;
    }
    public String getMevki() {
        return mevki;
    }
    public Long getYas() {
        return yas;
    }

    public void setIsim(String isim){
        this.isim = isim;
    }
    public void setMevki(String mevki) {
        this.mevki = mevki;
    }
    public void setYas(Long yas) {
        this.yas = yas ;
    }

}
