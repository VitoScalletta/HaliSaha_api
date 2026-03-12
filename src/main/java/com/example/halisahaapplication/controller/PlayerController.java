package com.example.halisahaapplication.controller;

import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.service.PlayerService;
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
    public ResponseEntity<List<PlayerDto>> getAllPlayers(){
        List<PlayerDto> players = playerService.getAllPLayers();
        return ResponseEntity.ok(players);
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

}
