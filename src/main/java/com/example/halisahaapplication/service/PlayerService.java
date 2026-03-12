package com.example.halisahaapplication.service;

import com.example.halisahaapplication.dto.MatchDto;
import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.entity.Player;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlayerService {

    PlayerDto createPlayer(PlayerDto playerDto);

    PlayerDto getPlayerById(Long id);
    Page<PlayerDto> getAllPLayers(int page, int size);

    PlayerDto updatePlayer(Long id,PlayerDto playerDto);

    void deletePlayer(Long id);

    void addPlayerToMatch(Long playerId,Long matchId);
}
