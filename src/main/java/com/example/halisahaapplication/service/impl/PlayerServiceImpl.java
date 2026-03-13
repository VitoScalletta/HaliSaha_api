package com.example.halisahaapplication.service.impl;

import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.entity.Match;
import com.example.halisahaapplication.entity.Player;
import com.example.halisahaapplication.exception.BusinessRuleException;
import com.example.halisahaapplication.exception.ResourceNotFoundException;
import com.example.halisahaapplication.mapper.PlayerMapper;
import com.example.halisahaapplication.repository.MatchRepository;
import com.example.halisahaapplication.repository.PlayerRepository;
import com.example.halisahaapplication.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Player player  = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hata : oyuncu bulunamadı "));

        return playerMapper.mapToDto(player);
    }

    @Override
    public Page<PlayerDto> getAllPLayers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Player> playerPage = playerRepository.findAll(pageable);
        return playerPage.map(playerMapper::mapToDto);
    }

    @Override
    public PlayerDto updatePlayer(Long id, PlayerDto playerDto) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hata : oyuncu bulunamadı"));

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
          throw new BusinessRuleException("Böyle bir oyuncu yok");
      }

      Match match = matchRepository.findById(matchId).orElse(null);
      if(match == null){
          throw new BusinessRuleException("Hata: Maç bulunamadı");
      }

      if (match.getPlayers().size() >= match.getKontenjan()){
          throw new BusinessRuleException("Hata : Kontenjan dolu");
      }

      if (match.getPlayers().contains(player)){
          throw new BusinessRuleException(  "Hata : Bu oyuncu maça zaten ekli");
      }

      List<Match> playerMatches = player.getMatches();
      LocalDateTime matchTime = match.getTarih();
      for (Match existingMatch : playerMatches){
          if (existingMatch.getTarih().isEqual(matchTime)){
              throw new BusinessRuleException("Hata : Bu oyuncunun aynı saatte maçı var");
          }
      }

      match.getPlayers().add(player);
      matchRepository.save(match);
    }
}
