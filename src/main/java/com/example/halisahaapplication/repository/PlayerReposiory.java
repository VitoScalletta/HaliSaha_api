package com.example.halisahaapplication.repository;

import com.example.halisahaapplication.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerReposiory extends JpaRepository<Player,Long> {


}
