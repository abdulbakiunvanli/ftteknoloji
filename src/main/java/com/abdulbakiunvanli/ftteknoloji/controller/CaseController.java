package com.abdulbakiunvanli.ftteknoloji.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abdulbakiunvanli.ftteknoloji.exception.ResourceNotFoundException;
import com.abdulbakiunvanli.ftteknoloji.model.Urun;
import com.abdulbakiunvanli.ftteknoloji.model.UrunYorum;
import com.abdulbakiunvanli.ftteknoloji.service.KullaniciService;
import com.abdulbakiunvanli.ftteknoloji.service.UrunService;
import com.abdulbakiunvanli.ftteknoloji.service.UrunYorumService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/case")
@Tag(name = "Cases API")
public class CaseController {

	@Autowired
	private UrunYorumService urunYorumService;
	@Autowired
	private UrunService urunService;
	@Autowired
	private KullaniciService kullaniciService;

	@Operation(summary = "1) Bir ürüne ait yorumları listeleyen bir metot yazınız.")
	@GetMapping("/1/urun/{id}/urun-yorum")
	private ResponseEntity<List<UrunYorum>> urunYorumFindAllByUrunId(
			@PathVariable(value = "id", required = true) Integer urunId) {
		if (!urunService.existsById(urunId)) {
			throw new ResourceNotFoundException("Ürün bulunamadı. id = " + urunId);
		}
		List<UrunYorum> urunYorumList = urunYorumService.findAllByUrunId(urunId);
		return ResponseEntity.ok(urunYorumList);
	}

	@Operation(summary = "2) Verilen tarih aralıklarında belirli bir ürüne yapılmış olan yorumları gösteren bir metot yazınız.", description = "Örnek tarih : 2022-09-05 (yyyy-MM-dd)")
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

	@Operation(summary = "3) Bir kullanıcının yapmış olduğu yorumları listeleyen bir metot yazınız.")
	@GetMapping("/3/kullanici/{id}/urun-yorum")
	private ResponseEntity<List<UrunYorum>> urunYorumFindAllByKullaniciId(
			@PathVariable(value = "id", required = true) Integer kullaniciId) {
		if (!kullaniciService.existsById(kullaniciId)) {
			throw new ResourceNotFoundException("Kullanıcı bulunamadı. id = " + kullaniciId);
		}
		List<UrunYorum> urunYorumList = urunYorumService.findAllByKullaniciId(kullaniciId);
		return ResponseEntity.ok(urunYorumList);
	}

	@Operation(summary = "4) Bir kullanıcının belirli tarihler aralığında yapmış olduğu yorumları gösteren bir metot yazınız.", description = "Örnek tarih : 2022-09-05 (yyyy-MM-dd)")
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

	@Operation(summary = "5) Son kullanma tarihi geçmiş ürünleri listeleyen bir metot yazınız.")
	@GetMapping("/5/urun/expired")
	private ResponseEntity<List<Urun>> urunFindAllByExpired() {
		List<Urun> urunList = urunService.findAllByExpired();
		return ResponseEntity.ok(urunList);
	}

	@Operation(summary = "6) Son kullanma tarihi geçmemiş ürünleri listeleyen bir metot yazınız. (Son kullanma tarihi boş olanlar da gelmeli.)")
	@GetMapping("/6/urun/not-expired")
	private ResponseEntity<List<Urun>> urunFindAllByNotExpired() {
		List<Urun> urunList = urunService.findAllByNotExpired();
		return ResponseEntity.ok(urunList);
	}
}
