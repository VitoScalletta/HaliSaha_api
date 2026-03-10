package com.example.halisahaapplication.service;

import com.example.halisahaapplication.dto.MatchDto;
import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.entity.Player;

import java.util.List;

public interface PlayerService {

    PlayerDto createPlayer(PlayerDto playerDto);

    PlayerDto getPlayerById(Long id);
    List<PlayerDto> getAllPLayers();

    PlayerDto updatePlayer(Long id,PlayerDto playerDto);

    void deletePlayer(Long id);

    void addPlayerToMatch(Long playerId,Long matchId);
}
