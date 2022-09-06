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

import com.abdulbakiunvanli.ftteknoloji.model.Kullanici;
import com.abdulbakiunvanli.ftteknoloji.service.KullaniciService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/kullanici")
@Tag(name = "Kullanıcı API")
public class KullaniciController {

	@Autowired
	private KullaniciService kullaniciService;

	@GetMapping()
	private ResponseEntity<List<Kullanici>> getUsers() {
		List<Kullanici> kullaniciList = kullaniciService.findAll();
		return ResponseEntity.ok(kullaniciList);
	}

	@GetMapping("/{id}")
	private ResponseEntity<Kullanici> getUser(@PathVariable(value = "id", required = true) Integer id) {
		Optional<Kullanici> kullanici = kullaniciService.findById(id);
		if (!kullanici.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(kullanici.get());
	}

	@PostMapping()
	private ResponseEntity<Kullanici> createUser(@Valid @RequestBody Kullanici kullaniciRequest) {
		Kullanici kullanici = kullaniciService.save(new Kullanici(kullaniciRequest.getAd(), kullaniciRequest.getSoyad(),
				kullaniciRequest.getEmail(), kullaniciRequest.getTelefon()));
		return ResponseEntity.status(HttpStatus.CREATED).body(kullanici);
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<HttpStatus> deleteUser(@PathVariable(value = "id", required = true) Integer id) {
		Optional<Kullanici> kullanici = kullaniciService.findById(id);
		if (!kullanici.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		kullaniciService.delete(kullanici.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	private ResponseEntity<HttpStatus> updateUser(@PathVariable(value = "id", required = true) Integer id,
			@Valid @RequestBody Kullanici kullaniciRequest) {
		Optional<Kullanici> kullanici = kullaniciService.findById(id);
		if (!kullanici.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		kullaniciRequest.setId(kullanici.get().getId());
		kullaniciService.save(kullaniciRequest);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
