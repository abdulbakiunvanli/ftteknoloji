package com.abdulbakiunvanli.ftteknoloji.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.abdulbakiunvanli.ftteknoloji.annotations.AnyNotNull;

@Entity(name = "urun")
public class Urun {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "ad", nullable = false, length = 250)
	@NotBlank(message = "Ad boş geçilemez.")
	@Length(max = 50, message = "Ad en fazla 50 karakter olabilir.")
	private String ad;

	@Column(name = "fiyat", nullable = false)
	@DecimalMin(value = "0.0", message = "Fiyat en az 0.0 olabilir.")
	@Digits(integer = 15, fraction = 2, message = "Geçerli fiyat giriniz (Decimal(15,2)).")
	@AnyNotNull(message = "Fiyat boş geçilemez.")
	private BigDecimal fiyat;

	@Column(name = "son_kullanma_tarihi")
	private LocalDate sonKullanmaTarihi;

	public Urun() {

	}

	public Urun(final String ad, final BigDecimal fiyat, final LocalDate sonKullanmaTarihi) {
		super();
		this.ad = ad;
		this.fiyat = fiyat;
		this.sonKullanmaTarihi = sonKullanmaTarihi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public BigDecimal getFiyat() {
		return fiyat;
	}

	public void setFiyat(BigDecimal fiyat) {
		this.fiyat = fiyat;
	}

	public LocalDate getSonKullanmaTarihi() {
		return sonKullanmaTarihi;
	}

	public void setSonKullanmaTarihi(LocalDate sonKullanmaTarihi) {
		this.sonKullanmaTarihi = sonKullanmaTarihi;
	}
}
