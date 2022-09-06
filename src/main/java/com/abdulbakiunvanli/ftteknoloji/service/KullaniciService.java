package com.abdulbakiunvanli.ftteknoloji.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdulbakiunvanli.ftteknoloji.model.Kullanici;
import com.abdulbakiunvanli.ftteknoloji.repository.KullaniciRepository;

@Service
public class KullaniciService {

	@Autowired
	private KullaniciRepository kullaniciRepository;

	public List<Kullanici> findAll() {
		return kullaniciRepository.findAll();
	}

	public Optional<Kullanici> findById(Integer id) {
		return kullaniciRepository.findById(id);
	}

	public Kullanici save(Kullanici kullanici) {
		return kullaniciRepository.save(kullanici);
	}

	public void delete(Kullanici kullanici) {
		kullaniciRepository.delete(kullanici);
	}
	
	public Boolean existsById(Integer id) {
		return kullaniciRepository.existsById(id);
	}
}
