package com.abdulbakiunvanli.ftteknoloji.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UrunYorumDTO {

	private Integer id;
	
	@NotBlank(message = "Yorum boş geçilemez.")
	@Length(max = 500, message = "Yorum en fazla 500 karakter olabilir.")
	private String yorum;
	
	private LocalDate yorumTarihi;
	
	private Integer urunId;
	
	private Integer kullaniciId;

	public UrunYorumDTO() {

	}

	public UrunYorumDTO(final String yorum, final LocalDate yorumTarihi, final Integer urunId,
			final Integer kullaniciId) {
		super();
		this.yorum = yorum;
		this.yorumTarihi = yorumTarihi;
		this.urunId = urunId;
		this.kullaniciId = kullaniciId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYorum() {
		return yorum;
	}

	public void setYorum(String yorum) {
		this.yorum = yorum;
	}

	public LocalDate getYorumTarihi() {
		return yorumTarihi;
	}

	public void setYorumTarihi(LocalDate yorumTarihi) {
		this.yorumTarihi = yorumTarihi;
	}

	public Integer getUrunId() {
		return urunId;
	}

	public void setUrunId(Integer urunId) {
		this.urunId = urunId;
	}

	public Integer getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(Integer kullaniciId) {
		this.kullaniciId = kullaniciId;
	}
}
