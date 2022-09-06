package com.abdulbakiunvanli.ftteknoloji.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "kullanici", indexes = { @Index(name = "telefon_UNIQUE", columnList = "telefon", unique = true),
		@Index(name = "email_UNIQUE", columnList = "email", unique = true) })
public class Kullanici {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "ad", nullable = false, length = 50)
	@NotBlank(message = "Ad boş geçilemez.")
	@Length(max = 50, message = "Ad en fazla 50 karakter olabilir.")
	private String ad;

	@Column(name = "soyad", nullable = false, length = 50)
	@NotBlank(message = "Soyad boş geçilemez.")
	@Length(max = 50, message = "Soyad en fazla 50 karakter olabilir.")
	private String soyad;

	@Column(name = "email", unique = true, nullable = false, length = 50)
	@NotBlank(message = "Email boş geçilemez.")
	@Email(message = "Geçerli email giriniz.")
	@Length(max = 50, message = "Email en fazla 50 karakter olabilir.")
	private String email;

	@Column(name = "telefon", unique = true, nullable = false, length = 15)
	@NotBlank(message = "Telefon boş geçilemez.")
	@Length(max = 15, message = "Telefon en fazla 15 karakter olabilir.")
	private String telefon;

	public Kullanici() {

	}

	public Kullanici(final String ad, final String soyad, final String email, final String telefon) {
		this.ad = ad;
		this.soyad = soyad;
		this.email = email;
		this.telefon = telefon;
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

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
}
