package com.example.halisahaapplication.mapper;

import com.example.halisahaapplication.dto.MatchDto;
import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.entity.Match;
import com.example.halisahaapplication.entity.Player;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    private ModelMapper modelMapper;

    public MatchMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MatchDto mapToDto(Match match) {
        return modelMapper.map(match, MatchDto.class);
    }

    public Match mapFromDto(MatchDto matchDto) {
        return modelMapper.map(matchDto, Match.class);
    }
}
