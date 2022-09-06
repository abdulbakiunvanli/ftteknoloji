package com.abdulbakiunvanli.ftteknoloji.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.abdulbakiunvanli.ftteknoloji.annotations.AnyNotNull;

@Entity(name = "urun_yorum")
public class UrunYorum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "yorum", nullable = false, length = 500)
	@NotBlank(message = "Yorum boş geçilemez.")
	@Length(max = 500, message = "Yorum en fazla 500 karakter olabilir.")
	private String yorum;

	@Column(name = "yorum_tarihi", nullable = false)
	@AnyNotNull(message = "Yorum tarihi boş geçilemez.")
	private LocalDate yorumTarihi;

	@Column(name = "urun_id", nullable = false)
	@NotNull(message = "urunId boş geçilemez.")
	private Integer urunId;

	@Column(name = "kullanici_id", nullable = false)
	@NotNull(message = "kullaniciId boş geçilemez.")
	private Integer kullaniciId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "urun_id", foreignKey = @ForeignKey(name = "fk_urun_urun_yorum"), insertable = false, updatable = false)
	private Urun urun;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kullanici_id", foreignKey = @ForeignKey(name = "fk_kullanici_urun_yorum"), insertable = false, updatable = false)
	private Kullanici kullanici;

	public UrunYorum() {

	}

	public UrunYorum(final String yorum, final LocalDate yorumTarihi, final Integer urunId, final Integer kullaniciId,
			final Urun urun, final Kullanici kullanici) {
		super();
		this.yorum = yorum;
		this.yorumTarihi = yorumTarihi;
		this.urunId = urunId;
		this.kullaniciId = kullaniciId;
		this.urun = urun;
		this.kullanici = kullanici;
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

	public Urun getUrun() {
		return urun;
	}

	public void setUrun(Urun urun) {
		this.urun = urun;
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}
}
