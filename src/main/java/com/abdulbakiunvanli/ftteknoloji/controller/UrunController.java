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

import com.abdulbakiunvanli.ftteknoloji.model.Urun;
import com.abdulbakiunvanli.ftteknoloji.service.UrunService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/urun")
@Tag(name = "Ürün API")
public class UrunController {

	@Autowired
	private UrunService urunService;

	@GetMapping()
	private ResponseEntity<List<Urun>> getUrun() {
		List<Urun> urunList = urunService.findAll();
		return ResponseEntity.ok(urunList);
	}

	@GetMapping("/{id}")
	private ResponseEntity<Urun> getUrun(@PathVariable(value = "id", required = true) Integer id) {
		Optional<Urun> urun = urunService.findById(id);
		if (!urun.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(urun.get());
	}

	@PostMapping()
	private ResponseEntity<Urun> createUrun(@Valid @RequestBody Urun urunRequest) {
		Urun urun = urunService
				.save(new Urun(urunRequest.getAd(), urunRequest.getFiyat(), urunRequest.getSonKullanmaTarihi()));
		return ResponseEntity.status(HttpStatus.CREATED).body(urun);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<HttpStatus> deleteUrun(@PathVariable(value = "id", required = true) Integer id) {
		Optional<Urun> urun = urunService.findById(id);
		if (!urun.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		urunService.delete(urun.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	private ResponseEntity<HttpStatus> updateUrun(@PathVariable(value = "id", required = true) Integer id,
			@Valid @RequestBody Urun urunRequest) {
		Optional<Urun> urun = urunService.findById(id);
		if (!urun.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		urunRequest.setId(urun.get().getId());
		urunService.save(urunRequest);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
