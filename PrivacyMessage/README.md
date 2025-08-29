# PrivacyMessage

Signal tabanlı güvenli mesajlaşma uygulaması - En üst düzey gizlilik ve güvenlik için tasarlanmış modern mesajlaşma platformu.

## 🛡️ Temel Değerler

- **Şifreleme:** Tüm iletişim uçtan uca (E2EE) şifrelenir
- **Açıklık:** Signal'in açık kaynak protokolü kullanılır
- **Gizlilik:** Minimum veri toplama ilkesi
- **Kullanıcı Deneyimi:** Güvenlik arka planda sorunsuz çalışır

## 🏗️ Teknik Mimari

### İstemci (Client)
- **Android:** Kotlin/Jetpack Compose
- **iOS:** Swift/SwiftUI  
- **Web:** React/TypeScript
- **Ana Kütüphaneler:** `libsignal-client`, `WebRTC`
- **Veri Depolama:** SQLite (Room/CoreData)

### Sunucu (Server)
- **Teknoloji:** Signal Server (Java) açık kaynak sürümü
- **Barındırma:** Yüksek gizlilik standartlarına sahip bulut sağlayıcısı
- **Minimum Veri Toplama:** Sadece gerekli kimlik doğrulama verileri

## 🚀 Özellikler

### Temel Özellikler (MVP)
- ✅ Telefon numarası ile kayıt/kimlik doğrulama
- ✅ Kişi bulma (rehberden, sunucuya bilgi göndermeden)
- ✅ Birebir metin mesajlaşma (E2EE)
- ✅ Medya paylaşımı (Resim, Video - E2EE)
- ✅ Grup sohbetleri (Signal grup protokolü ile E2EE)
- ✅ Okundu bilgisi, Yazıyor... indikatörü

### İleri Düzey Özellikler (Post-MVP)
- 🔄 Sesli/Görüntülü Arama (WebRTC üzerinden E2EE)
- 🔄 Kaybolan Mesajlar (ayrı ayrı zamanlayıcı)
- 🔄 Dosya Paylaşımı (PDF, DOCX vb.)
- 🔄 Yedekleme ve Aktarma (şifrelenmiş yedekler)
- 🔄 Gizli Sohbetler (bildirim göstermeyen, parola korumalı)

## 🎨 Tasarım

- **Ana Renk:** Derin mavi (`#2563EB`) - Güvenliği temsil eder
- **Aksan Renk:** Turuncu (`#F97316`) - Önemli aksiyonlar için
- **Font:** Inter/SF Pro Display
- **Tema:** Açık/Koyu mod desteği

## 🔐 Güvenlik

- **Uçtan Uca Şifreleme:** Signal Protokolü (Double Ratchet, X3DH, PQXDH)
- **Güvenlik Doğrulaması:** Safety Number QR kod okuma
- **Gelişmiş Güvenlik Kilidi:** Periyodik PIN kontrolü
- **Sunucu Güvenliği:** Düzenli denetimler, DDoS koruması

## 📱 Platformlar

- [ ] Android (Kotlin/Jetpack Compose)
- [ ] iOS (Swift/SwiftUI)
- [ ] Web (React/TypeScript)

## 🛠️ Geliştirme

### Gereksinimler
- Android Studio / Xcode / Node.js
- Java 17+
- Kotlin 1.8+
- Signal Server kurulumu

### Kurulum
```bash
# Android
cd android
./gradlew build

# iOS  
cd ios
pod install
xcodebuild

# Web
cd web
npm install
npm start
```

## 📄 Lisans

GNU AGPLv3 - Signal projesi ile aynı lisans

## 🤝 Katkıda Bulunma

1. Fork yapın
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Commit yapın (`git commit -m 'Add amazing feature'`)
4. Push yapın (`git push origin feature/amazing-feature`)
5. Pull Request açın

## 📞 Destek

- **Dokümantasyon:** [docs.privacy-message.org](https://docs.privacy-message.org)
- **Topluluk:** [community.privacy-message.org](https://community.privacy-message.org)
- **Güvenlik:** security@privacy-message.org