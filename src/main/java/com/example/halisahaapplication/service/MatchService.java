package com.example.halisahaapplication.service;

import com.example.halisahaapplication.dto.MatchDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MatchService {

    MatchDto createMatch(MatchDto matchDto);

    MatchDto getMatchById(Long id);
    Page<MatchDto> getAllMatchs(int page, int size);

    MatchDto updateMatch(Long id,MatchDto matchDto);

    void deleteMatch(Long id);
}
