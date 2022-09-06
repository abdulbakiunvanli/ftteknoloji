package com.abdulbakiunvanli.ftteknoloji.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdulbakiunvanli.ftteknoloji.model.Urun;
import com.abdulbakiunvanli.ftteknoloji.repository.UrunRepository;

@Service
public class UrunService {

	@Autowired
	private UrunRepository urunRepository;

	public List<Urun> findAll() {
		return urunRepository.findAll();
	}

	public Optional<Urun> findById(Integer id) {
		return urunRepository.findById(id);
	}

	public Urun save(Urun urun) {
		return urunRepository.save(urun);
	}

	public void delete(Urun urun) {
		urunRepository.delete(urun);
	}
	
	public Boolean existsById(Integer id) {
		return urunRepository.existsById(id);
	}
	
	public List<Urun> findAllByExpired() {
		return urunRepository.findAllBySonKullanmaTarihiBefore(LocalDate.now());
	}
	
	public List<Urun> findAllByNotExpired() {
		return urunRepository.findAllBySonKullanmaTarihiGreaterThanEqualOrSonKullanmaTarihiIsNull(LocalDate.now());
	}
}
