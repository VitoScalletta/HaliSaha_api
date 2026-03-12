package com.example.halisahaapplication.controller;

import com.example.halisahaapplication.dto.MatchDto;
import com.example.halisahaapplication.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<MatchDto> createMatch(MatchDto matchDto){
        MatchDto savedMatch = matchService.createMatch(matchDto);

        return new ResponseEntity<>(savedMatch, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable Long id){
        MatchDto matchDto = matchService.getMatchById(id);
        return ResponseEntity.ok(matchDto);
    }

    @GetMapping
    public ResponseEntity<List<MatchDto>> getAllMatches(){
        List<MatchDto> matches = matchService.getAllMatchs();
        return ResponseEntity.ok(matches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDto> updateMatch(@PathVariable Long id,@RequestBody MatchDto matchDto){
        MatchDto updatedMatch = matchService.updateMatch(id, matchDto);
        return ResponseEntity.ok(updatedMatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable Long id){
        matchService.deleteMatch(id);
        return ResponseEntity.ok("Maç başarıyla silindi");
    }
}
