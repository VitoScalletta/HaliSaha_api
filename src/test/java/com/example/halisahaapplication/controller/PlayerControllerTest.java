package com.example.halisahaapplication.controller;

import com.example.halisahaapplication.dto.PlayerDto;
import com.example.halisahaapplication.exception.ResourceNotFoundException;
import com.example.halisahaapplication.service.PlayerService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Oyuncu getirme testi başarılı")
    public void testThatGetPlayerByIdReturnsCorrectSql() throws Exception{
        PlayerDto testPlayer = new PlayerDto();
        testPlayer.setIsim("Emre");
        testPlayer.setMevki("Stoper");
        testPlayer.setYas(19);

        Mockito.when(playerService.getPlayerById(1L)).thenReturn(testPlayer);

        mockMvc.perform(get("/api/players/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isim").value("Emre"))
                .andExpect(jsonPath("$.mevki").value("Stoper"))
                .andExpect(jsonPath("$.yas").value(19));
    }
    @Test
    @DisplayName("Oyuncu kayıt testi başarılı")
    public void testThatCreatePlayerReturnsCorrectSql() throws Exception{
        PlayerDto testPlayer = new PlayerDto();
        testPlayer.setIsim("Emre");
        testPlayer.setMevki("Stoper");
        testPlayer.setYas(19);

        PlayerDto savedPlayer = new PlayerDto();
        testPlayer.setIsim("Emre");
        testPlayer.setMevki("Stoper");
        testPlayer.setYas(19);

        Mockito.when(playerService.createPlayer(any(PlayerDto.class))).thenReturn(testPlayer);

        mockMvc.perform(post("/api/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPlayer))).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isim").value("Emre"))
                .andExpect(jsonPath("$.mevki").value("Stoper"))
                .andExpect(jsonPath("$.yas").value(19));
    }

    @Test
    @DisplayName("404 Testi : Olmayan oyuncu arandığında hata dönmeli")
    public void testThat404ErrorReturnsSuccessfuly() throws Exception{
        Mockito.when(playerService.getPlayerById(99L))
                .thenThrow(new ResourceNotFoundException("Oyuncu yok"));

        mockMvc.perform(get("/api/players/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Oyuncu yok"));
    }
}
