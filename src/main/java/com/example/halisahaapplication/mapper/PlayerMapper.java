package com.example.halisahaapplication.mapper;

import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.entity.Player;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    private final ModelMapper modelMapper;
    public PlayerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PlayerDto mapToDto(Player player) {
        return modelMapper.map(player, PlayerDto.class);
    }

    public Player mapFromDto(PlayerDto playerDto) {
        return modelMapper.map(playerDto, Player.class);
    }
}
