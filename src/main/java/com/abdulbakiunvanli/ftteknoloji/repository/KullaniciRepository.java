package com.abdulbakiunvanli.ftteknoloji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdulbakiunvanli.ftteknoloji.model.Kullanici;

@Repository
public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {

}
