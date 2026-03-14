# ⚽ Halı Saha Yönetim API (Astro-Turf Management API)

Halı saha maç organizasyonlarını, dinamik oyuncu kontenjanı ve saat çakışması algoritmalarıyla profesyonelce yöneten RESTful API projesi. Bu proje, sadece temel veritabanı kayıt işlemlerini değil, gerçek dünya senaryolarına uygun gelişmiş iş kurallarını barındıracak şekilde tasarlanmıştır.

## 🚀 Kullanılan Teknolojiler

* **Dil:** Java 17
* **Framework:** Spring Boot 
* **Veritabanı:** PostgreSQL & Spring Data JPA (Hibernate)
* **Veri Transferi (DTO):** ModelMapper
* **Dokümantasyon:** Swagger (Springdoc OpenAPI)
* **Test:** JUnit 5, Mockito, MockMvc
* **Altyapı:** Docker & Docker Compose

## 🧠 Gelişmiş İş Kuralları (Business Rules)

Bu API'yi standart bir projeden ayıran temel özellik, Service katmanında çalışan özel mühendislik algoritmalarıdır:

* **⏳ Zaman Çakışması Engeli:** Bir oyuncu, aynı saatte gerçekleşen iki farklı maça kayıt olamaz. Sistem bunu otomatik algılar ve mükerrer kaydı engeller.
* **🏟️ Saha Müsaitlik Kontrolü:** Aynı saatte aynı saha için birden fazla maç oluşturulamaz. Ayrıca geçmiş bir tarihe veya tesisin kapalı olduğu çalışma saatleri dışında (02:00 - 10:00 arası) maç alınamaz.
* **👥 Dinamik Kontenjan Yönetimi:** Her maçın kendine ait bir kapasitesi vardır. Kontenjan dolduğunda veya aynı oyuncu aynı maça ikinci kez eklenmeye çalışıldığında istek `BusinessRuleException` fırlatılarak reddedilir.
* **🛡️ Akıllı Hata Yönetimi:** Tüm iş kuralı ihlalleri `@RestControllerAdvice` kullanılarak Global Exception Handler üzerinden yakalanır ve frontend/mobil geliştiriciler için standart, anlamlı JSON hata mesajlarına dönüştürülür.

## ⚙️ Kurulum ve Çalıştırma

Projeyi kendi bilgisayarınızda çalıştırmak için aşağıdaki adımları izleyebilirsiniz.

### 1. Veritabanı Ayarları (`application.properties`)
Projenin veritabanına bağlanabilmesi için `src/main/resources/application.properties` dosyasını açıp kendi PostgreSQL bilgilerinizi girmeniz gerekmektedir:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/halisaha_db
spring.datasource.username=KENDI_KULLANICI_ADINIZ
spring.datasource.password=KENDI_SIFRENIZ
