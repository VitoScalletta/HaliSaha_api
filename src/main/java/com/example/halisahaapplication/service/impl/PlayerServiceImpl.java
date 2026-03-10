package com.example.halisahaapplication.service.impl;

import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.entity.Player;
import com.example.halisahaapplication.mapper.PlayerMapper;
import com.example.halisahaapplication.repository.PlayerRepository;
import com.example.halisahaapplication.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player = playerMapper.mapFromDto(playerDto);
        Player savedPlayer = playerRepository.save(player);
        return playerMapper.mapToDto(savedPlayer);
    }

    @Override
    public PlayerDto getPlayerById(Long id) {
        Player player  = playerRepository.findById(id).orElse(null);
        if (player == null) return null;

        return playerMapper.mapToDto(player);
    }

    @Override
    public List<PlayerDto> getAllPLayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(player -> playerMapper.mapToDto(player))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDto updatePlayer(Long id, PlayerDto playerDto) {
        Player existingPlayer = playerRepository.findById(id).orElse(null);
        if (existingPlayer == null) return null;

        existingPlayer.setIsim(playerDto.getIsim());
        existingPlayer.setMevki(playerDto.getMevki());
        existingPlayer.setYas(playerDto.getYas());

        Player updatedPlayer = playerRepository.save(existingPlayer);

        return playerMapper.mapToDto(updatedPlayer);
    }

    @Override
    public void deletePlayer(Long id) {
        if (playerRepository.existsById(id)){
            playerRepository.deleteById(id);
        }
    }
    @Override
    public void addPlayerToMatch(Long playerId,Long matchId){

    }
}
