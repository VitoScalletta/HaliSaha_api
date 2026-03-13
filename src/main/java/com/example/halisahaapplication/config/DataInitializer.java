package com.example.halisahaapplication.config;

import com.example.halisahaapplication.entity.Match;
import com.example.halisahaapplication.entity.Player;
import com.example.halisahaapplication.repository.MatchRepository;
import com.example.halisahaapplication.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private PlayerRepository playerRepository;
    private MatchRepository matchRepository;

    public DataInitializer(PlayerRepository playerRepository,MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Veritabanında hiç oyuncu yoksa bu işlemi yap (Her projeyi başlattığında tekrar tekrar eklemesin diye)
        if (playerRepository.count() == 0) {

            System.out.println("⏳ Veritabanı boş, test verileri yükleniyor...");

            // for döngüsü ile otomatik 30 tane oyuncu yaratıyoruz
            for (int i = 1; i <= 30; i++) {
                Player player = new Player();
                player.setIsim("Test Oyuncusu " + i);

                // Oyuncuların mevkilerini rastgele dağıtalım
                if (i % 3 == 0) player.setMevki("Kaleci");
                else if (i % 2 == 0) player.setMevki("Defans");
                else player.setMevki("Forvet");

                player.setYas(20 + (i % 10)); // 20 ile 30 arası rastgele yaşlar

                playerRepository.save(player);
            }

            System.out.println("✅ 30 adet test oyuncusu başarıyla veritabanına eklendi!");
        }
        if (matchRepository.count() == 0) {
            // 1 gün sonrası, saat 20:00 (Kurallara uygun!)
            LocalDateTime yarinAksam = LocalDateTime.now().plusDays(1).withHour(20).withMinute(0);
            // 2 gün sonrası, saat 21:00 (Kurallara uygun!)
            LocalDateTime ikiGunSonra = LocalDateTime.now().plusDays(2).withHour(21).withMinute(0);

            Match match1 = new Match(yarinAksam, "A Sahası", 14); // 14 kişi kontenjan
            Match match2 = new Match(ikiGunSonra, "B Sahası", 14);

            matchRepository.save(match1);
            matchRepository.save(match2);

            System.out.println("✅ Test maçları başarıyla veritabanına eklendi!");
        }
    }
}
