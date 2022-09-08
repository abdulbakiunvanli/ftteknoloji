
# FT Teknoloji Java Spring Practicum

Bu proje Spring Initializr(https://start.spring.io) ile oluşturuldu.

`database.sql` içerisinde bu projede çalışırken kullandığım veri tabanının tam export edilmiş scripttir.(User dahil)

Kullanıcı, Ürün ve Ürün yorum tabloları için CRUD işlemlerini de ekledim.

#### Bağlılıklar

- Spring boot (2.7.3)
- Spring Web
- MySQL Driver
- Spring Data JPA
- Validation
- Springdoc OpenAPI UI

**Not**: Lombok IDE üzerinde kurulu olmadığı durumda çalışmayabiliyor. Bu yüzden lombok kütüphanesini dahil etmedim.
## Case Çözümlerim

#### Case 1 - Bir ürüne ait yorumları listeleyen bir metot yazınız.

UrunYorumRepository.java 
```
List<UrunYorum> findAllByUrunId(Integer urunId);
```
UrunYorumService.java
```
public List<UrunYorum> findAllByUrunId(Integer urunId) {
    return urunYorumRepository.findAllByUrunId(urunId);
}
```
CaseController.java
```
@GetMapping("/1/urun/{id}/urun-yorum")
private ResponseEntity<List<UrunYorum>> urunYorumFindAllByUrunId(
        @PathVariable(value = "id", required = true) Integer urunId) {
    if (!urunService.existsById(urunId)) {
        throw new ResourceNotFoundException("Ürün bulunamadı. id = " + urunId);
    }
    List<UrunYorum> urunYorumList = urunYorumService.findAllByUrunId(urunId);
    return ResponseEntity.ok(urunYorumList);
}
```

#### Case 2 - Verilen tarih aralıklarında belirli bir ürüne yapılmış olan yorumları gösteren bir metot yazınız.

UrunYorumRepository.java 
```
List<UrunYorum> findAllByUrunIdAndYorumTarihiBetween(Integer urunId, LocalDate startDate, LocalDate endDate);
```
UrunYorumService.java
```
public List<UrunYorum> findAllByUrunIdAndYorumTarihiBetween(Integer urunId, LocalDate startDate, LocalDate endDate) {
    return urunYorumRepository.findAllByUrunIdAndYorumTarihiBetween(urunId, startDate, endDate);
}
```
CaseController.java
```
@GetMapping("/2/urun/{id}/urun-yorum/date-between")
private ResponseEntity<List<UrunYorum>> urunYorumFindAllByUrunIdAndYorumTarihiBetween(
        @PathVariable(value = "id", required = true) Integer urunId,
        @RequestParam(value = "startDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @RequestParam(value = "endDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    if (!urunService.existsById(urunId)) {
        throw new ResourceNotFoundException("Ürün bulunamadı. id = " + urunId);
    }
    List<UrunYorum> urunYorumList = urunYorumService.findAllByUrunIdAndYorumTarihiBetween(urunId, startDate,
            endDate);
    return ResponseEntity.ok(urunYorumList);
}
```

#### Case 3 - Bir kullanıcının yapmış olduğu yorumları listeleyen bir metot yazınız.

UrunYorumRepository.java 
```
List<UrunYorum> findAllByKullaniciId(Integer kullaniciId);
```
UrunYorumService.java
```
public List<UrunYorum> findAllByKullaniciId(Integer kullaniciId) {
    return urunYorumRepository.findAllByKullaniciId(kullaniciId);
}
```
CaseController.java
```
@GetMapping("/3/kullanici/{id}/urun-yorum")
private ResponseEntity<List<UrunYorum>> urunYorumFindAllByKullaniciId(
        @PathVariable(value = "id", required = true) Integer kullaniciId) {
    if (!kullaniciService.existsById(kullaniciId)) {
        throw new ResourceNotFoundException("Kullanıcı bulunamadı. id = " + kullaniciId);
    }
    List<UrunYorum> urunYorumList = urunYorumService.findAllByKullaniciId(kullaniciId);
    return ResponseEntity.ok(urunYorumList);
}
```

#### Case 4 - Bir kullanıcının belirli tarihler aralığında yapmış olduğu yorumları gösteren bir metot yazınız.

UrunYorumRepository.java 
```
List<UrunYorum> findAllByKullaniciIdAndYorumTarihiBetween(Integer kullaniciId, LocalDate startDate, LocalDate endDate);
```
UrunYorumService.java
```
public List<UrunYorum> findAllByKullaniciIdAndYorumTarihiBetween(Integer kullaniciId, LocalDate startDate, LocalDate endDate) {
    return urunYorumRepository.findAllByKullaniciIdAndYorumTarihiBetween(kullaniciId, startDate, endDate);
}
```
CaseController.java
```
@GetMapping("/4/kullanici/{id}/urun-yorum/date-between")
private ResponseEntity<List<UrunYorum>> urunYorumFindAllByKullaniciId(
        @PathVariable(value = "id", required = true) Integer kullaniciId,
        @RequestParam(value = "startDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @RequestParam(value = "endDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    if (!kullaniciService.existsById(kullaniciId)) {
        throw new ResourceNotFoundException("Kullanıcı bulunamadı. id = " + kullaniciId);
    }
    List<UrunYorum> urunYorumList = urunYorumService.findAllByKullaniciIdAndYorumTarihiBetween(kullaniciId,
            startDate, endDate);
    return ResponseEntity.ok(urunYorumList);
}
```

#### Case 5 - Son kullanma tarihi geçmiş ürünleri listeleyen bir metot yazınız.

UrunRepository.java 
```
List<Urun> findAllBySonKullanmaTarihiBefore(LocalDate Date);
```
UrunService.java
```
public List<Urun> findAllByExpired() {
    return urunRepository.findAllBySonKullanmaTarihiBefore(LocalDate.now());
}
```
CaseController.java
```
@GetMapping("/5/urun/expired")
private ResponseEntity<List<Urun>> urunFindAllByExpired() {
    List<Urun> urunList = urunService.findAllByExpired();
    return ResponseEntity.ok(urunList);
}
```

#### Case 6 - Son kullanma tarihi geçmemiş ürünleri listeleyen bir metot yazınız. (Son kullanma tarihi boş olanlar da gelmeli.)

UrunRepository.java 
```
List<Urun> findAllBySonKullanmaTarihiGreaterThanEqualOrSonKullanmaTarihiIsNull(LocalDate Date);
```
UrunService.java
```
public List<Urun> findAllByNotExpired() {
    return urunRepository.findAllBySonKullanmaTarihiGreaterThanEqualOrSonKullanmaTarihiIsNull(LocalDate.now());
}
```
CaseController.java
```
@GetMapping("/6/urun/not-expired")
private ResponseEntity<List<Urun>> urunFindAllByNotExpired() {
    List<Urun> urunList = urunService.findAllByNotExpired();
    return ResponseEntity.ok(urunList);
}
```
## API Dokümantasyon (http://localhost:8080/swagger-ui/index.html#/) 

#### Case 1 - Bir ürüne ait yorumları listeleyen bir metot yazınız.

```http
  GET /api/v1/case/1/urun/{id}/urun-yorum
```

| Parametre | Tip     | Açıklama                | Örnek |
| :-------- | :------- | :------------------------- | :---- |
| `id`      | `integer`| **Gerekli**. Ürün Id       |  1 |

#### Case 2 - Verilen tarih aralıklarında belirli bir ürüne yapılmış olan yorumları gösteren bir metot yazınız.

```http
  GET /api/v1/case/2/urun/{id}/urun-yorum/date-between?startDate={startDate}&endDate={endDate}
```

| Parametre | Tip     | Açıklama                | Örnek |
| :-------- | :------- | :------------------------- | :---- |
| `id`      | `integer`| **Gerekli**. Ürün Id       |  1 |
| `startDate` | `string($date)`| **Gerekli**. Başlangıç Tarihi (yyyy-MM-dd)  |  2022-01-01 |
| `endDate` | `string($date)`| **Gerekli**. Bitiş Tarihi (yyyy-MM-dd)  |  2022-12-01 |

#### Case 3 - Bir kullanıcının yapmış olduğu yorumları listeleyen bir metot yazınız.

```http
  GET /api/v1/case/3/kullanici/{id}/urun-yorum
```

| Parametre | Tip     | Açıklama                | Örnek |
| :-------- | :------- | :------------------------- | :---- |
| `id`      | `integer`| **Gerekli**. Kullanıcı Id       |  1 |

#### Case 4 - Bir kullanıcının belirli tarihler aralığında yapmış olduğu yorumları gösteren bir metot yazınız.

```http
  GET /api/v1/case/4/kullanici/{id}/urun-yorum/date-between?startDate={startDate}&endDate={endDate}
```

| Parametre | Tip     | Açıklama                | Örnek |
| :-------- | :------- | :------------------------- | :---- |
| `id`      | `integer`| **Gerekli**. Kullanıcı Id       |  1 |
| `startDate` | `string($date)`| **Gerekli**. Başlangıç Tarihi (yyyy-MM-dd)  |  2022-01-01 |
| `endDate` | `string($date)`| **Gerekli**. Bitiş Tarihi (yyyy-MM-dd)  |  2022-12-01 |

#### Case 5 - Son kullanma tarihi geçmiş ürünleri listeleyen bir metot yazınız.

```http
  GET /api/v1/case/5/urun/expired
```

#### Case 6 - Son kullanma tarihi geçmemiş ürünleri listeleyen bir metot yazınız. (Son kullanma tarihi boş olanlar da gelmeli.)

```http
  GET /api/v1/case/6/urun/not-expired
```