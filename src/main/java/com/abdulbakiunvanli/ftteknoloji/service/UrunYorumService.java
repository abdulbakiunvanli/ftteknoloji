package com.abdulbakiunvanli.ftteknoloji.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdulbakiunvanli.ftteknoloji.model.UrunYorum;
import com.abdulbakiunvanli.ftteknoloji.repository.UrunYorumRepository;

@Service
public class UrunYorumService {

	@Autowired
	private UrunYorumRepository urunYorumRepository;

	public List<UrunYorum> findAll() {
		return urunYorumRepository.findAll();
	}

	public Optional<UrunYorum> findById(Integer id) {
		return urunYorumRepository.findById(id);
	}

	public UrunYorum save(UrunYorum urunYorum) {
		return urunYorumRepository.save(urunYorum);
	}

	public void delete(UrunYorum urunYorum) {
		urunYorumRepository.delete(urunYorum);
	}

	public List<UrunYorum> findAllByUrunId(Integer urunId) {
		return urunYorumRepository.findAllByUrunId(urunId);
	}

	public List<UrunYorum> findAllByKullaniciId(Integer kullaniciId) {
		return urunYorumRepository.findAllByKullaniciId(kullaniciId);
	}
	
	public List<UrunYorum> findAllByUrunIdAndYorumTarihiBetween(Integer urunId, LocalDate startDate, LocalDate endDate) {
		return urunYorumRepository.findAllByUrunIdAndYorumTarihiBetween(urunId, startDate, endDate);
	}
	
	public List<UrunYorum> findAllByKullaniciIdAndYorumTarihiBetween(Integer kullaniciId, LocalDate startDate, LocalDate endDate) {
		return urunYorumRepository.findAllByKullaniciIdAndYorumTarihiBetween(kullaniciId, startDate, endDate);
	}
}
