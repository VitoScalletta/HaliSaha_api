package com.example.halisahaapplication.service;

import com.example.halisahaapplication.dto.MatchDto;

import java.util.List;

public interface MatchService {

    MatchDto createMatch(MatchDto matchDto);

    MatchDto getMatchById(Long id);
    List<MatchDto> getAllMatchs();

    MatchDto updateMatch(Long id,MatchDto matchDto);

    void deleteMatch(Long id);
}
