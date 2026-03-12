package com.example.halisahaapplication.service.impl;

import com.example.halisahaapplication.dto.MatchDto;
import com.example.halisahaapplication.entity.Match;
import com.example.halisahaapplication.mapper.MatchMapper;
import com.example.halisahaapplication.repository.MatchRepository;
import com.example.halisahaapplication.service.MatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    public MatchServiceImpl(MatchRepository matchRepository,MatchMapper matchMapper){
        this.matchMapper=matchMapper;
        this.matchRepository=matchRepository;
    }
    @Override
    public MatchDto createMatch(MatchDto matchDto){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime wantedDate = matchDto.getTarih();
        int wantedHour = wantedDate.getHour();

        if (wantedDate.isBefore(now)){
            throw new RuntimeException("Hata :Geçmişe maç alamazsınız");
        }
        if (wantedHour >= 2 && wantedHour < 10) {
            throw new RuntimeException("Hata:Çalışma Saatleri İçerisinde Bir Saat seçiniz");
        }
        boolean ismatchAlreadyExists = matchRepository.existsBySahaAdiAndTarih(matchDto.getSahaAdi(),matchDto.getTarih());
        if (ismatchAlreadyExists) {
            throw new RuntimeException("Hata: Bu tarihte bir maç var zaten");
        }

        Match match = matchMapper.mapFromDto(matchDto);
        Match savedMatch = matchRepository.save(match);
        return matchMapper.mapToDto(savedMatch);
    }

    @Override
    public MatchDto getMatchById(Long id) {
        Match match = matchRepository.findById(id).orElse(null);
        if(match == null)return null;

        return matchMapper.mapToDto(match);
    }

    @Override
    public Page<MatchDto> getAllMatchs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Match> matchPage = matchRepository.findAll(pageable);
        return matchPage.map(match -> matchMapper.mapToDto(match));
    }

    @Override
    public MatchDto updateMatch(Long id, MatchDto matchDto) {
        Match existingMatch = matchRepository.findById(id).orElse(null);
        if (existingMatch == null) return null;

        existingMatch.setKontenjan(matchDto.getKontenjan());
        existingMatch.setTarih(matchDto.getTarih());
        existingMatch.setSahaAdi(matchDto.getSahaAdi());

        Match updateMatch = matchRepository.save(existingMatch);
        return matchMapper.mapToDto(updateMatch);
    }

    @Override
    public void deleteMatch(Long id) {
        if (matchRepository.existsById(id)){
            matchRepository.deleteById(id);
        }
    }
}
