package com.example.halisahaapplication.service.impl;

import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.entity.Match;
import com.example.halisahaapplication.entity.Player;
import com.example.halisahaapplication.mapper.PlayerMapper;
import com.example.halisahaapplication.repository.MatchRepository;
import com.example.halisahaapplication.repository.PlayerRepository;
import com.example.halisahaapplication.service.PlayerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final MatchRepository matchRepository;

    public PlayerServiceImpl(
            PlayerRepository playerRepository,
            PlayerMapper playerMapper,
            MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.matchRepository=matchRepository;
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
      Player player = playerRepository.findById(playerId).orElse(null);
      if (player == null){
          throw new RuntimeException("Böyle bir oyuncu yok");
      }

      Match match = matchRepository.findById(matchId).orElse(null);
      if(match == null){
          throw new RuntimeException("Hata: Maç bulunamadı");
      }

      if (match.getPlayers().size() >= match.getKontenjan()){
          throw new RuntimeException("Hata : Kontenjan dolu");
      }

      if (match.getPlayers().contains(player.getId())){
          throw new RuntimeException("Hata : Bu oyuncu maça zaten ekli");
      }

      List<Match> playerMatches = player.getMatches();
      LocalDateTime matchTime = match.getTarih();
      for (Match existingMatch : playerMatches){
          if (existingMatch.getTarih().isEqual(matchTime)){
              throw new RuntimeException("Hata : Bu oyuncunun aynı saatte maçı var");
          }
      }

      match.getPlayers().add(player);
      matchRepository.save(match);
    }
}
