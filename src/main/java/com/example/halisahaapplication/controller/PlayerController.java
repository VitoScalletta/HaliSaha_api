package com.example.halisahaapplication.controller;

import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(PlayerDto playerDto){
        PlayerDto savedPlayer = playerService.createPlayer(playerDto);

        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Long id){
        PlayerDto playerDto = playerService.getPlayerById(id);

        return ResponseEntity.ok(playerDto);
    }

    @GetMapping
    public ResponseEntity<Page<PlayerDto>> getAllPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<PlayerDto> playerDtoPage = playerService.getAllPLayers(page,size);
        return ResponseEntity.ok(playerDtoPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDto> updatePlayer(@PathVariable Long id,@RequestBody PlayerDto playerDto){
        PlayerDto updatedPlayer = playerService.updatePlayer(id, playerDto);
        return ResponseEntity.ok(updatedPlayer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.ok("Oyuncu Başarıyla silindi");
    }

    @PostMapping("/{playerId}/matches/{matchId}")
    public ResponseEntity<String> addPlayerToMatch(
            @PathVariable Long playerId,
            @PathVariable Long matchId
    ){
        playerService.addPlayerToMatch(playerId,matchId);
        return ResponseEntity.ok("Oyuncu Başarıyla kaydedildi");
    }

}
