package com.abdulbakiunvanli.ftteknoloji.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdulbakiunvanli.ftteknoloji.model.UrunYorum;

@Repository
public interface UrunYorumRepository extends JpaRepository<UrunYorum, Integer> {

	List<UrunYorum> findAllByUrunId(Integer urunId);
	List<UrunYorum> findAllByKullaniciId(Integer kullaniciId);
	List<UrunYorum> findAllByUrunIdAndYorumTarihiBetween(Integer urunId, LocalDate startDate, LocalDate endDate);
	List<UrunYorum> findAllByKullaniciIdAndYorumTarihiBetween(Integer kullaniciId, LocalDate startDate, LocalDate endDate);
}
