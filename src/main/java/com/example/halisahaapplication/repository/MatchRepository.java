package com.example.halisahaapplication.repository;

import com.example.halisahaapplication.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {

    boolean existsBySahaAdiAndTarih(String sahaAdi, LocalDateTime tarih);

}
