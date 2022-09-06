package com.abdulbakiunvanli.ftteknoloji.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdulbakiunvanli.ftteknoloji.dto.UrunYorumDTO;
import com.abdulbakiunvanli.ftteknoloji.exception.ResourceNotFoundException;
import com.abdulbakiunvanli.ftteknoloji.model.UrunYorum;
import com.abdulbakiunvanli.ftteknoloji.service.KullaniciService;
import com.abdulbakiunvanli.ftteknoloji.service.UrunService;
import com.abdulbakiunvanli.ftteknoloji.service.UrunYorumService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/urun-yorum")
@Tag(name = "Ürün Yorum API")
public class UrunYorumController {

	@Autowired
	private UrunYorumService urunYorumService;
	@Autowired
	private UrunService urunService;
	@Autowired
	private KullaniciService kullaniciService;

	@GetMapping()
	private ResponseEntity<List<UrunYorum>> getUrunYorum() {
		List<UrunYorum> urunYorumList = urunYorumService.findAll();
		return ResponseEntity.ok(urunYorumList);
	}

	@GetMapping("/{id}")
	private ResponseEntity<UrunYorum> getUrunYorum(@PathVariable(value = "id", required = true) Integer id) {
		Optional<UrunYorum> urunYorum = urunYorumService.findById(id);
		if (!urunYorum.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(urunYorum.get());
	}

	@PostMapping()
	private ResponseEntity<UrunYorum> createUrunYorum(@Valid @RequestBody UrunYorum urunYorumRequest) {
		UrunYorum urunYorum = kullaniciService.findById(urunYorumRequest.getKullaniciId()).map(kullanici -> {
			return urunService.findById(urunYorumRequest.getUrunId()).map(urun -> {
				return urunYorumService
						.save(new UrunYorum(urunYorumRequest.getYorum(), urunYorumRequest.getYorumTarihi(),
								urunYorumRequest.getUrunId(), urunYorumRequest.getKullaniciId(), urun, kullanici));
			}).orElseThrow(
					() -> new ResourceNotFoundException("Ürün bulunamadı. id = " + urunYorumRequest.getUrunId()));
		}).orElseThrow(
				() -> new ResourceNotFoundException("Kullanıcı bulunamadı. id = " + urunYorumRequest.getKullaniciId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(urunYorum);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<HttpStatus> deleteUrunYorum(@PathVariable(value = "id", required = true) Integer id) {
		Optional<UrunYorum> urunYorum = urunYorumService.findById(id);
		if (!urunYorum.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		urunYorumService.delete(urunYorum.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	private ResponseEntity<HttpStatus> updateUrunYorum(@PathVariable(value = "id", required = true) Integer id,
			@Valid @RequestBody UrunYorumDTO urunYorumDTORequest) {
		Optional<UrunYorum> urunYorum = urunYorumService.findById(id);
		if (!urunYorum.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		UrunYorum urunYorumData = urunYorum.get();
		urunYorumData.setYorum(urunYorumDTORequest.getYorum());
		urunYorumData.setYorumTarihi(urunYorumDTORequest.getYorumTarihi());
		urunYorumService.save(urunYorumData);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
