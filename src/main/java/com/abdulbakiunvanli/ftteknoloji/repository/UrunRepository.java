package com.abdulbakiunvanli.ftteknoloji.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdulbakiunvanli.ftteknoloji.model.Urun;

@Repository
public interface UrunRepository extends JpaRepository<Urun, Integer>{

	List<Urun> findAllBySonKullanmaTarihiBefore(LocalDate Date);
	List<Urun> findAllBySonKullanmaTarihiGreaterThanEqualOrSonKullanmaTarihiIsNull(LocalDate Date);
}
